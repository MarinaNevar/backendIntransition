package by.Coursepro.course.Repository;

import by.Coursepro.course.Entity.Instruction;
import by.Coursepro.course.Entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step,Long> {
    List<Step> findAllByInstruction(Long instruction);
    Step findById(long id);
}
