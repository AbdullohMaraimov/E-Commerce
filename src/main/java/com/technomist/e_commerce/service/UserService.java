package com.technomist.e_commerce.service;

import com.technomist.e_commerce.api.exception.UserAlreadyExistsException;
import com.technomist.e_commerce.api.model.LoginBody;
import com.technomist.e_commerce.api.model.RegistrationBody;
import com.technomist.e_commerce.model.AuthUser;
import com.technomist.e_commerce.model.repository.AuthUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthUserRepository authUserRepository;
    private final EncryptionService encryptionService;
    private final JWTService jwtService;

    public AuthUser registerUser(RegistrationBody registrationBody) throws UserAlreadyExistsException {

        if (authUserRepository.findAuthUserByEmailIgnoreCase(registrationBody.getEmail()).isPresent()
                || authUserRepository.findAuthUserByUsernameIgnoreCase(registrationBody.getUsername()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        AuthUser authUser = new AuthUser();
        authUser.setEmail(registrationBody.getEmail());
        authUser.setFirstName(registrationBody.getFirstName());
        authUser.setLastName(registrationBody.getLastName());
        authUser.setUsername(registrationBody.getUsername());

        authUser.setPassword(encryptionService.encryptPassword(registrationBody.getPassword()));

        return authUserRepository.save(authUser);
    }

    public String login(LoginBody loginBody) {
        Optional<AuthUser> opUser = authUserRepository.findAuthUserByUsernameIgnoreCase(loginBody.getUsername());

        if (opUser.isPresent()) {
            AuthUser user = opUser.get();
            if (encryptionService.verifyPassword(loginBody.getPassword(), user.getPassword())) {
                return jwtService.generateJWT(user);
            }
        }
        return null;
    }
}
