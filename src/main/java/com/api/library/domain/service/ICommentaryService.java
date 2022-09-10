package com.api.library.domain.service;

import com.api.library.dto.CommentaryBodyDTO;
import com.api.library.dto.CommentaryDTO;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICommentaryService{

    CommentaryDTO save(CommentaryDTO dto);
    
    List<CommentaryBodyDTO> getCommentaries();

    @Transactional
    CommentaryBodyDTO update(Long id, CommentaryBodyDTO dto, HttpServletRequest request);

    void deleteById(Long id, HttpServletRequest request);

    List<CommentaryBodyDTO> findAllById(Long id);

}
