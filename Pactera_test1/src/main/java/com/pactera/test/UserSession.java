package com.pactera.test;

import java.util.ArrayList;
import java.util.List;

public class UserSession {
	public static String currentUserId;
	public static String getCurrentUserId(){
		return currentUserId;
	}
	static List<String> authorisedUserIds = new ArrayList<String>();
	static{
		authorisedUserIds.add("ajohn8");
	}
	
	public static boolean isValidUser(){
		if(authorisedUserIds.contains(getCurrentUserId())){
			return true;
		}else{
			return false;
		}
	}
}
