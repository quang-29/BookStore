package com.example.bookstore.sercurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.oauth2.resourceserver.JwtDsl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    CustomJwtDecode customJwtDecode;


    private final String[] PUBLIC_ENDPOINTS = {"/auth/singUp","/auth/signIn","/auth/introspectToken",
                                            "/store/getAllStores","/store/getStoreById/{id}","/store/getAllBooksOfStore/{id}",
                                            "/book/getAllBooks","/book/getBookById/{id}",
                                            "/category/getAllCategories",
                                            "/ratingBook/showAllRatingBook/{id}",
                                            "/ratingStore/getAllRatingByStoreId/{id}",
                                            "/api/refreshToken",
                                            "/author/getAllAuthors"} ;
    private final String[] ADMIN_ENDPOINTS = {"/author/addAuthor","/author/updateAuthor/{id}","/author/deleteAuthor/{id}",
                                            "/category/addCategory","/file/*"
                                            ,"/store/createStore",
                                            "/user/getAllUsers", "/user/getUserById/{id}"
                                            ,"/user/updateUser/{id}","/user/deleteUser/{id}" };
    @Value("${jwt.signing.key}")
    private String signingKey;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests
                .requestMatchers(HttpMethod.GET, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.POST, PUBLIC_ENDPOINTS).permitAll()
                .requestMatchers(HttpMethod.POST, ADMIN_ENDPOINTS).hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.GET, ADMIN_ENDPOINTS).hasAuthority("ROLE_ADMIN")
                .anyRequest().authenticated()
        );
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.oauth2ResourceServer(
                oauth -> oauth
                .jwt(
                        jwtConfigurer -> jwtConfigurer.decoder(customJwtDecode)
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                )

                .authenticationEntryPoint(new JwtAuthenticationEntryPoint()))
                ;


        return httpSecurity.build();
    }

    JwtAuthenticationConverter jwtAuthenticationConverter(){

        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return converter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtDecoder jwtDecoder() {
        SecretKeySpec secretKeySpec = new SecretKeySpec(signingKey.getBytes(), "HS512");
        return NimbusJwtDecoder
                .withSecretKey(secretKeySpec)
                .macAlgorithm(MacAlgorithm.HS512)
                .build();
    }




}
