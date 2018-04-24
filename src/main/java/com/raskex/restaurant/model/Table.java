package com.raskex.restaurant.model;

import java.util.Date;
import java.util.List;

import org.jongo.marshall.jackson.oid.Id;
import org.jongo.marshall.jackson.oid.ObjectId;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.raskex.restaurant.enums.TableStatus;

@JsonInclude(Include.NON_NULL)
public class Table {

	@Id @ObjectId
	private String id;
	private TableStatus status;
	private List<Order> orders;
	private Integer number;
	private Double total;
	private Date createdAt;
	private Date updatedAt;

	public Table(Integer number) {
		this.number = number;
		createdAt = new Date();
	}
	
	
}
