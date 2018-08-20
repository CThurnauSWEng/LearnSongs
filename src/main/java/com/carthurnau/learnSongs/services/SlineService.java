package com.carthurnau.learnSongs.services;

import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.models.Sline;
import com.carthurnau.learnSongs.repositories.SlineRepository;

@Service
public class SlineService {
	
	private final SlineRepository sLineRepository;
	
	public SlineService(SlineRepository sLineRepository) {
		this.sLineRepository = sLineRepository;
	}

	public void createSline(Sline sLine) {
		sLineRepository.save(sLine);
	}
	
}
