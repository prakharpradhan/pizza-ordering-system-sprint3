package com.cg.pos.service.impl;

import java.sql.SQLException;

import com.cg.pos.dao.CartDao;
import com.cg.pos.dao.CartDaoImpl;
import com.cg.pos.dto.CartDetails;
import com.cg.pos.exceptions.PizzaException;
import com.cg.pos.service.CartService;
import com.cg.pos.utility.ExceptionMessage;

/**
 * cart service add to cart remove cart and confirm order
 * 
 * @author Prakhar
 *
 */
public class CartServiceImpl implements CartService {

	/*
	 * Method to add Item to Cart
	 * 
	 */
	@Override
	public int addToCart(int itemId, int qty, String storeName) throws PizzaException {
		CartDetails cart = new CartDetails();
		int check = 0;
		cart.setQuantity(qty);
		CartDao cartdao = new CartDaoImpl();
		int result = cartdao.checkDb(itemId, storeName);
		if (result == itemId) {
			check = cartdao.addToCart(cart, itemId);
			if (check == 0)

			{
				throw new PizzaException(ExceptionMessage.Message8);
			}
		}
		return check;
	}

	/**
	 * confirming order
	 * 
	 * @throws SQLException
	 */
	@Override
	public int confirmOrder(int cartId) throws PizzaException {
		CartDao cart = null;
		if (cart == null) {
			cart = new CartDaoImpl();
		}
		int orderId = cart.addOrder(cartId);
		if (orderId == 0) {

			throw new PizzaException(ExceptionMessage.MESSAGE10);
		}
		return orderId;
	}

	/**
	 * adding more item to cart
	 * 
	 * @throws SQLException
	 */
	@Override
	public void addMore(int cartId, int qty) throws PizzaException {
		CartDao cart = null;
		if (cart == null) {
			cart = new CartDaoImpl();
		}
		int updateRslt = cart.updateCart(cartId, qty);
		if (updateRslt == 0) {
			throw new PizzaException(ExceptionMessage.MESSAGE10);
		}

	}

	/**
	 * Method to view cartdeatils
	 * 
	 * @return
	 * @throws PizzaException
	 * @throws SQLException
	 */
	@Override
	public CartDetails viewCart(int cartId) throws PizzaException {
		CartDao cart = null;
		if (cart == null) {
			cart = new CartDaoImpl();
		}
		CartDetails cartDetails = cart.viewCart(cartId);
		if (cartDetails.getCartId() == 0) {
			throw new PizzaException(ExceptionMessage.MESSAGE10);
		}
		return cartDetails;
	}

	/**
	 * cart details delete function
	 * 
	 * @throws SQLException
	 */
	@Override
	public void deleteCart(int cartId) throws PizzaException {
		CartDao cart = null;
		if (cart == null) {
			cart = new CartDaoImpl();
		}
		int delRslt = cart.deleteCart(cartId);
		if (delRslt == 0) {
			throw new PizzaException(ExceptionMessage.MESSAGE10);
		}

	}

}
