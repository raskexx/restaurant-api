package com.raskex.restaurant.dao;

import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.jongo.MongoCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.raskex.restaurant.enums.OrderStatus;
import com.raskex.restaurant.exception.ResourceNotFoundException;
import com.raskex.restaurant.model.Order;

@Repository
public class OrderDaoImpl implements OrderDao {
	
	private MongoCollection collection;

	@Autowired
	private MongoDBManager manager;
	
	public OrderDaoImpl(MongoDBManager dbManager) {
		this.manager = dbManager;
		collection = manager.getCollection("orders");
	}

//	public void setMongoDBManager(MongoDBManager dbManager) {
//		this.manager = dbManager;
//	}
//	
//	public MongoDBManager getMongoDBManager() {
//		return manager;
//	}
	
	@Override
	public void persist(Order order) {
		collection.save(order);
	}

	@Override
	public List<Order> findAllByTable(Integer table) {
		Iterable<Order> ordersIterator = collection.find("{table: #}", table).as(Order.class);

		// TODO: Convert from iterator to List
		List<Order> orders = new ArrayList<Order>();
		for (Order order : ordersIterator) {
			orders.add(order);
		}
		return (0 == orders.size()) ? null : orders;
	}

	@Override
	public Order findById(String id) {
		try {
			return collection.findOne(new ObjectId(id)).as(Order.class);
		} catch (IllegalArgumentException e) {
			return null;
		}
	}

	@Override
	public void updateStatusById(String id, OrderStatus status) throws ResourceNotFoundException {
		collection.update(new ObjectId(id)).with("{$set: {status: #}}", status);
	}

}