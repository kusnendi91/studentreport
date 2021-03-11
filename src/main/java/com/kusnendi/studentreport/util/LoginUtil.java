package com.kusnendi.studentreport.util;

import org.mindrot.jbcrypt.BCrypt;


public class LoginUtil {

	
	public static boolean checkPwd(String pwd,String pwdb) {
		if(BCrypt.checkpw(pwd, pwdb)) {
			return true;
		}else {
			return false;
		}
	}

}
