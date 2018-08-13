package com.carthurnau.learnSongs.services;

import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.repositories.LanguageRepository;

@Service
public class LanguageService {

	private final LanguageRepository languageRepository;
	
	public LanguageService(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}
}
