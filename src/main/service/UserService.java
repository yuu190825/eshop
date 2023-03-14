package main.service;

import main.model.User;

/**
 * 用戶服務
 * @author sam
 *
 */
public interface UserService {

	public void createNewAccount(User user);
	
	public boolean loginExists(String login);
	
}
