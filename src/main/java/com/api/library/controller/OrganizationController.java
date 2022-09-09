package com.api.library.controller;


import com.api.library.domain.model.Organization;
import com.api.library.domain.service.IOrganizationService;
import com.api.library.domain.service.ISlideService;
import com.api.library.domain.util.Url;
import com.api.library.dto.OrganizationDTO;
import com.api.library.dto.OrganizationUpdateDTO;
import com.api.library.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.ORGANIZATION_URI)
public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @Autowired
    private ISlideService slidesService;

    @GetMapping("/public")
    public ResponseEntity<OrganizationDTO> getOrganizationDTO(){
        OrganizationDTO organizationDTO = this.organizationService.getOrg();
        return ResponseEntity.ok().body(organizationDTO);
    }


    @PostMapping("/public")
    public ResponseEntity<OrganizationUpdateDTO> putUpdateOrganization (@RequestBody @Valid OrganizationUpdateDTO orgUpdate, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new BadRequestException(bindingResult);
        }
        Organization org = organizationService.updateOrganizationDto(orgUpdate);
        organizationService.updateOrganization(org);
        return ResponseEntity.ok().body(orgUpdate);
    }

    @PostMapping
    public ResponseEntity<OrganizationDTO> addOrganization(@Valid @RequestBody OrganizationDTO organizationDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        OrganizationDTO organizationDto = organizationService.addOrganization(organizationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(organizationDto);
}


}
