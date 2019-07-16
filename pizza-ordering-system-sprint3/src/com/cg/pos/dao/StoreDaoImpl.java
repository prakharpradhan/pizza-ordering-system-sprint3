package com.cg.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.pos.dto.StoreDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.utility.DBConnection;
import com.cg.pos.utility.ExceptionMessage;
import com.cg.pos.utility.DBQueries;

/**
 * Dao class to fetch store name according to his user input
 * 
 * @author PRAKHAR
 *
 */

public class StoreDaoImpl implements StoreDao {
	private Connection connection = null;

	@Override
	public StoreDetails searchStore(StoreDetails store) throws PizzaException {

		connection = DBConnection.getConnection();
		String sql = DBQueries.SEARCH_STORE;
		StoreDetails str = new StoreDetails();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, store.getStoreName());
			rs = preparedStatement.executeQuery();
			while (rs.next()) {

				str.setStoreId(rs.getInt(1));
				str.setStoreName(rs.getString(2));
				str.setStoreAddress(rs.getString(3));
				str.setStoreContact(rs.getLong(4) + "");
				str.setOwnerName(rs.getString(5));
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

		return str;

	}

}
