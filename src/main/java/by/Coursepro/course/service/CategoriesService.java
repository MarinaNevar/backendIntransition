package by.Coursepro.course.service;

import by.Coursepro.course.entity.Category;
import by.Coursepro.course.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoriesService {

    private final CategoryRepository categoryRepository;

    public List<Category> getAllCategories() {
        return this.categoryRepository.findAll();
    }

    public Set<Category> getCategories(Set<Category>categories){
        Set<Category> categorySet = new HashSet<>();
        for (Category category: categories){
            Category dbcat = this.categoryRepository.findById((long) category.getId());
            categorySet.add(dbcat);
        }
        return categorySet;
    }
}
