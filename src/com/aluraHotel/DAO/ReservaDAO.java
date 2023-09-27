package com.aluraHotel.DAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import com.aluraHotel.fieldValidation.DataSearchSellector;
import com.aluraHotel.modelo.Huesped;
import com.aluraHotel.modelo.Reserva;

public class ReservaDAO {

	private Connection conn;

	public ReservaDAO(Connection conn) {
		this.conn = conn;
	}

	public void guardar(Reserva reserva) {
		try {
			PreparedStatement statement = conn.prepareStatement("INSERT INTO RESERVA("
					+ "FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO) "
					+ "VALUES(?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			try(statement){
				ejecutarRegistro(reserva, statement);
			}
		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reserva> buscar(String dato) {
		try {
			List<Reserva> resultado = new ArrayList<>();
			if (DataSearchSellector.dataSearchSelector(dato)) {
				final PreparedStatement statement = conn.prepareStatement("SELECT "
						+ "ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO "
						+ "FROM RESERVA WHERE ID = ?", Statement.RETURN_GENERATED_KEYS);
				try (statement){
					statement.setInt(1, Integer.parseInt(dato));
					statement.execute();
					ResultSet resultSet = statement.getResultSet();
					
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("ID"), 
								resultSet.getString("FECHA_ENTRADA"),
								resultSet.getString("FECHA_SALIDA"),
								resultSet.getString("VALOR"),
								resultSet.getString("FORMA_PAGO"));
						resultado.add(fila);
					}
				}
			} else {
				final PreparedStatement statement = conn.prepareStatement("SELECT "
						+ "* "
						//+ "ID, FECHA_ENTRADA, FECHA_SALIDA, VALOR, FORMA_PAGO "
						+ "FROM RESERVA AS r JOIN HUESPED AS h "
						+ "ON r.ID = h.RESERVA_ID "
						+ "WHERE h.APELLIDO = ?");
				try (statement){
					statement.setString(1, dato);
					statement.execute();
					ResultSet resultSet = statement.getResultSet();
					
					while (resultSet.next()) {
						Reserva fila = new Reserva(resultSet.getInt("ID"), 
								resultSet.getString("FECHA_ENTRADA"),
								resultSet.getString("FECHA_SALIDA"),
								resultSet.getString("VALOR"),
								resultSet.getString("FORMA_PAGO"));
						resultado.add(fila);
					}
				}
			}
		return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int editar(Reserva reserva) {
		try {
			final PreparedStatement statement = conn.prepareStatement("UPDATE RESERVA SET "
					+ "FECHA_ENTRADA = ?,"
					+ "FECHA_SALIDA = ?,"
					+ "VALOR = ?,"
					+ "FORMA_PAGO = ? "
					+ "WHERE ID = ?");
			
			try(statement){
				statement.setString(1, reserva.getFechaEntrada());
				statement.setString(2, reserva.getFechaSalida());
				statement.setString(3, reserva.getValorReservacion());
				statement.setString(4, reserva.getFormaPago());
				statement.setInt(5, reserva.getId());
				statement.execute();
				int updateCount = statement.getUpdateCount();
				return updateCount;
			}
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int eliminar(Integer id) {
		try{
			final PreparedStatement statement = conn.prepareStatement("DELETE FROM RESERVA WHERE ID = ?");
			
			try(statement){
				statement.setInt(1, id);
				statement.execute();
				int updateCount =  statement.getUpdateCount();
				return updateCount;
			}			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}	
	
	private void ejecutarRegistro(Reserva reserva, PreparedStatement statement) throws SQLException {
		statement.setString(1, reserva.getFechaEntrada());
		statement.setString(2, reserva.getFechaSalida());
		statement.setString(3, reserva.getValorReservacion());
		statement.setString(4, reserva.getFormaPago());
		statement.execute();
		
		ResultSet resultSet = statement.getGeneratedKeys();
		try(resultSet){
			while (resultSet.next()) {
				reserva.setId(resultSet.getInt(1));
				System.out.println(String.format("Se realizo la reserva %s", reserva));
			}
		}
	}	
}
