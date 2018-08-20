package com.carthurnau.learnSongs.services;

import java.util.List;
import java.util.Optional;

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
	
	public Lyric createLyric(Lyric lyric) {
		return lyricRepository.save(lyric);
	}

	public Lyric updateLyric(Lyric lyric) {
		return lyricRepository.save(lyric);
	}

	public Lyric findById(Long id) {
		
		Optional<Lyric> optionalLyric = lyricRepository.findById(id);
		
		if (optionalLyric.isPresent()) {
			return optionalLyric.get();
		} else {
			return null;
		}
		
	}


}
