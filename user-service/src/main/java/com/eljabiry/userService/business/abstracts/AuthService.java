package com.eljabiry.userService.business.abstracts;

import com.eljabiry.userService.dto.UserAuthenticationResponseDto;
import com.eljabiry.userService.dto.UserLoginRequestDto;

public interface AuthService {

    UserAuthenticationResponseDto login(UserLoginRequestDto userLoginRequestDto);

}
