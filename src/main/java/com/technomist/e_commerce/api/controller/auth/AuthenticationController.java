package com.technomist.e_commerce.api.controller.auth;

import com.technomist.e_commerce.api.exception.UserAlreadyExistsException;
import com.technomist.e_commerce.api.model.LoginBody;
import com.technomist.e_commerce.api.model.LoginResponse;
import com.technomist.e_commerce.api.model.RegistrationBody;
import com.technomist.e_commerce.model.AuthUser;
import com.technomist.e_commerce.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<HttpStatus> registerUser(@Valid @RequestBody RegistrationBody registrationBody){
        try{
            userService.registerUser(registrationBody);
            return ResponseEntity.ok().build();

        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) {
        String jwt = userService.login(loginBody);
        if (jwt == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setJwt(jwt);
            return ResponseEntity.ok(loginResponse);
        }
    }

    @GetMapping("/me")
    public AuthUser getLoggedInUserProfile(@AuthenticationPrincipal AuthUser user) {
        return user;
    }

}
