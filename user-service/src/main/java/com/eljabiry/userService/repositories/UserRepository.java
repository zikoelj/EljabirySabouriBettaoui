package com.eljabiry.userService.repositories;

import com.eljabiry.userService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserId(Long userId);

    User findUserByEmail(String email);
}
