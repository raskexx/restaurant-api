package com.raskex.restaurant.dao;

import java.util.List;

import com.raskex.restaurant.enums.OrderStatus;
import com.raskex.restaurant.exception.ResourceNotFoundException;
import com.raskex.restaurant.model.Order;

public interface OrderDao {
	public void persist(Order order);

	public List<Order> findAllByTable(Integer table);

	public Order findById(String id);

	public void updateStatusById(String id, OrderStatus status) throws ResourceNotFoundException;
}
