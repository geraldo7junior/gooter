package br.com.gooter.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	public Connection getConnection() throws SQLException,
			ClassNotFoundException {

		Class.forName("com.mysql.jdbc.Driver");

		return DriverManager.getConnection("jdbc:mysql://localhost/gooter",
				"root", "mugen");

	}
}
