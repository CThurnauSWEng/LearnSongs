package com.carthurnau.learnSongs.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carthurnau.learnSongs.models.Sline;

@Repository
public interface SlineRepository  extends CrudRepository<Sline, Long>{
	
	List<Sline> findAll();

}
