package com.api.library.domain.repository;

import com.api.library.domain.model.Commentary;
import java.util.List;
import com.api.library.domain.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {

    List<Commentary> findAllByOrderByCreationDate();

    List<Commentary> findByNews(News news);

}
