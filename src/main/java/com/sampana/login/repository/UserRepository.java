package com.sampana.login.repository;

import org.springframework.stereotype.Repository;

import com.sampana.login.model.User;

/**
 * 
 * @author Sudhanshu
 *
 */
@Repository("userRepository")
public interface UserRepository extends BaseRepository<User, Long> {

	public User findByEmail(String email);

	public User findByUserName(String username);

}
