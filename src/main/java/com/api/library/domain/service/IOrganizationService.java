package com.api.library.domain.service;

import com.api.library.dto.OrganizationDTO;
import com.api.library.dto.OrganizationUpdateDTO;
import com.api.library.exception.NotFoundException;
import com.api.library.domain.model.Organization;
import org.springframework.transaction.annotation.Transactional;

public interface IOrganizationService {

    @Transactional
    OrganizationDTO addOrganization(OrganizationDTO organizationDto);

    @Transactional(readOnly = true)
    OrganizationDTO getOrg();

    Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate) throws NotFoundException;

    void updateOrganization(Organization org);

}
