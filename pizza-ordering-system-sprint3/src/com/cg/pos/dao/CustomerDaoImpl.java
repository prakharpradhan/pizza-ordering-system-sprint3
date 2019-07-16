package com.cg.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.pos.dto.CustomerDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.utility.DBConnection;
import com.cg.pos.utility.ExceptionMessage;
import com.cg.pos.utility.DBQueries;

/**
 * Dao class to fetch customer name according to his id
 * 
 * @author PRAKHAR
 *
 */

public class CustomerDaoImpl implements CustomerDao {
	/**
	 * method to fetch from dao
	 */
	private Connection connection = null;

	@Override
	public CustomerDetails viewCustomerDetails(CustomerDetails customerDetails) throws PizzaException {
		connection = DBConnection.getConnection();
		String sql = DBQueries.ViewCustomer;
		CustomerDetails cust = new CustomerDetails();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, customerDetails.getCustomerId());

			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				cust.setCustomerName(rs.getString(1));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new PizzaException(ExceptionMessage.MESSAGE1);
		} finally {

			try {
				rs.close();
				preparedStatement.close();
			} catch (SQLException e) {
				throw new PizzaException(ExceptionMessage.MESSAGE1);
			}

		}

		return cust;

	}

}
