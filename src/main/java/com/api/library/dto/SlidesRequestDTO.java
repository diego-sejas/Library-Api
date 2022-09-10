package com.api.library.dto;

import com.api.library.domain.model.Organization;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SlidesRequestDTO {
    @NotEmpty
    private String nameImage;
    private String imageUrl;
    private String text;
    private Integer orderSlides;
    private Organization organizations;
    @NotEmpty
    private String base64Value;
}
