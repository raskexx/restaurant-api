package com.raskex.restaurant.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.EnumUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.raskex.restaurant.config.SpringApplicationContext;
import com.raskex.restaurant.dao.OrderDao;
import com.raskex.restaurant.enums.OrderStatus;
import com.raskex.restaurant.exception.ApplicationException;
import com.raskex.restaurant.exception.ResourceNotFoundException;
import com.raskex.restaurant.model.Order;

@Controller
@Path("/orders")
public class OrderController {
	
	OrderDao orderDao;
	
//	public OrderController(OrderDao orderDao) {
//		this.orderDao = orderDao;
//	}
	
	@Autowired
	public void setOrderDao(OrderDao dao) {
		orderDao = dao;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response saveOrder(Order order) throws ApplicationException {
		if (!order.isValid()) {
			throw new ApplicationException("Order is invalid!");
		}
		orderDao = (OrderDao) SpringApplicationContext.getBean("orderDao");
		// TODO: Change to Dependency Injection
//		OrderDao orderDao = new OrderDaoImpl();
		orderDao.persist(order);

		return Response.status(Status.CREATED).build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOrderById(@PathParam("id") String id) throws ResourceNotFoundException {
		// TODO: Change to Dependency Injection
//		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());

		Order order = orderDao.findById(id);

		if (null == order) {
			throw new ResourceNotFoundException("No Order found with Id " + id);
		}

		return Response.status(Status.OK).entity(order).build();
	}

	@GET
	@Path("/table/{table}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllOrdersByTable(@PathParam("table") Integer table) throws ResourceNotFoundException {
		// TODO: Change to Dependency Injection
//		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());

		List<Order> orders = orderDao.findAllByTable(table);

		if (null == orders) {
			throw new ResourceNotFoundException("No Orders found for table " + table);
		}

		return Response.status(Status.OK).entity(orders).build();
	}

	@PUT
	@Path("/{id}/{status}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response putOrderStatusByOrderId(@PathParam("id") String id, @PathParam("status") String status) throws ResourceNotFoundException, ApplicationException {
		// TODO: Change to Dependency Injection
//		OrderDao orderDao = new OrderDaoImpl(new MongoDBManager());

		Order order = orderDao.findById(id);

		if (null == order) {
			throw new ResourceNotFoundException("No Order found with Id " + id);
		}

		if (!EnumUtils.isValidEnum(OrderStatus.class, status)) {
			throw new ResourceNotFoundException("Status is invalid!");
		}

		orderDao.updateStatusById(id, OrderStatus.valueOf(status));
		return Response.status(Status.NO_CONTENT).build();
	}
}
