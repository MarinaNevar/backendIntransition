package by.Coursepro.course.repository;

import by.Coursepro.course.entity.Comment;
import by.Coursepro.course.entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByInstruction(Instruction instruction);
    Comment findById(long id);
}
