package com.api.library.domain.service;

import com.api.library.dto.NewsDTO;
import com.api.library.dto.PageDTO;
import org.springframework.transaction.annotation.Transactional;

public interface INewsService {

    NewsDTO save(NewsDTO dto);

    NewsDTO getById(Long id);

    void deleteById(Long id);

    NewsDTO updateNewsById(Long id, NewsDTO dto);

    @Transactional(readOnly = true)
    PageDTO<NewsDTO> getAllNewsPageable(Integer page);
}