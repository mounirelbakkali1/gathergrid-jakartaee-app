package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ma.youcode.gathergrid.repositories.CategoryRepository;

@RequestScoped
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Inject

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}
