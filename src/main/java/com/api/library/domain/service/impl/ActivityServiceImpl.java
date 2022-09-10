package com.api.library.domain.service.impl;

import com.api.library.dto.ActivityDTO;
import com.api.library.exception.NotFoundException;
import com.api.library.mapper.ActivityMapper;
import com.api.library.domain.model.Activity;
import com.api.library.domain.repository.ActivityRepository;
import com.api.library.domain.service.IActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ActivityServiceImpl implements IActivityService {

    @Autowired
    private ActivityMapper activityMapper;

    @Autowired
    private ActivityRepository activityRepository;

    @Transactional
    @Override
    public ActivityDTO save(ActivityDTO activityDTO) {
        Activity entity = activityMapper.activityDTOtoEntity(activityDTO);
        Activity activitySaved = activityRepository.save(entity);
        return activityMapper.activityEntityToDTO(activitySaved);
    }

    @Transactional
    @Override
    public ActivityDTO update(Long id, ActivityDTO activityDTO) {
        Optional<Activity> entity = activityRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Activity with id provided not found");
        }
        this.activityMapper.activityEntityRefreshValues(entity.get(), activityDTO);
        return this.activityMapper.activityEntityToDTO(activityRepository.save(entity.get()));
    }

}
