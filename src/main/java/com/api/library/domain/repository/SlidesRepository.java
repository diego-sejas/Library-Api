package com.api.library.domain.repository;

import com.api.library.domain.model.Organization;
import com.api.library.domain.model.Slides;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlidesRepository extends JpaRepository<Slides, Long> {

    List<Slides> findByOrganizations(Organization organization);

}