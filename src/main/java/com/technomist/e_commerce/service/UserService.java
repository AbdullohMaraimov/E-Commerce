package com.technomist.e_commerce.service;

import com.technomist.e_commerce.api.exception.UserAlreadyExistsException;
import com.technomist.e_commerce.api.model.RegistrationBody;
import com.technomist.e_commerce.model.AuthUser;
import com.technomist.e_commerce.model.repository.AuthUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AuthUserRepository authUserRepository;

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
        // todo encrypt password
        authUser.setPassword(registrationBody.getPassword());

        return authUserRepository.save(authUser);
    }

}
