package com.carthurnau.learnSongs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.models.Song;
import com.carthurnau.learnSongs.repositories.SongRepository;

@Service
public class SongService {
	
	private final SongRepository songRepository;
	
	public SongService(SongRepository songRepository) {
		this.songRepository = songRepository;
	}
	
	public List<Song> findAll(){
		return songRepository.findAll();
	}

}
