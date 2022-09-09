package com.api.library.auth.controller;

import com.api.library.auth.model.Img;
import com.api.library.auth.repository.ImgRepository;
import com.api.library.auth.service.impl.S3ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/img")
public class ImgController {

    @Autowired
    private ImgRepository imgRepository;

    @Autowired
    private S3ServiceImpl s3ServiceImpl;

    @GetMapping
    List<Img> getAll() {
        return imgRepository.findAll()
                .stream()
                .peek(img -> img.setImagenUrl(s3ServiceImpl.getObjectUrl(img.getImagenPath())))
                .collect(Collectors.toList());
    }

    @PostMapping
    Img create(@RequestBody Img img) {
        imgRepository.save(img);
        img.setImagenUrl(s3ServiceImpl.getObjectUrl(img.getImagenPath()));
        return img;
    }


}
