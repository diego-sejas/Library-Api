package com.api.library.mapper;

import com.api.library.dto.TestimonialDTO;
import com.api.library.domain.model.Testimonial;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

@Component
public class TestimonialMapper {


    public Testimonial testimonialDto2Entity(@NotNull TestimonialDTO dto) {
        Testimonial entityResult = new Testimonial();
        this.entityTestimonialRefreshValues(entityResult,dto);
        return entityResult;
    }

    public TestimonialDTO testimonialEntity2Dto(@NotNull Testimonial entity) {
        return new TestimonialDTO(
                entity.getId(),
                entity.getName(),
                entity.getImage(),
                entity.getContent()
        );
    }

    public void entityTestimonialRefreshValues(Testimonial entity, TestimonialDTO dto){
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        entity.setImage(dto.getImage());
    }
}
