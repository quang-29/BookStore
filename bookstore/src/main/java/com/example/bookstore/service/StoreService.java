package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.StoreDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Store;
import com.example.bookstore.entity.StoreBook;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.StoreBookRepository;
import com.example.bookstore.repository.StoreRepository;
import com.example.bookstore.service.imp.FilesStorageServiceImp;
import com.example.bookstore.service.imp.StoreServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoreService implements StoreServiceImp {

    @Autowired
    FilesStorageServiceImp filesStorageServiceImp;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    StoreBookRepository storeBookRepository;
    @Autowired
    BookRepository bookRepository;

    @Override
    public boolean createStore(MultipartFile file, String storeName, String storeDescription, String address, String email, String phone, String openDate) {
        boolean isSaveSuccess = false;

        try {
            if (filesStorageServiceImp.save(file)) {
                Store store = new Store();
                store.setStoreName(storeName);
                store.setStoreDescription(storeDescription);
                store.setAddress(address);
                store.setEmail(email);
                store.setPhone(phone);
                store.setImageStore(file.getOriginalFilename());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd[ HH:mm]");
                LocalDateTime open = LocalDateTime.parse(openDate + " 00:00", formatter);

                store.setOpenDate(open);
                storeRepository.save(store);
                isSaveSuccess = true;
            }
        } catch (Exception e) {
            throw new RuntimeException("Error creating store", e);
        }
        return isSaveSuccess;
    }

    @Override
    public StoreDTO fingStoreById(int id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.STORE_NOT_FOUND));
        StoreDTO storeDTO = new StoreDTO();
        storeDTO.setName(store.getStoreName());
        storeDTO.setDescription(store.getStoreDescription());
        storeDTO.setAddress(store.getAddress());
        storeDTO.setEmail(store.getEmail());
        storeDTO.setPhone(store.getPhone());
        return storeDTO;

    }

    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> stores = storeRepository.findAll();
        List<StoreDTO> storeDTOList = new ArrayList<>();
        for (Store store : stores) {
            StoreDTO storeDTO = new StoreDTO();
            storeDTO.setName(store.getStoreName());
            storeDTO.setDescription(store.getStoreDescription());
            storeDTO.setAddress(store.getAddress());
            storeDTO.setEmail(store.getEmail());
            storeDTO.setPhone(store.getPhone());
            storeDTOList.add(storeDTO);
        }
        return storeDTOList;
    }

    @Override
    public List<BookDTO> getAllBooksOfStore(int id) {
        List<StoreBook> storeBooks = storeBookRepository.findBookIdByStoreId(id);
        List<BookDTO> bookDTOList = new ArrayList<>();
        for (StoreBook storeBook : storeBooks) {
            Book book = bookRepository.findById(storeBook.getBook().getId())
                    .orElseThrow(()-> new AppException(ErrorCode.INVALID_REQUEST));
                BookDTO bookDTO = new BookDTO();
                bookDTO.setName(book.getName());
                bookDTO.setAuthor(book.getAuthor().getName());
                bookDTO.setPages(book.getPage());
                bookDTO.setCategory(book.getCategory().getName());
                bookDTO.setDescription(book.getDescription());
                bookDTO.setReprint(book.getReprint());
                bookDTO.setPrice(book.getPrice());
                bookDTOList.add(bookDTO);
        }
        return bookDTOList;

    }


}
