package com.carthurnau.learnSongs.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.carthurnau.learnSongs.models.Lyric;
import com.carthurnau.learnSongs.models.Sline;
import com.carthurnau.learnSongs.models.Song;
import com.carthurnau.learnSongs.models.User;
import com.carthurnau.learnSongs.services.LyricService;
import com.carthurnau.learnSongs.services.SlineService;
import com.carthurnau.learnSongs.services.SongService;
import com.carthurnau.learnSongs.validators.SongValidator;

@Controller
public class SongController {
	
	private final SongService songService;
	private final SongValidator songValidator;
	private final LyricService lyricService;
	private final SlineService sLineService;
	
	public SongController(	SongService songService, 
							SongValidator songValidator,
							LyricService lyricService,
							SlineService sLineService) {
		this.songService 	= songService;
		this.songValidator	= songValidator;
		this.lyricService 	= lyricService;
		this.sLineService  	= sLineService;
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
	
	@RequestMapping(value = "/addLyrics/{id}")
	public String addLyricsForm(HttpSession session, Model model, @PathVariable("id") Long songId) {
		
		Song song = songService.findById(songId);
		model.addAttribute("song", song);
		
		Lyric lyric = new Lyric();
		model.addAttribute("lyric",lyric);
		
		session.setAttribute("song", song);
		
		return "addLyricsForm.jsp";
				
	}
	
	@RequestMapping(value = "/createNewLyrics", method = RequestMethod.POST)
	public String createNewLyrics(HttpSession session, Model model, @Valid @ModelAttribute("lyric") Lyric lyric, BindingResult result) {

		Song song = (Song) session.getAttribute("song");
		model.addAttribute("song", song);

		if (result.hasErrors()) {
			// unimplemented:  package errors and pass back to jsp
			System.out.println("error creating lyric");
			return "redirect:/addLyrics/" + song.getId();			
		} else {
			
			lyricService.createLyric(lyric);
			
			session.setAttribute("lyric",lyric);
			
			return "redirect:/addMoreLyricsForm/" + song.getId() + "/" +  lyric.getId();
		}
		
	}
	
	@RequestMapping("/addMoreLyricsForm/{songid}/{lyricid}")
	public String addMoreLyricsForm(Model model, HttpSession session, @PathVariable("songid") Long songid, @PathVariable("lyricid") Long lyricid) {
		
		Song song = songService.findById(songid);
		model.addAttribute("song", song);
		session.setAttribute("song", song);
				
		Lyric lyric = lyricService.findById(lyricid);
		session.setAttribute("lyric",lyric);
		model.addAttribute("lyric",lyric);
				
		List<Sline> allLyricLines = lyric.getLyricLines();
		model.addAttribute("allLyricLines", allLyricLines);
		
		Sline sLine = new Sline();
		model.addAttribute("sLine", sLine);
		
		return "addMoreLyrics.jsp";
	}
		
	@RequestMapping(value = "/addLyrics", method = RequestMethod.POST)
	public String addLyrics(Model model, HttpSession session, @Valid @ModelAttribute("sline") Sline sLine, BindingResult result) {
		
		Lyric lyric = (Lyric) session.getAttribute("lyric");
		model.addAttribute("lyric",lyric);
		
		Song song = (Song) session.getAttribute("song");
		model.addAttribute("song", song);

		// Save the sLine object which will add the line to the lyric object
		if (result.hasErrors()) {
			System.out.println("Error in addLyrics");
	        for (ObjectError m : result.getAllErrors()) {
	        	System.out.println(m.getDefaultMessage());
	        }
		} else {

			sLine.setLyric(lyric);
			
			
			sLineService.createSline(sLine);
			
			List<Sline> theseLyricLines = lyric.getLyricLines();
			theseLyricLines.add(sLine);
			lyric.setLyricLines(theseLyricLines);
			
			lyricService.updateLyric(lyric);
		}
		
		
		List<Sline> allLyricLines = lyric.getLyricLines();
		model.addAttribute("allLyricLines", allLyricLines);

				
		Sline newSline = new Sline();
		model.addAttribute("sLine", newSline);
		

		return "redirect:/addMoreLyricsForm/" + song.getId() + "/" +  lyric.getId();
	}

	
}
