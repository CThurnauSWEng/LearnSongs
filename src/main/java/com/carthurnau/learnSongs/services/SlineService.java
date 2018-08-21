package com.carthurnau.learnSongs.services;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.models.Lyric;
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

	public void deleteSline(Long lineid) {
		sLineRepository.deleteById(lineid);
		
	}

	public Sline findByID(Long lineid) {
		
		Optional<Sline> optionalsLine = sLineRepository.findById(lineid);
		
		if (optionalsLine.isPresent()) {
			return optionalsLine.get();
		} else {
			return null;
		}
		
	}

	public void updateSline(@Valid Sline sLine) {
		
		sLineRepository.save(sLine);
		
	}
	
}
