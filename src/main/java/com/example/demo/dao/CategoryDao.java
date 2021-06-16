package com.example.demo.dao;

import com.example.demo.model.Category;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Primary
public interface CategoryDao {
    public List<Category> getAllCategories();
    public Category getCategoryById(int id);
    public void addingCategory(Category c);
    public void updatingCategory(Category oldc, Category newc);
    public void removingCategory(int id);
}
