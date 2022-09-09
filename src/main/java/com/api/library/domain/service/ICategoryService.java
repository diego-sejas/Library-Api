package com.api.library.domain.service;

import com.api.library.dto.CategoryDTO;
import com.api.library.dto.CategoryDtoName;
import com.api.library.dto.PageDTO;

import java.util.List;


public interface ICategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDto);
  
    List<CategoryDtoName> getAllCategories();

    PageDTO<CategoryDTO> getAllCategoriesPageable(Integer page);

    CategoryDTO getCategoryById(Long id);
  
    void deleteCategory(Long id);
  
    CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto);

}
