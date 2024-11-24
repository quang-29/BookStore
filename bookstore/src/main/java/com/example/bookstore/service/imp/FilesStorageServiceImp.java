package com.example.bookstore.service.imp;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface FilesStorageServiceImp {
    public void init();
    public boolean save(MultipartFile file);
    public Resource load(String filename);
}
