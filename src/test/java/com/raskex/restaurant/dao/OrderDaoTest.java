package com.raskex.restaurant.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.raskex.restaurant.dao.MongoDBManager;
import com.raskex.restaurant.dao.OrderDao;
import com.raskex.restaurant.dao.OrderDaoImpl;
import com.raskex.restaurant.enums.OrderStatus;
import com.raskex.restaurant.exception.ResourceNotFoundException;
import com.raskex.restaurant.fixture.OrderFixture;
import com.raskex.restaurant.model.Order;

public class OrderDaoTest {

//	@Autowired
	MongoDBManager manager;
	
//	@Autowired
	OrderDao orderDao;

	@Before
	public void setUp() {
		manager = new MongoDBManager();
		orderDao = new OrderDaoImpl(manager);
	}

	@After
	public void tearDown() {
		manager.getCollection("orders").drop();
	}

	@Test
	public void testPersist(){
		Order order = OrderFixture.createTableOrderHamburguer();
		order.setId(null);

		orderDao.persist(order);
		assertNotNull(order.getId());
	}

	@Test
	public void testFindAllByTable() {
		orderDao.persist(OrderFixture.createTableOrderHamburguer());
		orderDao.persist(OrderFixture.createTableOrderDrink());
		orderDao.persist(OrderFixture.createTableOrderDessert());
		orderDao.persist(OrderFixture.createDeliveryOrder());

		List<Order> orders = orderDao.findAllByTable(5);
		assertEquals(2, orders.size());
	}

	@Test
	public void testFindById() {
		Order o = OrderFixture.createTableOrderHamburguer();

		Order order = orderDao.findById(o.getId());
		assertNull(order);

		orderDao.persist(o);

		order = orderDao.findById(o.getId());
		assertNotNull(order);
	}

	@Test
	public void testUpdateStatusById() throws ResourceNotFoundException {
		Order o = OrderFixture.createTableOrderHamburguer();
		orderDao.persist(o);

		orderDao.updateStatusById(o.getId(), OrderStatus.PREPARING);

		Order order = orderDao.findById(o.getId());
		assertEquals(OrderStatus.PREPARING, order.getStatus());
	}
}
