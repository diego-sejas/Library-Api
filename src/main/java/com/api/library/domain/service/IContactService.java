package com.api.library.domain.service;

import com.api.library.dto.ContactDTO;
import java.util.List;

public interface IContactService {
    ContactDTO save(ContactDTO contactDTO);
    
    List<ContactDTO> getAll();
}
