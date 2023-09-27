package com.aluraHotel.fieldValidation;

import java.util.Calendar;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

public class DataFieldValidation {
	
	private long falseDelay = 60000;
	
	public boolean dateData(JDateChooser data) {
		//Se suma un delay para asegurar que la fecha de reservacion sea por muy pronto hoy
		return data.getDate().getTime() + falseDelay < Calendar.getInstance().getTimeInMillis();
	}
	
	public boolean birthData(JDateChooser data) {
		return Calendar.getInstance().getTimeInMillis() < data.getDate().getTime();
	}
	
	public boolean nameData(JTextField data) {	
		return !data.getText().matches("[a-zA-Z0-9]*");
	}
	
	public boolean telData(JTextField data) {
		return !data.getText().matches("[0-9]*");
	}
	
	
}
