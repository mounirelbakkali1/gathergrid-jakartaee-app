package ma.youcode.gathergrid.repositories;

import ma.youcode.gathergrid.domain.Category;

import java.util.List;

public interface CategoryRepository {
    public Category findById(long id);
    public void save(Category category);
    public void update(Category category);
    public void delete(Category category);
    public List<Category> findAll();
}
