package by.Coursepro.course.transformer.user;

import by.Coursepro.course.dto.user.UserAdd;
import by.Coursepro.course.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAddTransformer  {

    public User makeModel(UserAdd userAdd){
    User user = new User();
    user.setFirstName(userAdd.getFirstName());
    user.setLastName(userAdd.getLastName());
    user.setUsername(userAdd.getUsername());
    user.setPassword(userAdd.getPassword());
    user.setEmail(userAdd.getEmail());
        return user;
    }
}
