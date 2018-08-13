package com.carthurnau.learnSongs.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.carthurnau.learnSongs.models.Song;


@Component
public class SongValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Song.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		
		Song song = (Song) target;
		
	}

}
