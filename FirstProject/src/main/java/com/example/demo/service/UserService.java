package com.example.demo.service;

import java.util.List;

import com.example.demo.model.User;

public interface UserService {
	// getAllUsers
	List<User> showUserList();
	void saveUser(User user);
	User getUserById(long id);
	void deleteUserById(long id);
}
 