package service;

import model.User;
import repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(User user) {
        // Validate full_name
        if (user.getFull_name() == null || user.getFull_name().isEmpty()) {
            return "Full name must not be empty";
        }

        // Validate mob_num
        if (user.getMob_num() == null || user.getMob_num().isEmpty()) {
            return "Mobile number must not be empty";
        }

        // Adjust mob_num if necessary
        if (user.getMob_num().startsWith("+91")) {
            user.setMob_num(user.getMob_num().substring(3));
        } else if (user.getMob_num().startsWith("0")) {
            user.setMob_num(user.getMob_num().substring(1));
        }

        // Validate pan_num
        if (user.getPan_num() == null || user.getPan_num().isEmpty()) {
            return "PAN number must not be empty";
        }
        user.setPan_num(user.getPan_num().toUpperCase());

        // Validate manager_id
        if (user.getManager_id() != null) {
            Optional<User> manager = userRepository.findById(user.getManager_id());
            if (manager.isEmpty()) {
                return "Invalid manager_id";
            }
        }

        // Set default values
        user.setUser_id(UUID.randomUUID());
        user.setCreated_at(LocalDateTime.now());
        user.setUpdated_at(null);
        user.setIs_active(true);

        userRepository.save(user);

        return "User created successfully";
    }

    public List<User> getUsers(String mobNum, UUID userId, UUID managerId) {
        if (mobNum != null) {
            return userRepository.findByMobNum(mobNum);
        } else if (userId != null) {
            return userRepository.findByUserIdOrMobNum(userId, null);
        } else if (managerId != null) {
            return userRepository.findByManagerId(managerId);
        } else {
            return userRepository.findAll();
        }
    }

    public String deleteUser(UUID userId, String mobNum) {
        Optional<User> user;
        if (userId != null) {
            user = userRepository.findById(userId);
        } else {
            List<User> users = userRepository.findByMobNum(mobNum);
            if (users.isEmpty()) {
                return "User not found";
            }
            user = Optional.of(users.get(0));
        }

        if (user.isPresent()) {
            userRepository.delete(user.get());
            return "User deleted successfully";
        } else {
            return "User not found";
        }
    }

    public String updateUser(List<UUID> userIds, User updateData) {
        // Validate update_data
        if (updateData.getFull_name() != null || updateData.getMob_num() != null || updateData.getPan_num() != null) {
            return "Only manager_id can be updated in bulk";
        }

        // Validate manager_id
        if (updateData.getManager_id() != null) {
            Optional<User> manager = userRepository.findById(updateData.getManager_id());
            if (manager.isEmpty()) {
                return "Invalid manager_id";
            }
        }

        for (UUID userId : userIds) {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                User existingUser = user.get();

                // Update manager_id
                if (existingUser.getManager_id() != null && updateData.getManager_id() != null) {
                    existingUser.setIs_active(false);
                    userRepository.save(existingUser);

                    User newUser = new User();
                    newUser.setUser_id(UUID.randomUUID());
                    newUser.setFull_name(existingUser.getFull_name());
                    newUser.setMob_num(existingUser.getMob_num());
                    newUser.setPan_num(existingUser.getPan_num());
                    newUser.setManager_id(updateData.getManager_id());
                    newUser.setCreated_at(existingUser.getCreated_at());
                    newUser.setUpdated_at(LocalDateTime.now());
                    newUser.setIs_active(true);

                    userRepository.save(newUser);
                } else {
                    existingUser.setManager_id(updateData.getManager_id());
                    existingUser.setUpdated_at(LocalDateTime.now());
                    userRepository.save(existingUser);
                }
            }
        }

        return "Users updated successfully";
    }
}