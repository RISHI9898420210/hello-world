package com.org.kpit.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	
	public static Gson getInstance() {
		return new GsonBuilder().setDateFormat("yyyy/mm/dd HH:mm:ss").create();
	}

}
