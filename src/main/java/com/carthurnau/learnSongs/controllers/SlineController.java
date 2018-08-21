package com.carthurnau.learnSongs.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carthurnau.learnSongs.models.Lyric;
import com.carthurnau.learnSongs.models.Sline;
import com.carthurnau.learnSongs.models.Song;
import com.carthurnau.learnSongs.services.LyricService;
import com.carthurnau.learnSongs.services.SlineService;
import com.carthurnau.learnSongs.services.SongService;

@Controller
public class SlineController {

	private final LyricService lyricService;
	private final SongService songService;
	private final SlineService sLineService;
	
	public SlineController( LyricService lyricService, 
							SongService songService,
							SlineService sLineService) {
		this.lyricService = lyricService;
		this.songService  = songService;
		this.sLineService = sLineService;
	}
	
	@RequestMapping("/deleteLyricLine/{lyricid}/{lineid}")
	public String deleteLyricLine(HttpSession session, Model model, @PathVariable("lineid") Long lineid, @PathVariable("lyricid") Long lyricid) {
		
		Song song = (Song) session.getAttribute("song");
		model.addAttribute("song", song);
		
		Lyric lyric = lyricService.findById(lyricid);
		
		sLineService.deleteSline(lineid);
		
		return "redirect:/addMoreLyricsForm/" + song.getId() + "/" +  lyric.getId();
	}
	
	@RequestMapping("/editLyricLine/{lyricid}/{lineid}")
	public String showEditLineForm(HttpSession session, Model model, @PathVariable("lineid") Long lineid, @PathVariable("lyricid") Long lyricid) {

		Song song = (Song) session.getAttribute("song");
		model.addAttribute("song", song);
		
		Lyric lyric = lyricService.findById(lyricid);
		model.addAttribute("lyric", lyric);
		
		Sline sLine = sLineService.findByID(lineid);
		model.addAttribute("sLine", sLine);
		
		return "editLyricLineForm.jsp";		
		
	}

	@RequestMapping(value = "/processEditLine/{songid}/{lyricid}/{sLineid}", method = RequestMethod.POST)
	public String processEditLine(
				Model model,
				HttpSession session,
				@PathVariable("songid") Long songid,
				@PathVariable("lyricid") Long lyricid,
				@PathVariable("sLineid") Long sLineid,
				@Valid @ModelAttribute("Sline") Sline sLine,
				BindingResult result) {

			
		if (result.hasErrors()) {

			return "redirect:/editLyricLine/" + lyricid + "/" + sLineid;

		} else {
			
			Song song = (Song) session.getAttribute("song");
			
			Lyric lyric = lyricService.findById(lyricid);
						
			sLine.setId(sLineid);
			sLine.setLyric(lyric);
			sLineService.updateSline(sLine);
			
			return "redirect:/addMoreLyricsForm/" + song.getId() + "/" +  lyric.getId();
			

		}

	}
		

}
