package by.Coursepro.course.controllers;

import by.Coursepro.course.dto.ErrorDto;
import by.Coursepro.course.dto.login.LoginRequest;
import by.Coursepro.course.dto.login.LoginResponse;
import by.Coursepro.course.dto.user.UserAdd;
import by.Coursepro.course.dto.user.UserEdit;
import by.Coursepro.course.dto.user.UserList;
import by.Coursepro.course.entity.Language;
import by.Coursepro.course.entity.Theme;
import by.Coursepro.course.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {


    private final UserService userService;

    @PostMapping("/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }

    @PostMapping
    public ErrorDto addUser(@RequestBody UserAdd userAdd) {
        return userService.addUser(userAdd);
    }

   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    List<UserList> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/{username}")
    UserEdit findUserByUsername(@PathVariable String username) {
        return this.userService.findUserByUsername(username);
    }

    @PutMapping
    void editUser(@RequestBody UserEdit user) {
        this.userService.editUser(user);
    }

    @PostMapping("/{idUser}/images")
    void editUserImage(@PathVariable Long idUser, @RequestParam("file") MultipartFile image) {
        this.userService.setUsersImage(idUser, image);
    }

    @GetMapping("/getThemes")
    public List<Theme> getThemes() {
        return this.userService.getThemes();
    }

    @GetMapping("/getLanguages")
    public List<Language> getLanguages() {
        return this.userService.getLanguage();
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WRITER') or hasRole('ROLE_READER')")
    @PostMapping("/setUserLanguage/{username}")
    public void setUserLanguage(@PathVariable String username, @RequestBody Language language) {
        this.userService.setLanguage(username, language);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/setUserRole/{idUser}")
    public void setUserRole(@PathVariable Long idUser,@RequestBody String role){
        this.userService.setRole(idUser,role);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_WRITER') or hasRole('ROLE_READER')")
    @PostMapping("/setUserTheme/{username}")
    public void setUserTheme(@PathVariable String username, @RequestBody Theme theme) {
        this.userService.setTheme(username, theme);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{idUser}")
    void deleteUser(@PathVariable Long idUser) {
        this.userService.deleteUser(idUser);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value = "/{idUser}/block")
    public void blockUser(@PathVariable Long idUser, @RequestBody Map<String, String> blockStatus) {
        boolean blockedStatus = Boolean.parseBoolean(blockStatus.get("blocked"));
        this.userService.blockUser(idUser, blockedStatus);
    }
    @RequestMapping("/{username}/images")
    String getImage(@PathVariable String username) {
        return this.userService.getImage(username);
    }

    @GetMapping("/{username}/unique")
    boolean uniqueUsername(@PathVariable String username) {

        return this.userService.uniqueUsername(username);
    }


}
