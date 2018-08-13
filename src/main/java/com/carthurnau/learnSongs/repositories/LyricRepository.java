package com.carthurnau.learnSongs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carthurnau.learnSongs.models.Lyric;

public interface LyricRepository extends CrudRepository<Lyric, Long>{
	
	List<Lyric> findAll();

}
