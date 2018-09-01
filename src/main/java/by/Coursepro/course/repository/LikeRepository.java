package by.Coursepro.course.repository;

import by.Coursepro.course.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
    Like findById(long id);
}
