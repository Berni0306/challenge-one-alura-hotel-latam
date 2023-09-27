package com.aluraHotel.modelo;


public class Reserva {

	private Integer id;
	private String fechaEntrada;
	private String fechaSalida;
	private String valorReservacion;
	private String formaPago;
	
	public Reserva(String fechaEntrada, String fechaSalida, String valorReservacion, String formaPago) {
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valorReservacion = valorReservacion;
		this.formaPago = formaPago;
	}
	
	public Reserva(Integer id,String fechaEntrada, String fechaSalida, String valorReservacion, String formaPago) {
		this.id = id;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valorReservacion = valorReservacion;
		this.formaPago = formaPago;
	}

	public Reserva() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(String fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public String getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(String fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getValorReservacion() {
		return valorReservacion;
	}

	public void setValorReservacion(String valorReservacion) {
		this.valorReservacion = valorReservacion;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	@Override
	public String toString() {
		return String.format(
				"{id: %d, Fecha de entrada: %s, Fecha de salida: %s, Costo: $%s, Forma de pago: %s}",
				this.id,
				this.fechaEntrada,
				this.fechaSalida,
				this.valorReservacion,
				this.formaPago);
	}
}
