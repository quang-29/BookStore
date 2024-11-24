package com.example.bookstore.service;

import com.example.bookstore.dto.RatingBookDTO;
import com.example.bookstore.dto.request.RatingBookRequest;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.RatingBook;
import com.example.bookstore.entity.User;
import com.example.bookstore.enums.ErrorCode;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.RatingBookRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.imp.RatingBookServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingBookService implements RatingBookServiceImp {
    private final BookRepository bookRepository;

    @Autowired
    RatingBookRepository ratingBookRepository;
    @Autowired
    UserRepository userRepository;

    public RatingBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public boolean addRatingBook(RatingBookRequest ratingBookRequest) {

        boolean isCreated = false;

        try {
            Book book = bookRepository.findById(ratingBookRequest.getBookId())
                    .orElseThrow(() -> new AppException(ErrorCode.BOOK_NOT_FOUND));
            User user = userRepository.findById(ratingBookRequest.getUserId())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            RatingBook ratingBook = new RatingBook();
            ratingBook.setBook(book);
            ratingBook.setUser(user);
            ratingBook.setContent(ratingBookRequest.getContent());
            ratingBook.setRatePoint(ratingBookRequest.getRatePoint());
            ratingBook.setCreatedAt(ratingBookRequest.getCreatedAt());
            ratingBookRepository.save(ratingBook);
            isCreated = true;

        } catch (Exception e) {
            isCreated = false;
            throw new RuntimeException(e);
        }

        return isCreated;
    }

    @Override
    public List<RatingBookDTO> getRatingBooksById(int id) {
        List<RatingBook> ratingBookList = ratingBookRepository.findAllRatingBookByBookId(id);
        List<RatingBookDTO> ratingBookDTOList = new ArrayList<>();
        for (RatingBook ratingBook : ratingBookList) {
            RatingBookDTO ratingBookDTO = new RatingBookDTO();
            User user = ratingBook.getUser();
            Book book = ratingBook.getBook();
            ratingBookDTO.setUsername(user.getUsername());
            ratingBookDTO.setBookName(book.getName());
            ratingBookDTO.setContent(ratingBook.getContent());
            ratingBookDTO.setRatePoint(ratingBook.getRatePoint());
            ratingBookDTO.setCreatedAt(ratingBook.getCreatedAt());
            ratingBookDTOList.add(ratingBookDTO);

        }
        return ratingBookDTOList;

    }
}
