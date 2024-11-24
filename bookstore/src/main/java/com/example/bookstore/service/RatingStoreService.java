package com.example.bookstore.service;

import com.example.bookstore.dto.RatingStoreDTO;
import com.example.bookstore.dto.request.RatingStoreRequest;
import com.example.bookstore.entity.RatingStore;
import com.example.bookstore.entity.Store;
import com.example.bookstore.entity.User;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.repository.RatingStoreRepository;
import com.example.bookstore.repository.StoreRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.imp.RatingStoreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingStoreService implements RatingStoreServiceImp {

    @Autowired
    RatingStoreRepository ratingStoreRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    StoreRepository storeRepository;

    @Override
    public boolean createRatingStore(RatingStoreRequest ratingStoreRequest) {
        boolean isCreateSuccess = false;
        try {
            RatingStore ratingStore = new RatingStore();

            User user = userRepository.findById(ratingStoreRequest.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            Store store = storeRepository.findById(ratingStoreRequest.getStoreId())
                    .orElseThrow(()-> new AppException(ErrorCode.STORE_NOT_FOUND) );
            ratingStore.setUser(user);
            ratingStore.setStore(store);
            ratingStore.setContent(ratingStoreRequest.getContent());
            ratingStore.setRatePoint(ratingStoreRequest.getRatePoint());
            ratingStore.setCreatedAt(ratingStoreRequest.getCreatedAt());
            ratingStoreRepository.save(ratingStore);
            isCreateSuccess = true;
        } catch (Exception e) {
            isCreateSuccess = false;
            throw new RuntimeException(e);
        }
        return isCreateSuccess;
    }

    @Override
    public List<RatingStoreDTO> getAllRatingStore(int id) {
        List<RatingStore> ratingStores = ratingStoreRepository.findRatingStoresByStoreId(id);
        List<RatingStoreDTO> ratingStoreDTOS = new ArrayList<>();
        for (RatingStore ratingStore : ratingStores) {
            RatingStoreDTO ratingStoreDTO = new RatingStoreDTO();
            ratingStoreDTO.setStoreName(ratingStore.getStore().getStoreName());
            ratingStoreDTO.setUsername(ratingStore.getUser().getUsername());
            ratingStoreDTO.setContent(ratingStore.getContent());
            ratingStoreDTO.setRatePoint(ratingStore.getRatePoint());
            ratingStoreDTO.setCreatedAt(ratingStore.getCreatedAt());
            ratingStoreDTOS.add(ratingStoreDTO);
        }
        return ratingStoreDTOS;
    }
}
