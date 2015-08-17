package com.example.chawla.constants;

public interface ApiConstants {

	interface ENTITY {
		String	MENU1	= "menu1";
		String	MENU	= "menu";
	}

	interface PARAMS {

		String	NAME	= "name";
		String	PRICE	= "price";
		String	TYPE	= "type";
	}

	final int		REQUEST_GET_LOOKS	= 1;
	final String	URL_GET_LOOKS		= "https://api.parse.com/1/functions/search";

}
