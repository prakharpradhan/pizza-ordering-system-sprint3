package com.cg.pos.dao;

import java.util.ArrayList;

import com.cg.pos.dto.PizzaDetails;
import com.cg.pos.dto.StoreDetails;
import com.cg.pos.exceptions.PizzaException;

public interface PizzaDao {

	

	ArrayList<PizzaDetails> getPizzaDetails(StoreDetails store) throws PizzaException;

}
