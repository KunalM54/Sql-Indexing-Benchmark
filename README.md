# SQL Indexing Benchmark

A Spring Boot REST API for benchmarking SQL indexing performance with bulk order generation and efficient batch insertion.

## Overview

This application demonstrates SQL indexing concepts by generating large volumes of order data and performing lookup operations. It uses the JavaFaker library to generate realistic product names, implements batch insertion with memory optimization, and defines database indexes for performance testing.

## Features

* Bulk Order Generation
* Batch Insertion with Memory Optimization
* Database Indexing on orderId
* Order Lookup by ID
* Order Lookup by orderId

## Tech Stack

* Java 17
* Spring Boot 4.0.4
* Spring Data JPA
* Spring Web MVC
* MySQL
* JavaFaker

## System Design / How It Works

1. Order generation creates records with UUID-based orderId, faked productName, and random price between 100-50000
2. Batch insertion processes 1000 records at a time using saveAll
3. EntityManager flush and clear operations optimize memory during bulk inserts
4. Index on orderId column improves lookup performance
5. Two lookup methods provided: by auto-generated id and by orderId string

## Project Structure

```text
com.example.sqlindexingbenchmark
├── SqlIndexingBenchmarkApplication.java
├── controller
│   └── OrderController.java
├── service
│   └── OrdersService.java
├── repository
│   └── OrderRepository.java
└── entity
    └── OrderTbl.java
```

## Setup & Installation

1. Ensure Java 17 and Maven are installed
2. Create MySQL database
3. Configure `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
4. Run `mvn spring-boot:run`

## API Endpoints

Base path: `http://localhost:8080/`

### 1) Generate Orders

* **POST** `/generate/{totalOrders}`
* **Path Variable**: totalOrders (number of records to generate)

```http
POST /generate/10000
```

### 2) Find Order by ID

* **GET** `/findOrderId/{id}`
* **Path Variable**: id

```http
GET /findOrderId/1
```

### 3) Find Order by orderId

* **GET** `/findByOrderId/{orderId}`
* **Path Variable**: orderId

```http
GET /findByOrderId/ORD12345678-abcd-1234
```

## Database Schema

### `order_with_index`

* `id` (PK)
* `orderId`
* `productName`
* `price`

**Index**: `idx_order_id` on `orderId`

## Configuration Notes

* `spring.application.name=sql-indexing-benchmark`
* `spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name`
* `spring.datasource.username=your_username`
* `spring.datasource.password=your_password`
* `spring.jpa.hibernate.ddl-auto=update`

## Future Improvements

* Add query performance timing/metrics
* Compare indexed vs non-indexed queries
* Add composite index testing
* Support for different batch sizes
