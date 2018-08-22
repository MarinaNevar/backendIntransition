package by.Coursepro.course.Repository;

import by.Coursepro.course.Entity.Comment;
import by.Coursepro.course.Entity.Instruction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findAllByInstruction(Instruction instruction);
    Comment findById(long id);
}
