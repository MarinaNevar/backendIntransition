package by.Coursepro.course.repository;

import by.Coursepro.course.entity.Instruction;
import by.Coursepro.course.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionRepository extends JpaRepository<Instruction,Long> {
    List<Instruction> findAllByUser(User user);
    List<Instruction> findAllById(long id);
    Instruction findById(long id);
}
