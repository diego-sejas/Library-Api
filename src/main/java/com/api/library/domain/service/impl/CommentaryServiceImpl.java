package com.api.library.domain.service.impl;

import com.api.library.auth.dto.UserResponseDto;
import com.api.library.auth.repository.UserRepository;
import com.api.library.auth.service.impl.UserDetailsCustomService;
import com.api.library.domain.model.Commentary;
import com.api.library.domain.model.News;

import com.api.library.domain.model.UserEntity;
import com.api.library.domain.repository.CommentaryRepository;
import com.api.library.domain.repository.NewsRepository;
import com.api.library.domain.service.ICommentaryService;
import com.api.library.dto.CommentaryBodyDTO;
import com.api.library.dto.CommentaryDTO;
import com.api.library.exception.ForbiddenException;
import com.api.library.exception.NotFoundException;
import com.api.library.mapper.CommentaryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentaryServiceImpl implements ICommentaryService {

    private static final String ID_NOT_FOUND = "Id not found: ";

    @Autowired
    private CommentaryRepository commentaryRepository;

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsCustomService userDetailsCustomService;

    @Autowired
    private CommentaryMapper mapper;


    @Override
    public CommentaryDTO save(CommentaryDTO commentaryDTO) {

        newsRepository.findById(commentaryDTO.getNews().getId()).orElseThrow(() -> new NotFoundException("Post does not exist"));
        userRepository.findById(commentaryDTO.getUser().getId()).orElseThrow(() -> new NotFoundException("User does not exist"));
        Commentary commentary = mapper.commentaryDTO2Entity(commentaryDTO);
        Commentary commentarySaved = commentaryRepository.save(commentary);
        return mapper.commentaryEntity2DTO(commentarySaved);
    }

    @Override
    public List<CommentaryBodyDTO> getCommentaries() {
        List<Commentary> entityList = commentaryRepository.findAllByOrderByCreationDate();
        if (entityList.isEmpty()) {
            throw new NotFoundException("Commentary list is empty");
        }
        List<CommentaryBodyDTO> dtoList = mapper.entityListToDtoList(entityList);
        return dtoList;
    }

    @Override
    @Transactional
    public CommentaryBodyDTO update(Long id, CommentaryBodyDTO dto, HttpServletRequest request) {
        Optional<Commentary> entity = this.commentaryRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Commentary id does not exist");
        }
        Commentary commentary = commentaryRepository.findById(id).orElseThrow(() -> new NotFoundException(ID_NOT_FOUND + id));
        UserResponseDto responseDto = userDetailsCustomService.getProfile(request);
        UserEntity user = userRepository.findByEmail(responseDto.getEmail()).get();
        if (Objects.equals(user.getId(), commentary.getUserEntity().getId()) || request.isUserInRole("ROLE_ADMIN")) {
            this.mapper.entityCommentaryRefreshValues(entity.get(), dto);
            Commentary result = this.commentaryRepository.save(entity.get());
            return this.mapper.commentaryEntityBodyToDTO(result);
        } else {
            throw new ForbiddenException("User with no access.");
        }

    }
    @Transactional
    @Override
    public void deleteById(Long id, HttpServletRequest request) {
        Commentary commentary = commentaryRepository.findById(id).orElseThrow(() -> new NotFoundException(ID_NOT_FOUND + id));
        UserResponseDto responseDto = userDetailsCustomService.getProfile(request);
        UserEntity user = userRepository.findByEmail(responseDto.getEmail()).get();
        if (Objects.equals(user.getId(), commentary.getUserEntity().getId()) || request.isUserInRole("ROLE_ADMIN")){
            commentaryRepository.deleteById(id);
        }else{
            throw new ForbiddenException("User with no access.");
        }
    }

    public List<CommentaryBodyDTO> findAllById(Long id){
        News news = newsRepository.findById(id).orElseThrow(() -> new NotFoundException("Post no exist Id: "+id));
        List<Commentary> listAll = commentaryRepository.findByNews(news);
            return mapper.entityListToDtoList(listAll);
    }

}