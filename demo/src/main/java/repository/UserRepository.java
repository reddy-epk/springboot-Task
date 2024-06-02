package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findByMobNum(String mobNum);
    List<User> findByUserIdOrMobNum(UUID userId, String mobNum);
    List<User> findByManagerId(UUID managerId);
}