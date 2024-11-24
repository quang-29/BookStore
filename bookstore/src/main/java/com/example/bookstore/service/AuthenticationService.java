package com.example.bookstore.service;

import com.example.bookstore.dto.request.IntrospectRequest;
import com.example.bookstore.dto.request.LogOutRequest;
import com.example.bookstore.dto.request.RefreshTokenRequest;
import com.example.bookstore.dto.response.IntrospectResponse;
import com.example.bookstore.dto.response.LogInResponse;
import com.example.bookstore.dto.response.RefreshTokenResponse;
import com.example.bookstore.entity.Role;
import com.example.bookstore.entity.TokenInValid;
import com.example.bookstore.entity.User;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.repository.InvalidTokenRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.imp.AuthenticationServiceImp;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
public class AuthenticationService implements AuthenticationServiceImp {

    @NonFinal
    @Value("${jwt.signing.key}")
    private String signingKey;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    InvalidTokenRepository invalidTokenRepository;
    @Override
    public boolean signUp(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        Role role = new Role();
        role.setId(2);
        user.setRole(role);
        if(userRepository.existsByUsername(username)) {
            return false;
        } else {
            userRepository.save(user);
            return true;
        }

    }

    @Override
    public LogInResponse logIn(String username, String password) {
        var user = userRepository.findByUsername(username);
        if(user.isPresent()) {
           boolean authenticated = passwordEncoder.matches(password, user.get().getPassword());
           if(authenticated) {
               String token = generateToken(user.get());
               return LogInResponse.builder()
                       .authenticated(true)
                       .token(token)
                       .build();
           }

        }
        return LogInResponse.builder()
                .authenticated(false)
                .token("")
                .build();

    }

    @Override
    public IntrospectResponse introspect(IntrospectRequest request) {
        var token = request.getToken();
        boolean isValid = true;

        try {
            verifyToken(token);
        } catch (AppException e) {
            isValid = false;
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return IntrospectResponse.builder()
                .valid(isValid)
                .build();
    }

    private SignedJWT verifyToken(String token) throws JOSEException, ParseException {
        JWSVerifier verifier = new MACVerifier(signingKey);

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        if (!(verified && expiryTime.after(new Date())))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        if (invalidTokenRepository
                .existsById(signedJWT.getJWTClaimsSet().getJWTID()))
            throw new AppException(ErrorCode.UNAUTHENTICATED);

        return signedJWT;
    }


    public String generateToken(User user) {

        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("com.example")
                .issueTime(new Date())
                .jwtID(UUID.randomUUID().toString())
                .expirationTime(new Date(
                        Instant.now().plus(2, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", user.getRole().getRoleName())
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(signingKey.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public void logOut(LogOutRequest request) throws ParseException, JOSEException {

        SignedJWT signedJWT = verifyToken(request.getToken());
        String jit = signedJWT.getJWTClaimsSet().getJWTID();
        Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        TokenInValid invalidatedToken = TokenInValid.builder()
                .id(jit)
                .expires(expiryTime)
                .token(request.getToken())
                .build();

        invalidTokenRepository.save(invalidatedToken);
        }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws ParseException, JOSEException {
        var signedJWT = verifyToken(request.getToken());

        var jit = signedJWT.getJWTClaimsSet().getJWTID();
        var expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

        TokenInValid tokenInValid =
                TokenInValid.builder()
                        .id(jit)
                        .expires(expiryTime)
                        .token(request.getToken())
                        .build();

        invalidTokenRepository.save(tokenInValid);

        var username = signedJWT.getJWTClaimsSet().getSubject();

        var user = userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHENTICATED));

        var token = generateToken(user);

        return RefreshTokenResponse.builder().token(token).valid(true).build();
    }
}
