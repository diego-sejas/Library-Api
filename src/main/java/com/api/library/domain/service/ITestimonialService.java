package com.api.library.domain.service;

import com.api.library.dto.TestimonialDTO;
import org.springframework.transaction.annotation.Transactional;

public interface ITestimonialService {

    @Transactional
    TestimonialDTO save(TestimonialDTO dto);

    @Transactional
    TestimonialDTO update(Long id, TestimonialDTO dto);

    @Transactional
    void deleteTestimonial(Long id);
}
