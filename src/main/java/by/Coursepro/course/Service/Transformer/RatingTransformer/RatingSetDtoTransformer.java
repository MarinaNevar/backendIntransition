package by.Coursepro.course.Service.Transformer.RatingTransformer;

import by.Coursepro.course.DTO.RatingDTO.RatingSetDto;
import by.Coursepro.course.Entity.Rating;
import by.Coursepro.course.Repository.InstructionRepository;
import by.Coursepro.course.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RatingSetDtoTransformer {
    private final UserRepository userRepository;
    private final InstructionRepository instructionRepository;


    public Rating makeModel(RatingSetDto ratingSetDto){
        Rating rating = new Rating();
        rating.setUser(userRepository.findByUsername(ratingSetDto.getUsername()));
        rating.setInstruction(instructionRepository.findById(ratingSetDto.getIdPost()));
        rating.setValue(ratingSetDto.getRating());
        return rating;
    }
}
