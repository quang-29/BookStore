package com.example.bookstore.service.imp;


import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.StoreDTO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

public interface StoreServiceImp {

    boolean createStore(MultipartFile file,
                        String storeName,
                        String storeDescription,
                        String address,
                        String email,
                        String phone,
                        String openDate
                        );

    StoreDTO fingStoreById(int id);

    List<StoreDTO> getAllStores();

    List<BookDTO> getAllBooksOfStore(int id);
}
