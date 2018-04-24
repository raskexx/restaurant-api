package com.raskex.restaurant.dao;

import java.net.UnknownHostException;

import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.marshall.jackson.JacksonMapper;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.raskex.restaurant.serialization.Group;

@Service
public class MongoDBManager {

	Jongo jongo;

	public MongoDBManager() {
		try {
			Mongo mongo = new Mongo("127.0.0.1", 27017);
			DB db = mongo.getDB("test");
			jongo = new Jongo(db, new JacksonMapper.Builder()
					.registerModule(new JodaModule())
					.enable(MapperFeature.AUTO_DETECT_GETTERS)
					.withView(Group.Public.class)
					.build());
		} catch (UnknownHostException e) {
		}
	}

	public MongoCollection getCollection(String name) {
		return jongo.getCollection(name);
	}
}
