package com.cg.pos.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.dto.StoreDetails;
import com.cg.pos.exceptions.PizzaException;
/**
 * Dao class to fetch PIZZA DETAILS according to his storeid
 * @author PRAKHAR
 */

public class PizzaDaoImpl implements PizzaDao {
	private EntityManager manager = null;
	@Override
	public ArrayList<PizzaDetails> getPizzaDetails(StoreDetails store) throws PizzaException {
		
		return null;

	}
}