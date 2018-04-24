Restaurant API
=======

**To read this document in Brazilian Portuguese, access the [README.pt-br.md](README.pt-br.md) file.**

REST API to manage a restaurant with local and online orders (delivery).

## Challenge

### Use Cases

#### Types of orders:

- Orders made at the restaurant.
- Orders made online for delivery (partners).

#### Cooking system

- As a cook I should be able to view all orders placed (on site / delivery) in the order of arrival.
- As a cook I should be able to change the order status to indicate that the order is being prepared.
- As a cook I should be able to change the order status to indicate that the order is ready.
- As a waiter I should be able to change the order status to indicate that the order has been delivered.

#### Table System

- As a customer I must be able to place an order, choosing a burger and a drink.
- As a customer I must be able to check the order status, to see if it is already being prepared.
- As a customer I must be able to check the value of the bill at any moment.

#### Online orders (API)

- A partner must be able to send an order through the API, stating the order items and the delivery address.
- A partner should be able to check the order status.

## Motivation

Learn new technologies, libraries and ensure good coverage tests.

- [RESTEasy] (http://resteasy.jboss.org/) - Build a REST webservice, the choice was RESTEasy.
- [REST-assured] (https://code.google.com/p/rest-assured/) - This library was chosen due to its simplicity to test and validate REST services.
- [MongoDB] (http://www.mongodb.org/) - As a document database, it was chosen to best represent the structure of an order (products, quantity and delivery data) and also the excellent reading performance.
- [Jongo] (http://www.jongo.org/) - This library was chosen to allow the use of MongoDB in Java and also its great performance.

## Future Improvements

- **Cache**

- **Authentication**

	Implement OAuth2.

- **Dependency Injection**

	Spring.

## Requirements

- Java 7
- Jetty 9
- MongoDB 2.6.4

## Configuration

### MongoDB

1 - [Download] (http://www.mongodb.org/downloads) MongoDB 2.6.4 for the version of your operating system.

2 - Follow the installation recommendations and start the server

`` Html
$ mongod
`` `

## How to use

With the Web server and the MongoDB started and running, perform the following requests to consume the API:

### List all products

**Example request:**

- **GET** [/products](/products)
- **Accept:** application/json
- **Content-Type:** application/json

**Example response:**

```json
	[
		{
			"id": "5403d655c19e51e2ea3c02c0",
			"type": "HAMBURGUER",
			"description": "X-EGG",
			"price": 10.5
		},
		{
			"id": "5403d7e6c19e51e2ea3c02c2",
			"type": "DRINK",
			"description": "Coca-Cola",
			"price": 4
		},
		{
			"id": "5403dddd9386acbf5d3f6055",
			"type": "DESSERT",
			"description": "Sorvete",
			"price": 8.5
		}
	]
```

- **200** OK

### Get the details of a product by ID

**Example request:**

- **GET** [/products/{id}](/products/{id})
- **Accept:** application/json
- **Content-Type:** application/json

**Example response:**

```json
	{
		"id": "5403d655c19e51e2ea3c02c0",
		"type": "HAMBURGUER",
		"description": "X-EGG",
		"price": 10.5
	}
```

- **200** OK

### Create an order (local)

**Example request:**

- **POST** [/orders](/orders)
- **Accept:** application/json
- **Content-Type:** application/json

```json
	{
		"id": "5403d7f7c19e51e2ea3c02c3",
		"table": 5,
		"total": 21.0,
		"status": "WAITING",
		"items":[  
			{  
				"product": {  
					"id": "5403d655c19e51e2ea3c02c0",
					"type": "HAMBURGUER",
					"description": "X-EGG",
					"price": 10.5
				},
				"quantity": 2
			}
		],
		"delivery": null
	}
```

**Example response:**

- **201** CREATED

### Create an order (online)

**Example request:**

- **POST** [/orders](/orders)
- **Accept:** application/json
- **Content-Type:** application/json

```json
	{
		"id": "5203f2f7c11e54e2eb2c12e5",
		"table": null,
		"total": 21.0,
		"status": "WAITING",
		"items": [
			{
				"product": {
					"id": "5403d655c19e51e2ea3c02c0",
					"type": "HAMBURGUER",
					"price" :10.5,
					"description" :"X-EGG"
				},
				"quantity": 2
			}
		],
		"delivery": {
			"address": {
				"state": "SP",
				"number": "100",
				"street": "Rua Guararapes",
				"complement": "APTO 302",
				"city": "São Paulo",
				"zip": "04561-000"
			},
			"fullname": "Lucas Michelini Reis de Oliveira",
			"email": "lucasmro@gmail.com",
			"phone": "11986115678"
		}
	}
```

**Example response:**

- **201** CREATED

### Get the details of an order by ID

**Example request:**

- **GET** [/orders/{id}](/orders/{id})
- **Accept:** application/json
- **Content-Type:** application/json

**Example response:**

```json
	{
		"id": "5604d7f8c19e51e2ea3c01d5",
		"table": 5,
		"total": 4,
		"status": "WAITING",
		"items": [
			{
				"product": {
					"id": "5403d7e6c19e51e2ea3c02c2",
					"type": "DRINK",
					"price": 4,
					"description": "Coca-Cola"
				},
				"quantity": 1
			}
		],
		"delivery": null
	}
```

- **200** OK

### List all requests by table number

**Example request:**

- **GET** [/orders/table/{table}](/orders/table/{table})
- **Accept:** application/json
- **Content-Type:** application/json

**Example response:**

```json
	[
		{
			"id": "5403d7f7c19e51e2ea3c02c3",
			"table": 5,
			"total": 21,
			"status": "WAITING",
			"items": [
				{
					"product": {
						"id": "5403d655c19e51e2ea3c02c0",
						"type": "HAMBURGUER",
						"price": 10.5,
						"description": "X-EGG"
					},
					"quantity": 2
				}
			],
			"delivery": null
		},
		{
			"id": "5604d7f8c19e51e2ea3c01d5",
			"table": 5,
			"total": 4,
			"status": "WAITING",
			"items": [
				{
					"product": {
						"id": "5403d7e6c19e51e2ea3c02c2",
						"type": "DRINK",
						"price": 4,
						"description": "Coca-Cola"
					},
					"quantity": 1
				}
			],
			"delivery": null
		}
	]
```

- **200** OK

### Modify the status of an order by ID

**Example request:**

- **PUT** [/orders/{id}/{status}](/orders/{id}/{status})
- **Accept:** application/json
- **Content-Type:** application/json

**Example response:**

- **204** NO CONTENT

## Error Codes

| Code | Description | Reason                                    |
| ---- | ----------- | ----------------------------------------- |
| 400  | Bad Request | Missing parameters or invalid parameters. |
| 404  | Not Found   | Value not found in the database.	         |
