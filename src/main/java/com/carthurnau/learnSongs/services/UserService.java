package com.carthurnau.learnSongs.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.carthurnau.learnSongs.models.User;
import com.carthurnau.learnSongs.repositories.UserRepository;

@Service
public class UserService {


    private final UserRepository userRepository;
    
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    // register user and hash their password
    public User registerUser(User user) {
    	
    	
    	
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        return userRepository.save(user);
    }
    
	public List<User> findByEmail(String email) {

		return userRepository.findByEmail(email);
	}
    
    // find user by id
    public User findUserById(Long id) {
    	Optional<User> u = userRepository.findById(id);
    	
    	if(u.isPresent()) {
            return u.get();
    	} else {
    	    return null;
    	}
    }
    
    public boolean uniqueUser(String email) {
    	
    	ArrayList<User> users = (ArrayList) userRepository.findByEmail(email);
    	
    	if (users.size() > 0) {
    		return false;
    	} else {
    		return true;
    	}
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
    	
    	ArrayList<User> users = (ArrayList) userRepository.findByEmail(email);
        
    	// if we can't find it by email, return false
        if(users.size() < 1) {
        	System.out.println("User not found");
            return false;
        } else {
        	
        	// Use the first one we found. Assume registration will disallow dups
        	User user = users.get(0);
        	
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
            	System.out.println("passwords do not match");
                return false;
            }
        }
    }

	
}
