package ru.indivio.market.services;

import ru.indivio.market.entites.Category;
import ru.indivio.market.repositories.CategoryRepository;
import ru.indivio.market.utils.CategoryNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return (List<Category>)categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return category.get();
        }
        return null;
    }
    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }
    public Category saveOrUpdate(Category category) {
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new CategoryNotFoundException("Category with id = " + id + " not found");
        }
        categoryRepository.delete(category.get());
    }

    public void addCategory(String name){
        Category category = new Category();
        category.setTitle(name);
        categoryRepository.save(category);
    }

    public boolean isCategoryWithTitleExists(String title) {
        return categoryRepository.findOneByTitle(title) != null;
    }

}
