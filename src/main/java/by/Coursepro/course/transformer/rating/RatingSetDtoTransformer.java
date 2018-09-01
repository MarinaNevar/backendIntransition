package by.Coursepro.course.transformer.rating;

import by.Coursepro.course.dto.rating.RatingSet;
import by.Coursepro.course.entity.Rating;
import by.Coursepro.course.repository.InstructionRepository;
import by.Coursepro.course.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingSetDtoTransformer {
    private final UserRepository userRepository;
    private final InstructionRepository instructionRepository;

    public Rating makeModel(RatingSet ratingSet){
        Rating rating = new Rating();
        rating.setUser(userRepository.findByUsername(ratingSet.getUsername()));
        rating.setInstruction(instructionRepository.findById(ratingSet.getIdInstruction()));
        rating.setValue(ratingSet.getRating());
        return rating;
    }
}
