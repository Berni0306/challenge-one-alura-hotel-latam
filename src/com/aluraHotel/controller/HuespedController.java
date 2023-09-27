package com.aluraHotel.controller;

import java.util.List;

import com.aluraHotel.DAO.HuespedDAO;
import com.aluraHotel.connFactory.ConnectionFactory;
import com.aluraHotel.modelo.Huesped;

public class HuespedController {

	private HuespedDAO huespedDAO;
	
	public HuespedController() {
		this.huespedDAO = new HuespedDAO(new ConnectionFactory().recuperaConexion());
	}

	public void guardar(Huesped huesped) {
		huespedDAO.guardar(huesped);
	}
	
	public List<Huesped> buscar(String dato) {
		return huespedDAO.buscar(dato);
	}

	public int editar(Huesped huesped) {
		return huespedDAO.editar(huesped);
	}

	public int eliminar(Integer id) {
		return huespedDAO.eliminar(id);
	}
}
