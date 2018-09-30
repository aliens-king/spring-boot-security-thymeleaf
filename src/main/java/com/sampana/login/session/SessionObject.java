package com.sampana.login.session;

import java.io.Serializable;

import com.sampana.login.vo.UserVO;



/**
 * The Class SessionObject.
 * 
 * This entity is used to set all objects in session
 * @author Sudhanshu
 */
public class SessionObject implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6706859041373378853L;
	private UserVO userVO;

	/**
	 * @return the userVO
	 */
	public UserVO getUserVO() {
		return userVO;
	}

	/**
	 * @param userVO the userVO to set
	 */
	public void setUserVO(UserVO userVO) {
		this.userVO = userVO;
	}
	
	
	
	
}
