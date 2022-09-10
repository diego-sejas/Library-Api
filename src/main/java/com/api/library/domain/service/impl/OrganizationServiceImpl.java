package com.api.library.domain.service.impl;

import com.api.library.dto.OrganizationDTO;
import com.api.library.dto.OrganizationUpdateDTO;
import com.api.library.dto.SlidesDTO;
import com.api.library.exception.NotFoundException;
import com.api.library.mapper.OrganizationMapper;
import com.api.library.mapper.SlideMapper;
import com.api.library.domain.model.Organization;
import com.api.library.domain.model.Slides;
import com.api.library.domain.repository.OrganizationRepository;
import com.api.library.domain.repository.SlidesRepository;
import com.api.library.domain.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private SlidesRepository slidesRepository;

    @Autowired
    private SlideMapper slidesMapper;

    @Override
    @Transactional
    public OrganizationDTO addOrganization(OrganizationDTO organizationDto) {

        Organization organization = organizationMapper.organizationDto2Entity(organizationDto);
        Organization savedEntity = organizationRepository.save(organization);
        return organizationMapper.organizationEntity2DTO(savedEntity);

    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationDTO getOrg() {

        Optional<Organization> org = organizationRepository.findAll().stream().findFirst();

        if (!org.isPresent()) {
            throw new NotFoundException("The organization ins't exist");
        }

        List<Slides> slides = slidesRepository.findAll();
        List<SlidesDTO> slidesDTOS = slides.stream().map(s -> slidesMapper.entitySlides2SlidesDto(s)).collect(Collectors.toList());

        OrganizationDTO orgDTO = organizationMapper.organizationEntity2DTOSlides(org.get(), slidesDTOS);

        return orgDTO;

    }

    @Override
    public Organization updateOrganizationDto(OrganizationUpdateDTO orgUpdate) throws NotFoundException {
        Optional<Organization> org = organizationRepository.findById(orgUpdate.getId());

        if (org.isEmpty()) {
            throw new NotFoundException("Organization id does not exist");
        } else {
            return organizationMapper.organizationUpdateDTO2Entity(orgUpdate, org.get());
        }
    }

    @Override
    public void updateOrganization(Organization org) {
        organizationRepository.save(org);
       }
}
