package com.carthurnau.learnSongs.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	
    @RequestMapping("/home")
    public String home(HttpSession session, Model model) {
        // get user from session, save them in the model and return the home page
    	
    	User user = (User) session.getAttribute("user");
    	model.addAttribute("user",user);
    	
    	List<Song> songList = songService.findAll();
    	model.addAttribute("songList",songList);
    	
    	return "homePage.jsp";
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
	
	@RequestMapping(value = "/searchByArtist", method = RequestMethod.POST)
	public String searchByArtist(HttpSession session, Model model, @RequestParam(value="artist") String artist) {

		User user = (User) session.getAttribute("user");
		model.addAttribute("user",user);
		
		List<Song> songList = songService.findByArtist(artist);
    	model.addAttribute("songList",songList);
    	
    	return "homePage.jsp";
	}
	
	@RequestMapping(value = "/searchByTitle", method = RequestMethod.POST)
	public String searchByTitle(HttpSession session, Model model, @RequestParam(value="title") String title) {
		
		User user = (User) session.getAttribute("user");
		model.addAttribute("user",user);
		
		List<Song> songList = songService.findByTitle(title);
    	model.addAttribute("songList",songList);
    	
    	return "homePage.jsp";
	}
	
}
