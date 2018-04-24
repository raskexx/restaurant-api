package com.raskex.restaurant.fixture;

import org.jongo.MongoCollection;

import com.raskex.restaurant.dao.MongoDBManager;

public class OrderFixtureHelper {
	public static String COLLECTION_NAME = "orders";

	public static void load(MongoDBManager manager) {
		MongoCollection collection = manager.getCollection(COLLECTION_NAME);
		collection.save(OrderFixture.createTableOrderHamburguer());
		collection.save(OrderFixture.createTableOrderDrink());
		collection.save(OrderFixture.createTableOrderDessert());
		collection.save(OrderFixture.createDeliveryOrder());
	}

	public static void drop(MongoDBManager manager) {
		MongoCollection collection = manager.getCollection(COLLECTION_NAME);
		collection.drop();
	}
}
