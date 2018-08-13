package com.carthurnau.learnSongs.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.carthurnau.learnSongs.models.User;


@Component
public class UserValidator implements Validator {
    
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirmation", "Match");
        }  else {
        	System.out.println("password does not match confirmation");
        }
    }
}