package com.kodnest.tunehub.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodnest.tunehub.Entity.User;
import com.kodnest.tunehub.Repository.UserRepository;
import com.kodnest.tunehub.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository repository;

	public String addUser(User user) {
		repository.save(user);
		return "success";
	}

	// to check duplicate entries
	public boolean emailExists(String email) {
		if (repository.findByEmail(email) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean validateUser(String email, String password) {
		User user = repository.findByEmail(email);
		String dbpw = user.getPassword();
		if (password.equals(dbpw)) {
			return true;
		} else {
			return false;
		}

	}

	public String getRole(String email) {
		User user = repository.findByEmail(email);

		return user.getRole();
	}

	@Override
	public User getUser(String email) {

		return repository.findByEmail(email);
	}

	@Override
	public void updateUser(User user) {
		repository.save(user);

	}
}
