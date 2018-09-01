package by.Coursepro.course.repository;

import by.Coursepro.course.entity.Step;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StepRepository extends JpaRepository<Step,Long> {
    List<Step> findAllByInstruction(Long instruction);
    Step findById(long id);


}
