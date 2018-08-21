package com.carthurnau.learnSongs.controllers;

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
import com.carthurnau.learnSongs.services.LyricService;
import com.carthurnau.learnSongs.services.SlineService;
import com.carthurnau.learnSongs.services.SongService;

@Controller
public class LyricController {
	
	private final LyricService lyricService;
	private final SongService songService;
	private final SlineService sLineService;
	
	public LyricController( LyricService lyricService, 
							SongService songService,
							SlineService sLineService) {
		this.lyricService = lyricService;
		this.songService  = songService;
		this.sLineService = sLineService;
	}
	
	@RequestMapping("/showLyrics/{id}")
	public String showLyrics(HttpSession session, Model model, @PathVariable("id") Long songid, @RequestParam("language1") Lyric lyric1, @RequestParam("language2") Lyric lyric2) {

		Song song = songService.findById(songid);
		model.addAttribute("song", song);
		session.setAttribute("song", song);
		
		model.addAttribute("lyric1", lyric1);
		model.addAttribute("lyric2", lyric2);
		
		List<Sline> lines1 = lyric1.getLyricLines();
		model.addAttribute("lines1",lines1);
		
		List <Sline> lines2 = lyric2.getLyricLines();
		model.addAttribute("lines2",lines2);
		
		return "showLyrics.jsp";
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
