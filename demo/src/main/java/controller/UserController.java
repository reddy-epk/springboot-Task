package controller;

import model.User;
import service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create_user")
    public String createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/get_users")
    public List<User> getUsers(@RequestParam(required = false) String mobNum,
                               @RequestParam(required = false) UUID userId,
                               @RequestParam(required = false) UUID managerId) {
        return userService.getUsers(mobNum, userId, managerId);
    }

    @PostMapping("/delete_user")
    public String deleteUser(@RequestParam(required = false) UUID userId,
                              @RequestParam(required = false) String mobNum) {
        return userService.deleteUser(userId, mobNum);
    }

    @PostMapping("/update_user")
    public String updateUser(@RequestParam List<UUID> userIds,
                             @RequestBody User updateData) {
        return userService.updateUser(userIds, updateData);
    }
}