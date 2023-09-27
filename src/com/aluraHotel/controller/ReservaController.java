package com.aluraHotel.controller;

import com.aluraHotel.modelo.Reserva;

import java.util.List;

import com.aluraHotel.DAO.ReservaDAO;
import com.aluraHotel.connFactory.ConnectionFactory;

public class ReservaController {

	private ReservaDAO reservaDAO;
	
	public ReservaController() {
		this.reservaDAO = new ReservaDAO(new ConnectionFactory().recuperaConexion());
	}

	public void guardar(Reserva reserva) {
		reservaDAO.guardar(reserva);
	}

	public List<Reserva> buscar(String dato) {
		return reservaDAO.buscar(dato);
	}

	public int editar(Reserva reserva) {
		return reservaDAO.editar(reserva);
	}

	public int eliminar(Integer id) {
		return reservaDAO.eliminar(id);
	}
}
