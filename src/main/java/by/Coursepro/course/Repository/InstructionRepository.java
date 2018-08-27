package by.Coursepro.course.Repository;

import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Entity.User;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction,Long> {
    List<Instruction> findAllByUser(User user);
    List<Instruction> findAllById(long id);
    Instruction findById(long id);
}
