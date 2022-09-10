package com.api.library.domain.service.impl;

import com.api.library.dto.TestimonialDTO;
import com.api.library.exception.NotFoundException;
import com.api.library.mapper.TestimonialMapper;
import com.api.library.domain.model.Testimonial;
import com.api.library.domain.repository.TestimonialRepository;
import com.api.library.domain.service.ITestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TestimonialServiceImpl implements ITestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    @Autowired
    private TestimonialMapper mapper;

    @Override
    @Transactional
    public TestimonialDTO save(TestimonialDTO dto) {
        Testimonial testimonial = mapper.testimonialDto2Entity(dto);
        return mapper.testimonialEntity2Dto(testimonialRepository.save(testimonial));
    }

    @Override
    @Transactional
    public TestimonialDTO update(Long id, TestimonialDTO dto) {
        Optional<Testimonial> entity = this.testimonialRepository.findById(id);
        if (entity.isEmpty()) {
            throw new NotFoundException("Testimonial id does not exist");
        }
        this.mapper.entityTestimonialRefreshValues(entity.get(), dto);
        Testimonial result = this.testimonialRepository.save(entity.get());
        return this.mapper.testimonialEntity2Dto(result);
    }

    @Override
    @Transactional
    public void deleteTestimonial(Long id) throws NotFoundException {
        try {
            testimonialRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Testimonial id does not exist");
        }
    }
}
