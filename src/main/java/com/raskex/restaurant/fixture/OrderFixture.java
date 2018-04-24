package com.raskex.restaurant.fixture;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectWriter;

import com.raskex.restaurant.model.Address;
import com.raskex.restaurant.model.Delivery;
import com.raskex.restaurant.model.Order;
import com.raskex.restaurant.model.OrderItem;
import com.raskex.restaurant.model.Product;

public class OrderFixture {
	public static Order createTableOrderHamburguer() {
		Product product = ProductFixture.createXEggHamburguer();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(2);

		Order order = new Order();
		order.setId("5403d7f7c19e51e2ea3c02c3");
		order.setTable(5);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		ObjectWriter ow = new ObjectMapper().writer();
		String json = null;
		try {
			json = ow.writeValueAsString(order);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}

	public static Order createTableOrderDrink() {
		Product product = ProductFixture.createCokeDrink();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(1);

		Order order = new Order();
		order.setId("5604d7f8c19e51e2ea3c01d5");
		order.setTable(5);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		return order;
	}

	public static Order createTableOrderDessert() {
		Product product = ProductFixture.createIceCreamDessert();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(1);

		Order order = new Order();
		order.setId("5703d7f7c10e51e2ea2c02d8");
		order.setTable(10);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		return order;
	}

	public static Order createDeliveryOrder() {
		Product product = ProductFixture.createXEggHamburguer();

		OrderItem item = new OrderItem();
		item.setProduct(product);
		item.setQuantity(2);

		Address address = new Address();
		address.setStreet("Rua Guararapes");
		address.setNumber("100");
		address.setComplement("APTO 302");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setZip("04561-000");

		Delivery delivery = new Delivery();
		delivery.setFullname("Lucas Michelini Reis de Oliveira");
		delivery.setEmail("lucasmro@gmail.com");
		delivery.setAddress(address);

		Order order = new Order();
		order.setId("5203f2f7c11e54e2eb2c12e5");
		order.setDelivery(delivery);
		order.addItem(item);
		order.setTotal(item.getProduct().getPrice() * item.getQuantity());

		return order;
	}
}
