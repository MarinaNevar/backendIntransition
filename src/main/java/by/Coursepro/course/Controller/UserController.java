package by.Coursepro.course.Controller;

import by.Coursepro.course.DTO.UserDTO.UserEditDto;
import by.Coursepro.course.DTO.UserDTO.UserListDto;
import by.Coursepro.course.Entity.Language;
import by.Coursepro.course.Entity.Theme;
import by.Coursepro.course.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {


    private final UserService userService;


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<UserListDto> findAll() {
        return this.userService.findAll();
    }

    @GetMapping("/getThemes")
    public List<Theme> getThemes() {
        return this.userService.getThemes();
    }

    @GetMapping("/getLanguages")
    public List<Language> getLanguages() {
        return this.userService.getLanguage();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WRITER') or hasRole('READER')")
    @PostMapping("/setUserLanguage/{username}")
    public void setUserLanguage(@PathVariable String username, @RequestBody Language language) {
        this.userService.setLanguage(username, language);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/setUserRole/{idUser}")
    public void setUserRole(@PathVariable Long idUser,@RequestBody String role){
        this.userService.setRole(idUser,role);
    }

    @GetMapping("/{username}")
    public UserEditDto findUserByUsername(@PathVariable String username) {
        return this.userService.findUserByUsername(username);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WRITER') or hasRole('READER')")
    @PostMapping("/setUserTheme/{username}")
    public void setUserTheme(@PathVariable String username, @RequestBody Theme theme) {
        this.userService.setTheme(username, theme);
    }

    @PostMapping("/edit")
    public void editUser(@RequestBody UserEditDto user) {
        this.userService.editUser(user);
    }

    @PostMapping("/editImage/{idUser}")
    public void editUserImage(@PathVariable Long idUser, @RequestParam("file") MultipartFile image) {
        this.userService.setUsersImage(idUser, image);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{idUser}")
    public void deleteUser(@PathVariable Long idUser) {
        this.userService.deleteUser(idUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/block/{idUser}", method = RequestMethod.POST, produces = "application/json")
    public void blockUser(@PathVariable Long idUser, @RequestBody Map<String, String> blockStatus) {
        boolean blockedStatus = Boolean.parseBoolean(blockStatus.get("blocked"));
        this.userService.blockUser(idUser, blockedStatus);
    }
    @RequestMapping("/getImage/{username}")
    public String getImage(@PathVariable String username) {
        return this.userService.getImage(username);
    }

    @GetMapping("/unique/{username}")
    public boolean uniqueUsername(@PathVariable String username) {
        return this.userService.uniqueUsername(username);
    }


}
