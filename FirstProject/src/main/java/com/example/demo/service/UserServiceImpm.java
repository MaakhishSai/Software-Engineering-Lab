package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@Service
public class UserServiceImpm implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<User> showUserList() {
		return userRepository.findAll();
	}

	@Override
	// create user
	public void saveUser(User user) {
		// here we want to take id as the next available one so 
		// but during update create a new user if previously unavailable
		if (!userRepository.existsById(user.getId())) {
	        // This is a new user, so assign the next available ID
	        Long nextId = userRepository.findNextAvailableId().orElse(1L); // Default to 1 if no users exist
	        user.setId(nextId);
	    }
		this.userRepository.save(user);
	}

	@Override
	public User getUserById(long id) {
		Optional<User> optional = userRepository.findById(id);
		User user = null;
		if(optional.isPresent()) {
			user = optional.get();
		}
		else {
			throw new RuntimeException(" Employee Not Found for Id : " + id);
		}
		return user;
	}

	@Override
	public void deleteUserById(long id) {
		this.userRepository.deleteById(id);
	}

}
