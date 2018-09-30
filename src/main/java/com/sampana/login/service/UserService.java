package com.sampana.login.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.sampana.login.model.User;
import com.sampana.login.vo.UserVO;


/**
 * 
 * @author Sudhanshu
 *
 */
public interface UserService extends BaseService<User, Long>, UserDetailsService {

	public User findByEmail(String username);

	public User findByUserName(String userName);

	public void saveUser(UserVO userVO);

	/*public void saveUser(User user);*/
	

}
