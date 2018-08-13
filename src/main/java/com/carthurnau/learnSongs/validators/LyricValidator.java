package com.carthurnau.learnSongs.validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.carthurnau.learnSongs.models.Lyric;


@Component

public class LyricValidator implements Validator{

    @Override
    public boolean supports(Class<?> clazz) {
        return Lyric.class.equals(clazz);
    }

	@Override
	public void validate(Object target, Errors errors) {
		
		Lyric lyric = (Lyric) target;
		
	}
	

}
