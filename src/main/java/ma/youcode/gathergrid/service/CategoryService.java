package ma.youcode.gathergrid.service;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import ma.youcode.gathergrid.domain.Category;
import ma.youcode.gathergrid.repositories.CategoryRepository;
import ma.youcode.gathergrid.utils.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Inject

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public CategoryService() {
    }


    public Response<Category> createCategory(Category category){
        Response<Category> categoryResponse = new Response<>();
        ArrayList<Error> errors = new ArrayList<Error>();

        if (category.getName().isEmpty() || category.getDescription().isEmpty()){
            errors.add(new Error("All Fields are required"));
            categoryResponse.setError(errors);
        }else {
            categoryRepository.save(category);
            categoryResponse.setResult(category);
        }
        return categoryResponse;
    }

    public Response<List<Category>> getAllCategories(){
        Response<List<Category>> categoryResponse = new Response<>();
        categoryResponse.setResult(categoryRepository.findAll());
        return categoryResponse;
    }

    public Optional<Category> getCategoryById(long id){
        return Optional.ofNullable(categoryRepository.findById(id));
    }

    public Response<Category> deleteCategory(long id){
        Response<Category> categoryResponse = new Response<Category>();
        Optional<Category> optionalCategory = getCategoryById(id);
        if (optionalCategory.isPresent()){
            categoryRepository.delete(optionalCategory.get());
        }else {
            categoryResponse.setError(List.of(new Error("No Category Found")));
        }
        return categoryResponse ;
    }


}
