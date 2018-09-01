package by.Coursepro.course.service;

import by.Coursepro.course.constants.Abbreviation;
import by.Coursepro.course.dto.ErrorDto;
import by.Coursepro.course.dto.JsonException;
import by.Coursepro.course.dto.login.LoginRequest;
import by.Coursepro.course.dto.login.LoginResponse;
import by.Coursepro.course.dto.user.UserAdd;
import by.Coursepro.course.dto.user.UserEdit;
import by.Coursepro.course.dto.user.UserList;
import by.Coursepro.course.entity.*;
import by.Coursepro.course.repository.LanguageRepository;
import by.Coursepro.course.repository.ThemeRepository;
import by.Coursepro.course.repository.UserRepository;
import by.Coursepro.course.security.model.JwtUserDetails;
import by.Coursepro.course.security.service.AuthenticationHelper;
import by.Coursepro.course.transformer.user.UserAddTransformer;
import by.Coursepro.course.transformer.user.UserEditTranformer;
import by.Coursepro.course.transformer.user.UserListTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private final AuthenticationHelper authenticationHelper;
    private final AuthenticationManager authenticationManager;

    public List<UserList> findAll() {
        List<User> users = userRepository.findAllExisted();
        return users.stream().map(userListTransformer::makeDto).collect(Collectors.toList());
    }

    public UserEdit findUserByUsername(String username){
        User user = userRepository.findByUsername(username);
        return userEditTranformer.makeDto(user);
    }

    public List<Theme> getThemes(){
        return themeRepository.findAll();
    }

    public List<Language> getLanguage(){
        return languageRepository.findAll();
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
    public void editUser(UserEdit user){
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

    public ErrorDto addUser(UserAdd userAdd){
        User user = userAddTransformer.makeModel(userAdd);
        ErrorDto errorDto = checkUnique(user);
        if (errorDto != null) {
            return errorDto;
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

    private ErrorDto checkUnique(User user) {
        if (!this.uniqueUsername(user.getUsername())) {
            return new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_ISNT_UNIQUE_USERNAME);
        }
        if (this.isEmailExsists(user.getEmail())) {
            return new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_ISNT_UNIQUE_EMAIL);
        }
        return null;
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
        user.setInstructions(new ArrayList<>());
        user.setRatings(new HashSet<>());
        user.setLikes(new HashSet<>());
    }

    public LoginResponse login(final LoginRequest loginRequest) {
        try {
            String username = Optional.ofNullable(loginRequest.getUsername())
                    .orElseThrow(() -> new BadCredentialsException("Username should be passed."));
            String password = Optional.ofNullable(loginRequest.getPassword())
                    .orElseThrow(() -> new BadCredentialsException("Password should be passed."));
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username,
                    password);
            if(uniqueUsername(username)) {
                return new LoginResponse(null, new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_UNIQUE_USERNAME),
                        null);
            }
            if (userIsDeleted(loginRequest.getUsername())) {
                return new LoginResponse(null, new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_USER_DELETED),
                        null);
            }
            if (!userIsActive(loginRequest.getUsername())) {
                return new LoginResponse(null, new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_USER_ISNT_ACTIVE),
                        null);
            }
            if (userIsBlocked(loginRequest.getUsername())) {
                return new LoginResponse(null, new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_USER_BLOCKED),
                        null);
            }
            final Authentication authResult = this.authenticationManager.authenticate(authRequest);
            if (authResult.isAuthenticated()) {
                JwtUserDetails userDetails = (JwtUserDetails) authResult.getPrincipal();
                User user = userRepository.findById((long)userDetails.getId());
                String token = this.authenticationHelper.generateToken(userDetails.getId());
                return new LoginResponse(token,
                        new ErrorDto(Abbreviation.SUCCESS, Abbreviation.SUCCESS_AUTHENTICATION),
                        user);
            } else {
                throw new JsonException("Authentication failed.");
            }
        } catch (BadCredentialsException exception) {
            return new LoginResponse(null, new ErrorDto(Abbreviation.ERROR, Abbreviation.ERROR_INVALID_PARAMETRS),
                    null);
        }
    }
}
