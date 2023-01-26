package com.gold.smith.users.controller;

import com.gold.smith.users.model.FileUpload;
import com.gold.smith.users.model.FileUploadResponse;
import com.gold.smith.users.repository.FileUploadRepository;
import com.gold.smith.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class FileUploadController {

    @Autowired
    FileUploadRepository fileUploadRepository;

    @PostMapping("/upload/image")
    public ResponseEntity<FileUploadResponse> uplaodImage(@RequestParam("image") MultipartFile file)
            throws IOException {
        fileUploadRepository.save(new FileUpload(
                file.getOriginalFilename(),
                file.getContentType(),
                file.getBytes()));
                //FileUploadUtil.compressImage(file.getBytes())));
        return ResponseEntity.status(HttpStatus.OK)
                .body(new FileUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping(path = {"/get/image/info/{name}"})
    public FileUpload getImageDetails(@PathVariable("name") String name) throws IOException {
        final Optional<FileUpload> dbImage = fileUploadRepository.findByName(name);
        return  new FileUpload(
                dbImage.get().getName(),
                dbImage.get().getType(),
                dbImage.get().getImage());
    }

    @GetMapping(path = {"/get/image/{name}"})
    public ResponseEntity<byte[]> getImage(@PathVariable("name") String name) throws IOException {
        final Optional<FileUpload> dbImage = fileUploadRepository.findByName(name);
        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(FileUploadUtil.decompressImage(dbImage.get().getImage()));
    }
}