package com.api.library.domain.service.impl;

import com.api.library.domain.service.IContactService;
import com.api.library.dto.ContactDTO;
import com.api.library.exception.NotFoundException;
import com.api.library.domain.model.Contact;
import com.api.library.domain.repository.ContactRepository;
import com.api.library.mapper.ContactMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private ContactMapper mapper;

    @Override
    public ContactDTO save(ContactDTO contactDTO) {
        Contact entity = mapper.contactDTO2Entity(contactDTO);
        Contact entitySaved = contactRepository.save(entity);
        return mapper.contactEntity2DTO(entitySaved);
    }

    @Override
    public List<ContactDTO> getAll() {
        List<Contact> listEntity = contactRepository.findAll();
        if (listEntity.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<ContactDTO> listDto = mapper.entityListToDtoList(listEntity);
        return listDto;
    }
}
