package by.Coursepro.course.Repository;

import by.Coursepro.course.Entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme,Long> {
    Theme findById(long id);
}
