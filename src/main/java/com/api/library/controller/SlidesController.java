package com.api.library.controller;

import com.api.library.domain.util.Url;
import com.api.library.dto.SlidesDTO;
import com.api.library.dto.SlidesRequestDTO;
import com.api.library.dto.SlidesResponseDTO;
import com.api.library.domain.service.ISlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Url.SLIDES_URI)
public class SlidesController {

    @Autowired
    private ISlideService iSlideService;

    @GetMapping
    public ResponseEntity<List<SlidesDTO>> getSlides(){
        List<SlidesDTO> result = this.iSlideService.findAll();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping
    public ResponseEntity<SlidesResponseDTO> createSlides(@RequestBody SlidesRequestDTO requestDTO){
        SlidesResponseDTO result = this.iSlideService.save(requestDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSlidesById(@PathVariable Long id){
        iSlideService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SlidesResponseDTO> getSlidesResponseDTO(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(iSlideService.getSlidesById(id));
    }

}
