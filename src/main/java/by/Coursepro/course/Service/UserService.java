package by.Coursepro.course.Service;

import by.Coursepro.course.Constants.Abbreviation;
import by.Coursepro.course.DTO.ErrorDto;
import by.Coursepro.course.DTO.UserDTO.UserAddDto;
import by.Coursepro.course.DTO.UserDTO.UserEditDto;
import by.Coursepro.course.DTO.UserDTO.UserListDto;
import by.Coursepro.course.Entity.*;
import by.Coursepro.course.Repository.LanguageRepository;
import by.Coursepro.course.Repository.ThemeRepository;
import by.Coursepro.course.Repository.UserRepository;
import by.Coursepro.course.Service.Transformer.UserTransform.UserAddTransformer;
import by.Coursepro.course.Service.Transformer.UserTransform.UserEditTranformer;
import by.Coursepro.course.Service.Transformer.UserTransform.UserListTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;
    private final LanguageRepository languageRepository;
    private final UserListTransformer userListTransformer;
    private final UserAddTransformer userAddTransformer;
    private final UserEditTranformer userEditTranformer;
    private final CloudStorageService cloudStorageService;

    @Transactional(readOnly = true)
    public List<UserListDto> findAll() {
        List<User> users = userRepository.findAllExisted();
        List<UserListDto> userDtoList = new ArrayList<>();
        for (User user : users) {
            UserListDto dto = userListTransformer.makeDto(user);
            userDtoList.add(dto);
        }
        return userDtoList;
    }

    public UserEditDto findUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return userEditTranformer.makeDto(user);
    }

    public List<Theme> getThemes(){
        List<Theme> theme = themeRepository.findAll();
        return theme;
    }

    public List<Language> getLanguage(){
        List<Language> languages = languageRepository.findAll();
        return languages;
    }
    public void deleteUser(Long id){
        User deleteUser = userRepository.findById((long) id);
        deleteUser.setDeleted(true);
        userRepository.save(deleteUser);
    }

    public void blockUser(Long id, boolean blockStatus){
        User blockedUser = userRepository.findById((long) id);
        blockedUser.setBlocked(blockStatus);
        userRepository.save(blockedUser);
    }
    public void editUser(UserEditDto user){
        User oldUser = userRepository.findById((long) user.getId());
        User editedUser = userEditTranformer.mergeUserEdit(oldUser, user);
        userRepository.save(editedUser);
    }

    public void setLanguage(String username, Language language) {
        User user = userRepository.findByUsername(username);
        user.setLanguage(language);
        userRepository.save(user);
    }

    public void setUsersImage(Long userId, MultipartFile image) {
        String publicUrl = this.cloudStorageService.uploadImage(image);
        User user = this.userRepository.findById((long)userId);
        user.setAvatar(publicUrl);
        userRepository.save(user);
    }

    public void setTheme(String username, Theme theme){
        User user = userRepository.findByUsername(username);
        user.setTheme(theme);
        userRepository.save(user);
    }
    public void setRole(long id, String role){
        User user = userRepository.findById(id);
        user.setRole(Role.valueOf(role));
        userRepository.save(user);
    }


    public ErrorDto addUser(UserAddDto userAddDTO){
        User user = userAddTransformer.makeModel(userAddDTO);
        if (!this.uniqueUsername(user.getUsername())) {
            return new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_ISNT_UNIQUE_USERNAME);
        }
        if (this.isEmailExsists(user.getEmail())) {
            return new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_ISNT_UNIQUE_EMAIL);
        }
        setDefaultSettings(user);
        userRepository.save(user);
        return new ErrorDto(Abbreviation.SUCCESS, Abbreviation.SUCCESS_REGISTRATION);
    }
    public Boolean userIsActive(String username){
        return userRepository.findByUsername(username).getIsActive();
    }

    public Boolean uniqueUsername(String username){
        return userRepository.findByUsername(username)==null;
    }
    public Boolean isEmailExsists(String email){
        return userRepository.findByEmail(email)!=null;
    }

    public Boolean userIsBlocked(String username) {
        return userRepository.findByUsername(username).isBlocked();
    }

    public Boolean userIsDeleted(String username) {
        return userRepository.findByUsername(username).isDeleted();
    }

    private Boolean isUserExists(User user){
        return userRepository.findByUsername(user.getUsername())!=null;
    }
    public Boolean isNull(User user){
        return userRepository.findByUsername(user.getUsername())==null;
    }
    public String getImage(String username){
        return userRepository.findByUsername(username).getAvatar();
    }

    private void encoder(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(user.getPassword()));
    }


    private void setDefaultSettings(User user) {
        encoder(user);
        user.setIsActive(true);
        user.setBlocked(false);
        user.setDeleted(false);
        user.setLanguage(languageRepository.findById((long)1));
        user.setTheme(themeRepository.findById((long)1));
        user.setRole(Role.ROLE_WRITER);
        user.setAvatar(Abbreviation.DEFAULT_IMAGE);
        user.setAmountLike(0);
        user.setComments(new HashSet<>());
        user.setInstructions(new HashSet<>());
        user.setRatings(new HashSet<>());
        user.setLikes(new HashSet<>());
    }
}
