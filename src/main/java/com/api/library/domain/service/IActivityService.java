package com.api.library.domain.service;

import com.api.library.dto.ActivityDTO;

public interface IActivityService {

    ActivityDTO save(ActivityDTO activityDTO);

    ActivityDTO update(Long id, ActivityDTO activityDTO);
}
