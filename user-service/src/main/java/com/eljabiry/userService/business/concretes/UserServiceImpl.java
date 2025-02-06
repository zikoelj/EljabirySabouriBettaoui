package com.eljabiry.userService.business.concretes;

import com.eljabiry.userService.business.abstracts.ClaimService;
import com.eljabiry.userService.business.abstracts.UserService;
import com.eljabiry.userService.entity.Claim;
import com.eljabiry.userService.entity.User;
import com.eljabiry.userService.dto.UserRegisterRequestDto;
import com.eljabiry.userService.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ClaimService claimService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Boolean isUserExist(Long userId) {

        User user = userRepository.findUserByUserId(userId);

        return user != null;
    }

    @Override
    public void addUser(UserRegisterRequestDto userRegisterRequestDto) {

        Claim claim = claimService.getClaimByClaimName("CUSTOMER");

        User user = User.builder()
                .email(userRegisterRequestDto.getEmail())
                .password(passwordEncoder.encode(userRegisterRequestDto.getPassword()))
                .fullName(userRegisterRequestDto.getCustomerName())
                .claim(claim)
                .build();
        userRepository.save(user);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public boolean isUserCustomer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().allMatch(
                a -> a.getAuthority().equals("ROLE_CUSTOMER")
        );
    }

    @Override
    public boolean isUserAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().anyMatch(
                a -> a.getAuthority().equals("ROLE_ADMIN")
        );
    }
}
