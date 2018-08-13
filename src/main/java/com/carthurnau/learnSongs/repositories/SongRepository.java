package com.carthurnau.learnSongs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.carthurnau.learnSongs.models.Song;

public interface SongRepository extends CrudRepository<Song, Long>{
	
	List<Song> findAll();
	

}
