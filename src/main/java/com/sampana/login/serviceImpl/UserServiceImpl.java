package com.sampana.login.serviceImpl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sampana.login.model.User;
import com.sampana.login.repository.UserRepository;
import com.sampana.login.service.UserService;
import com.sampana.login.utils.IConstants;
import com.sampana.login.utils.ToolBox;
import com.sampana.login.utils.UserLocale;
import com.sampana.login.vo.UserVO;

/**
 * 
 * @author Sudhanshu
 *
 */
@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User, Long> implements UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	protected Environment env;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);

	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>(0);
		/*if (user == null) {
			throw new UsernameNotFoundException(username);
		}*/
		//grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		//grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}

	@Override
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

	@Override
	public void saveUser(UserVO userVO) {
		logger.info("-----save user start-----");
		User user=new User();
		Date date = UserLocale.getUserLocaleDate();
		// TODO Auto-generated method stub
		if (!ToolBox.isObjectEmpty(userVO.getUserId())) {
			user = new User(userVO.getUserId());
			user.setUserName(userVO.getUserName());
			user.setPassword(userVO.getPassword());
			user.setCreatedAt(userVO.getCreatedDate());
			user.setActive(userVO.isActive());
		} else {
			user.setPassword(new BCryptPasswordEncoder().encode(userVO.getPassword()));
			

		}
		user.setFullName(userVO.getFullName());
		user.setUserName(userVO.getFullName());
		user.setEmail(userVO.getEmail());
		user.setCreatedAt(date);
		user.setMobileNo(userVO.getPhoneNumber());
		user.setActive(IConstants.NEW_USER);
		userRepository. save(user);
	}

	/*@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}*/

}
