package by.Coursepro.course.Service.Transformer.UserTransform;

import by.Coursepro.course.DTO.UserDTO.UserAddDto;
import by.Coursepro.course.Entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddTransformer  {

    public User makeModel(UserAddDto userAddDTO){
    User user = new User();
    user.setFirstName(userAddDTO.getFirstName());
    user.setLastName(userAddDTO.getLastName());
    user.setUsername(userAddDTO.getUsername());
    user.setPassword(userAddDTO.getPassword());
    user.setEmail(userAddDTO.getEmail());
        return user;
    }
}
