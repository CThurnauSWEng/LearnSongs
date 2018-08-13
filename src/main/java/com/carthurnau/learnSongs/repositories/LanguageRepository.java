package com.carthurnau.learnSongs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carthurnau.learnSongs.models.Language;

public interface LanguageRepository extends CrudRepository<Language, Long>{
	
	List<Language> findAll();

}
