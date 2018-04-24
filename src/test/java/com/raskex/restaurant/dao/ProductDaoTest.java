package com.raskex.restaurant.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.raskex.restaurant.dao.MongoDBManager;
import com.raskex.restaurant.dao.ProductDao;
import com.raskex.restaurant.dao.ProductDaoImpl;
import com.raskex.restaurant.fixture.ProductFixture;
import com.raskex.restaurant.model.Product;

public class ProductDaoTest {
	MongoDBManager manager;
	ProductDao productDao;

	@Before
	public void setUp() {
		manager = new MongoDBManager();
		productDao = new ProductDaoImpl(manager);
	}

	@After
	public void tearDown() {
		manager.getCollection("products").drop();
	}

	@Test
	public void testInsert() {
		Product product = ProductFixture.createXEggHamburguer();
		product.setId(null);

		assertNull(product.getId());
		productDao.persist(product);
		assertNotNull(product.getId());
	}

	@Test
	public void testFindAll() {
		productDao.persist(ProductFixture.createXEggHamburguer());
		productDao.persist(ProductFixture.createCokeDrink());
		productDao.persist(ProductFixture.createIceCreamDessert());

		List<Product> products = productDao.findAll();
		assertEquals(3, products.size());
	}

	@Test
	public void testFindById() {
		Product p = ProductFixture.createXEggHamburguer();

		Product product = productDao.findById(p.getId());
		assertNull(product);

		productDao.persist(p);

		product = productDao.findById(p.getId());
		assertNotNull(product);
	}
}
