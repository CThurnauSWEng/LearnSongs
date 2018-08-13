package com.carthurnau.learnSongs.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.models.Lyric;
import com.carthurnau.learnSongs.repositories.LyricRepository;

@Service
public class LyricService {
	
	private final LyricRepository lyricRepository;
	
	public LyricService(LyricRepository lyricRepository) {
		this.lyricRepository = lyricRepository;
	}
	
	public List<Lyric> findAll(){
		return lyricRepository.findAll();
	}

}
