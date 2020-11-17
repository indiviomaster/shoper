package ru.indivio.market.controllers;


import org.springframework.http.HttpStatus;
import ru.indivio.market.entites.Category;
import ru.indivio.market.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("")
    public String categoriesPage(Model model) {
        List<Category> allCats = categoryService.getAllCategories();
        model.addAttribute("categories", allCats);
        return "categories-page";
    }

    @GetMapping("/add")
    public String addCategoryForm(Model model) {

        model.addAttribute("category", new Category());
        return "add-category";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable(name = "id") Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            category = new Category();
            category.setId(0L);
        }
        model.addAttribute("category", category);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "edit-category";
    }

    @PostMapping("/edit")
    public String processCategoryAddForm(@Valid @ModelAttribute("category") Category category, BindingResult theBindingResult, Model model) {
        if (category.getId() == 0 && categoryService.isCategoryWithTitleExists(category.getTitle())) {
            theBindingResult.addError(new ObjectError("category.title", "Категория с таким названием уже существует")); // todo не отображает сообщение
        }
        //проверка на ошибки
        if (theBindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "edit-category";
        }


        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }


}
