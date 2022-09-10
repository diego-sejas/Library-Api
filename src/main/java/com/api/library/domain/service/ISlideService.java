package com.api.library.domain.service;

import com.api.library.dto.SlidesDTO;
import com.api.library.dto.SlidesRequestDTO;
import com.api.library.dto.SlidesResponseDTO;

import java.util.List;

public interface ISlideService {

    SlidesResponseDTO save(SlidesRequestDTO requestDTO);

    List<SlidesDTO> findAll();

    void deleteById(Long id);

    SlidesResponseDTO getSlidesById(Long id);

    List<SlidesResponseDTO> getSlidesByOrganiztionId(Long organizationId);
}
