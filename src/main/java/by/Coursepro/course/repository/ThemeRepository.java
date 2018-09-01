package by.Coursepro.course.repository;

import by.Coursepro.course.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
    Theme findById(long id);
}
