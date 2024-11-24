package com.example.bookstore.controller;

import com.example.bookstore.dto.AuthorDTO;
import com.example.bookstore.dto.request.AuthorRequest;
import com.example.bookstore.dto.response.DataResponse;
import com.example.bookstore.exception.AppException;
import com.example.bookstore.service.imp.AuthorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    AuthorServiceImp authorServiceImp;

    @GetMapping("/getAllAuthors")
    public ResponseEntity<DataResponse> getAllAuthors() {
        List<AuthorDTO> authors = authorServiceImp.getAuthors();
        DataResponse dataResponse = DataResponse.builder()
                .data(authors).build();
        return ResponseEntity.ok().body(dataResponse);
    }
    @GetMapping("/getAuthorById/{id}")
    public ResponseEntity<DataResponse> getAuthorById(@PathVariable int id) {
        AuthorDTO authorDTO = authorServiceImp.getAuthorById(id);
        DataResponse dataResponse = DataResponse.builder()
                .data(authorDTO).build();
        return ResponseEntity.ok().body(dataResponse);

    }
    @PostMapping("/addAuthor")
    public ResponseEntity<DataResponse> addAuthor(@RequestBody AuthorRequest authorRequest) {
        boolean isAddSuccess = authorServiceImp.addAuthor(authorRequest);
        DataResponse dataResponse = DataResponse.builder()
                .message(isAddSuccess?"Add author successfully":"Add author failed")
                .data(isAddSuccess).build();
        return ResponseEntity.ok().body(dataResponse);
    }

    @PostMapping("/updateAuthor/{id}")
    public ResponseEntity<DataResponse> updateAuthor(@PathVariable("id") int id, @RequestBody AuthorRequest authorRequest) {
        try {
            boolean isUpdateSuccess = authorServiceImp.updateAuthor(id, authorRequest);
            DataResponse dataResponse = DataResponse.builder()
                    .message("Update author successfully")
                    .data(isUpdateSuccess)
                    .build();
            return ResponseEntity.ok(dataResponse); // 200 OK nếu thành công
        } catch (AppException e) {
            DataResponse dataResponse = DataResponse.builder()
                    .message("Update author failed: " + e.getMessage())
                    .data(false)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dataResponse); // 404 nếu không tìm thấy tác giả
        }
    }


    @DeleteMapping("/deleteAuthor/{id}")
    public ResponseEntity<DataResponse> deleteAuthor(@PathVariable int id) {
        boolean isDeleteAuthor = authorServiceImp.deleteAuthor(id);
        DataResponse dataResponse = DataResponse.builder()
                .message(isDeleteAuthor?"Delete author successfully":"Delete author failed")
                .data(isDeleteAuthor).build();
        return ResponseEntity.ok().body(dataResponse);
    }
}

