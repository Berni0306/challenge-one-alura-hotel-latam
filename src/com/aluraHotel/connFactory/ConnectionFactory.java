package com.aluraHotel.connFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {

	String databaseName = "aluraHotel";
	
	private DataSource dataSource;
	
	public ConnectionFactory() {
		var pooledDataSource = new ComboPooledDataSource();
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/aluraHotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("12345678");
		pooledDataSource.setMaxPoolSize(10);
		
		this.dataSource = pooledDataSource;
	}
	
	public void initDatabase() {
		try {
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/?user=root&password=12345678?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC");
			String createDatabase = "CREATE DATABASE IF NOT EXISTS " + databaseName;
			connection.createStatement().execute(createDatabase);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Connection recuperaConexion() {
		try {
			//initDatabase();
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
