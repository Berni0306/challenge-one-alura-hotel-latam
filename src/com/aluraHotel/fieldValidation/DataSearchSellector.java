package com.aluraHotel.fieldValidation;

public class DataSearchSellector {

	public static boolean dataSearchSelector(String data) {
		boolean result = true;
		try {
			Integer.parseInt(data);
		} catch (NumberFormatException e) {
			return false;
		}
		return result;
	}
}
