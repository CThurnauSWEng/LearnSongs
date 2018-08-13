package com.carthurnau.learnSongs.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.carthurnau.learnSongs.models.Language;

@Component
public class LanguageValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Language.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		
		Language language = (Language) target;
		
	}

}
