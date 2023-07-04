package com.springstudy.study.homework.DZ7.controller.mvc;

import com.springstudy.study.homework.DZ7.constants.Errors;
import com.springstudy.study.homework.DZ7.dto.UserDTO;
import com.springstudy.study.homework.DZ7.exception.MyDeleteException;
import com.springstudy.study.homework.DZ7.service.UserService;
import com.springstudy.study.homework.DZ7.service.userdetails.CustomUserDetails;
import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.server.PathParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Objects;
import java.util.UUID;

import static com.springstudy.study.homework.DZ7.constants.UserRolesConstants.ADMIN;

@Controller
@Slf4j
@RequestMapping("/users")
public class MVCUserController {

    private final UserService userService;

    public MVCUserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") UserDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой e-mail уже существует");
            return "registration";
        }
        userService.create(userDTO);
        return "redirect:login";
    }

    @GetMapping("/remember-password")
    public String rememberPassword() {
        return "users/rememberPassword";
    }

    @PostMapping("/remember-password")
    public String rememberPassword(@ModelAttribute("changePasswordForm") UserDTO userDTO) {
        userDTO = userService.getUserByEmail(userDTO.getEmail());
        if (Objects.isNull(userDTO)) {
            return "Error!";
        } else {
            userService.sendChangePasswordEmail(userDTO);
            return "redirect:/login";
        }
    }

    @GetMapping("/change-password")
    public String changePassword(@PathParam(value = "uuid") String uuid,
                                 Model model) {
        model.addAttribute("uuid", uuid);
        return "users/changePassword";
    }

//    @PostMapping("/change-password")
//    public String changePassword(@PathParam(value = "uuid") String uuid,
//                                 @ModelAttribute("changePasswordForm") UserDTO userDTO) {
//        userService.changePassword(uuid, userDTO.getPassword());
//        return "redirect:/login";
//    }

    @GetMapping("/change-password/user")
    public String changePassword(Model model) {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO userDTO = userService.getOne(Long.valueOf(customUserDetails.getUserId()));
        UUID uuid = UUID.randomUUID();
        userDTO.setChangePasswordToken(uuid.toString());
        userService.update(userDTO);
        model.addAttribute("uuid", uuid);
        return "users/changePassword";
    }

    @GetMapping("/profile/{id}")
    public String userProfile(@PathVariable Integer id,
                              @ModelAttribute(value = "exception") String exception,
                              Model model) throws AuthException {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!Objects.isNull(customUserDetails.getUserId())) {
            if (!ADMIN.equalsIgnoreCase(customUserDetails.getUsername())) {
                if (!id.equals(customUserDetails.getUserId())) {
                    throw new AuthException(HttpStatus.FORBIDDEN + ": " + Errors.Users.USER_FORBIDDEN_ERROR);
                }
            }
        }
        model.addAttribute("user", userService.getOne(Long.valueOf(id)));
        model.addAttribute("exception", exception);
        return "profile/viewProfile";
    }

    @GetMapping("/profile/update/{id}")
    public String updateProfile(@PathVariable Integer id,
                                Model model) throws AuthException {
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!id.equals(customUserDetails.getUserId())) {
            throw new AuthException(HttpStatus.FORBIDDEN + ": " + Errors.Users.USER_FORBIDDEN_ERROR);
        }
        model.addAttribute("userForm", userService.getOne(Long.valueOf(id)));
        return "profile/updateProfile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@ModelAttribute("userForm") UserDTO userDTOFromUpdateForm,
                                BindingResult bindingResult) {
        UserDTO userEmailDuplicated = userService.getUserByEmail(userDTOFromUpdateForm.getEmail());
        UserDTO foundUser = userService.getOne(userDTOFromUpdateForm.getId());
        if (userEmailDuplicated != null && !Objects.equals(userEmailDuplicated.getEmail(), foundUser.getEmail())) {
            bindingResult.rejectValue("email", "error.email", "Такой email уже существует");
            return "profile/updateProfile";
        }
        foundUser.setFirstName(userDTOFromUpdateForm.getFirstName());
        foundUser.setLastName(userDTOFromUpdateForm.getLastName());
        foundUser.setMiddleName(userDTOFromUpdateForm.getMiddleName());
        foundUser.setEmail(userDTOFromUpdateForm.getEmail());
        foundUser.setBirthDate(userDTOFromUpdateForm.getBirthDate());
        foundUser.setPhone(userDTOFromUpdateForm.getPhone());
        foundUser.setAddress(userDTOFromUpdateForm.getAddress());
        userService.update(foundUser);
        return "redirect:/users/profile/" + userDTOFromUpdateForm.getId();
    }

    @GetMapping("/list")
    public String listAllUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                               @RequestParam(value = "size", defaultValue = "10") int pageSize,
                               Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Direction.ASC, "login"));
        Page<UserDTO> userPage = userService.listAll(pageRequest);
        model.addAttribute("users", userPage);
        return "users/viewAllUsers";
    }

    @GetMapping("/add-librarian")
    public String addLibrarianPage(Model model) {
        model.addAttribute("userForm", new UserDTO());
        return "users/addLibrarian";
    }

    @PostMapping("/add-librarian")
    public String addLibrarian(@ModelAttribute("userForm") UserDTO userDTO,
                               BindingResult bindingResult) {
        if (userDTO.getLogin().equalsIgnoreCase(ADMIN) || userService.getUserByLogin(userDTO.getLogin()) != null) {
            bindingResult.rejectValue("login", "error.login", "Такой логин уже существует");
            return "registration";
        }
        if (userService.getUserByEmail(userDTO.getEmail()) != null) {
            bindingResult.rejectValue("email", "error.email", "Такой email уже существует");
            return "registration";
        }
        userService.createLibrarian(userDTO);
        return "redirect:/users/list";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) throws MyDeleteException {
        userService.deleteSoft(id);
        return "redirect:/users/list";
    }

    @GetMapping("/restore/{id}")
    public String restore(@PathVariable Long id) {
        userService.restore(id);
        return "redirect:/users/list";
    }

    @PostMapping("/search")
    public String searchUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                              @RequestParam(value = "size", defaultValue = "5") int size,
                              @ModelAttribute("userSearchForm") UserDTO userDTO,
                              Model model) {
        PageRequest pageRequest = PageRequest.of(page - 1, size, Sort.by(Sort.Direction.ASC, "first_name"));
        model.addAttribute("users", userService.findUsers(userDTO, pageRequest));
        return "users/viewAllUsers";
    }

    @ExceptionHandler({AuthException.class})
    public RedirectView handleError(HttpServletRequest request,
                                    Exception ex,
                                    RedirectAttributes redirectAttributes) {
        log.error("Запрос " + request.getRequestURL() + " вызвал ошибку " + ex.getMessage());
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redirectAttributes.addFlashAttribute("exception", ex.getMessage());
        return new RedirectView("/users/profile/" + customUserDetails.getUserId(), true);
    }

}
