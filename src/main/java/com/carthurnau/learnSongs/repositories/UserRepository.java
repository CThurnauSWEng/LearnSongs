package com.carthurnau.learnSongs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carthurnau.learnSongs.models.User;

public interface UserRepository extends CrudRepository<User, Long>{


	List<User> findByEmail(String email);
	
	List<User> findAll();
	
	
}
