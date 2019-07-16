package com.cg.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cg.pos.dto.CartDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.utility.DBConnection;
import com.cg.pos.utility.DBQueries;
import com.cg.pos.utility.ExceptionMessage;

/**
 * Cart database access class
 * 
 * @author Prakhar
 *
 */
public class CartDaoImpl implements CartDao {
	private Connection connection = null;

	/**
	 * adding data to cart
	 * 
	 * @throws SQLException
	 */
	@Override
	public int addToCart(CartDetails cart, int itemId) throws PizzaException {
		connection = DBConnection.getConnection();
		String sql = DBQueries.ADDTOCART;
		String sq = DBQueries.GETPRICE;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStmt = null;
		int cartid = 0;
		int addResult = 0;

		double price = 0;
		try {
			preparedStmt = connection.prepareStatement(sq);
			preparedStmt.setInt(1, itemId);
			ResultSet rs = preparedStmt.executeQuery();
			while (rs.next()) {
				price = rs.getDouble(1);
			}
			preparedStatement = connection.prepareStatement(sql, new String[] { "cartid" });
			preparedStatement.setInt(1, cart.getQuantity());
			preparedStatement.setDouble(2, price * cart.getQuantity());
			preparedStatement.setDouble(3, 50);
			preparedStatement.setDouble(4, (price * cart.getQuantity()) + 50);
			addResult = preparedStatement.executeUpdate();
			if (addResult > 0) {
				ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
				if (null != generatedKeys && generatedKeys.next()) {

					cartid = generatedKeys.getInt(1);

				}
			}

		} catch (SQLException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE1);
		} finally {
			try {
				connection.commit();

				preparedStatement.close();
			} catch (SQLException e) {
				throw new PizzaException(ExceptionMessage.MESSAGE1);
			}
		}
		return cartid;
	}

	/**
	 * checking pizza available in store
	 * 
	 * @throws SQLException
	 */
	@Override
	public int checkDb(int itemId, String storeName) throws PizzaException {
		connection = DBConnection.getConnection();
		String sql = DBQueries.DBCHECK;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int result = 0;
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, storeName);
			preparedStatement.setInt(2, itemId);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
				result = rs.getInt(1);

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

		return result;

	}

	/**
	 * view cart details
	 * 
	 * @throws SQLException
	 */
	@Override
	public CartDetails viewCart(int cartId) throws PizzaException {
		connection = DBConnection.getConnection();
		String sql = DBQueries.VIEW_CART_BY_ID;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;

		CartDetails cartDetails = new CartDetails();
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cartId);
			rs = preparedStatement.executeQuery();

			while (rs.next()) {

				cartDetails.setCartId(rs.getInt(1));
				cartDetails.setDelievryCharges(rs.getDouble(4));
				cartDetails.setQuantity(rs.getInt(2));
				cartDetails.setRestCharges(rs.getDouble(3));
				cartDetails.setTotal(rs.getDouble(5));

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
		return cartDetails;
	}

	/**
	 * deleting cart
	 * 
	 * @throws SQLException
	 */
	@Override
	public int deleteCart(int cartId) throws PizzaException {

		connection = DBConnection.getConnection();
		String sql = DBQueries.DELFROMCART;

		PreparedStatement preparedStatement = null;

		int delResult = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, cartId);
			delResult = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE1);
		} finally {
			try {
				connection.commit();
				preparedStatement.close();
			} catch (SQLException e) {
				throw new PizzaException(ExceptionMessage.MESSAGE1);

			}
		}
		return delResult;
	}

	/**
	 * updating cart
	 * 
	 * @throws SQLException
	 */
	@Override
	public int updateCart(int cartId, int qty) throws PizzaException {
		connection = DBConnection.getConnection();
		String sql = DBQueries.UPDATECART;

		PreparedStatement preparedStatement = null;

		int updateResult = 0;

		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, qty);
			preparedStatement.setInt(2, cartId);
			updateResult = preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE1);
		} finally {
			try {
				connection.commit();
				preparedStatement.close();
			} catch (SQLException e) {
				throw new PizzaException(ExceptionMessage.MESSAGE1);

			}
		}
		return updateResult;
	}

	/**
	 * addding order
	 * 
	 * @throws SQLException
	 */
	@Override
	public int addOrder(int cartId) throws PizzaException {
		connection = DBConnection.getConnection();
		String sql = DBQueries.ADDORDER;
		String sq = DBQueries.GETID;
		PreparedStatement prepstmt = null;
		PreparedStatement preparedStatement = null;
		int orderId = 0;
		int addResult = 0;
		int id = 0;
		ResultSet rs = null;
		try {
			prepstmt = connection.prepareStatement(sq);
			prepstmt.setInt(1, cartId);
			rs = prepstmt.executeQuery();
			while (rs.next()) {
				id = rs.getInt(1);
			}
			if (id != 0) {
				preparedStatement = connection.prepareStatement(sql, new String[] { "orderid" });
				preparedStatement.setInt(1, cartId);
				addResult = preparedStatement.executeUpdate();
				if (addResult > 0) {
					ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
					if (null != generatedKeys && generatedKeys.next()) {

						orderId = generatedKeys.getInt(1);

					}
				}

			} else {
				throw new PizzaException(ExceptionMessage.MESSAGE9);
			}
		} catch (SQLException e) {
			throw new PizzaException(ExceptionMessage.MESSAGE1);
		} finally {
			try {
				connection.commit();
			} catch (SQLException e) {

				throw new PizzaException(ExceptionMessage.MESSAGE1);
			}
		}
		return orderId;

	}
}
