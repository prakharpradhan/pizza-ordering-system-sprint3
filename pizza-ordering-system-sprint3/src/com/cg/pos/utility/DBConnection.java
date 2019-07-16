package com.cg.pos.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.cg.pos.exceptions.PizzaException;
/**
 * connection to database
 * @author trainee
 *
 */
public class DBConnection {
	private static Connection connection;

	public static Connection getConnection() throws PizzaException {

		if (null == connection) {
			try {
				connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");

			} catch (SQLException e) {
				throw new PizzaException(ExceptionMessage.MESSAGE1);

			}

		}

		return connection;
	}

	public static void dissconnect() throws PizzaException {
		try {
			connection.close();
		} catch (SQLException e) {

			throw new PizzaException(ExceptionMessage.MESSAGE1);
		}

	}

}
