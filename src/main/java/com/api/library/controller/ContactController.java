package com.api.library.controller;

import com.api.library.domain.util.Url;
import com.api.library.dto.ContactDTO;
import java.util.List;

import com.api.library.domain.service.IContactService;
import com.api.library.exception.BadRequestException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(Url.CONTACTS_URI)
public class ContactController {

    @Autowired
    private IContactService contactService;

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@Valid @RequestBody ContactDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }

        return new ResponseEntity<>(contactService.save(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ContactDTO>> getContacts() {
        List<ContactDTO> listDto = this.contactService.getAll();
        return ResponseEntity.ok().body(listDto);
    }
}
