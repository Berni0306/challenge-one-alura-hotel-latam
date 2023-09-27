package com.aluraHotel.DAO;

import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.aluraHotel.modelo.Huesped;
import com.aluraHotel.fieldValidation.DataSearchSellector;

public class HuespedDAO {

	private Connection conn;

	public HuespedDAO(Connection conn) {
		this.conn = conn;
	}

	public void guardar(Huesped huesped) {
		try {
			final PreparedStatement statement = conn.prepareStatement("INSERT INTO HUESPED("
					+ "NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, RESERVA_ID) "
					+ "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			try (statement){
				ejecutarRegistro(huesped, statement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huesped> buscar(String dato) {
		try {
			List<Huesped> resultado = new ArrayList<>();
			if (DataSearchSellector.dataSearchSelector(dato)) {
				final PreparedStatement statement = conn.prepareStatement("SELECT "
						+ "ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, RESERVA_ID "
						+ "FROM HUESPED WHERE RESERVA_ID = ?", Statement.RETURN_GENERATED_KEYS);
				try (statement){
					statement.setInt(1, Integer.parseInt(dato));
					statement.execute();
					ResultSet resultSet = statement.getResultSet();
					
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"), 
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getString("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"),
								resultSet.getInt("RESERVA_ID"));
						resultado.add(fila);
					}
				}
			} else {
				final PreparedStatement statement = conn.prepareStatement("SELECT "
						+ "ID, NOMBRE, APELLIDO, FECHA_NACIMIENTO, NACIONALIDAD, TELEFONO, RESERVA_ID "
						+ "FROM HUESPED WHERE APELLIDO = ?", Statement.RETURN_GENERATED_KEYS);
				try (statement){
					statement.setString(1, dato);
					statement.execute();
					ResultSet resultSet = statement.getResultSet();
							
					while (resultSet.next()) {
						Huesped fila = new Huesped(resultSet.getInt("ID"), 
								resultSet.getString("NOMBRE"),
								resultSet.getString("APELLIDO"),
								resultSet.getString("FECHA_NACIMIENTO"),
								resultSet.getString("NACIONALIDAD"),
								resultSet.getString("TELEFONO"),
								resultSet.getInt("RESERVA_ID"));
							resultado.add(fila);
					}	
				}
			}
		return resultado;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public int editar(Huesped huesped) {
		try {
			final PreparedStatement statement = conn.prepareStatement("UPDATE HUESPED SET "
					+ "NOMBRE = ?,"
					+ "APELLIDO = ?,"
					+ "FECHA_NACIMIENTO = ?,"
					+ "NACIONALIDAD = ?,"
					+ "TELEFONO = ? "
					+ "WHERE ID = ?");
				
			try(statement){
					statement.setString(1, huesped.getNombre());
					statement.setString(2, huesped.getApellido());
					statement.setString(3, huesped.getFechaNacimiento());
					statement.setString(4, huesped.getNacionalidad());
					statement.setString(5, huesped.getTelefono());
					statement.setInt(6, huesped.getId());
					statement.execute();
					int updateCount = statement.getUpdateCount();
					return updateCount;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	public int eliminar(Integer id) {
		try{
			final PreparedStatement statement = conn.prepareStatement("DELETE FROM HUESPED WHERE ID = ?");
			
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
	
	private void ejecutarRegistro(Huesped huesped, PreparedStatement statement) throws SQLException{
		statement.setString(1, huesped.getNombre());
		statement.setString(2, huesped.getApellido());
		statement.setString(3, huesped.getFechaNacimiento());
		statement.setString(4, huesped.getNacionalidad());
		statement.setString(5, huesped.getTelefono());
		statement.setInt(6, huesped.getIdReserva());
		statement.execute();
	
		ResultSet resultSet = statement.getGeneratedKeys();
		try (resultSet){
			while (resultSet.next()) {
				huesped.setId(resultSet.getInt(1));
				System.out.println(String.format("Se agrego al huesped %s", huesped));
			}
		}
	}
}