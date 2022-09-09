package com.api.library.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.api.library.domain.model.Activity;


@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long>{
    

}
