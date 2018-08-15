package com.carthurnau.learnSongs.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.carthurnau.learnSongs.models.Song;
import com.carthurnau.learnSongs.models.User;
import com.carthurnau.learnSongs.services.SongService;
import com.carthurnau.learnSongs.validators.SongValidator;

@Controller
public class SongController {
	
	private final SongService songService;
	private final SongValidator songValidator;
	
	public SongController(SongService songService, SongValidator songValidator) {
		this.songService 	= songService;
		this.songValidator	 = songValidator;
	}

	@RequestMapping("/newSong")
	public String newSongForm(Model model) {
		Song song = new Song();
		model.addAttribute("song",song);
		return "newSongForm.jsp";
	}
	
	@RequestMapping("/createNewSong")
	public String createNewSong(HttpSession session, Model model, @Valid @ModelAttribute("song") Song song, BindingResult result) {

		songValidator.validate(song,result);
	
		if (result.hasErrors()) {
			model.addAttribute("song",song);
			return "newSongForm.jsp";
		} else {

//*** need to add validation for uniqueness here
			
			boolean isUnique = songService.uniqueSong(song.getTitle());
			
			if (isUnique) {
				User user = (User) session.getAttribute("user");
				
				songService.createSong(song,user);
				session.setAttribute("song", song);
				
				return "redirect:/home";
								
			} else {
				
				model.addAttribute("error", "That song is already in the database. Please give this version a unique name.");
				model.addAttribute("song",song);
				return "newSongForm.jsp";
				
			}
			
		}
	}
}
