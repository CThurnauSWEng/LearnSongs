package com.carthurnau.learnSongs.services;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.models.Song;
import com.carthurnau.learnSongs.models.User;
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

	public Song createSong(@Valid Song song, User user) {
				
		song.setCreatedBy(user.getEmail());
		
		return songRepository.save(song);
		
	}
	
	public Song updateSong(Song song) {
		return songRepository.save(song);
	}
	
	public List<Song> findByTitle(String title){
		
		return songRepository.findByTitle(title);
	}
	
	public List<Song> findByArtist(String artist){
		
		return songRepository.findByArtist(artist);
	}
	
	public boolean uniqueSong(String title) {
		
		ArrayList<Song> songs = (ArrayList) songRepository.findByTitle(title);
		
		if (songs.size() > 0) {
			return false;
		} else {
			return true;
		}
	}

}
