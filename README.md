# Inventory Management System

This project is an Inventory Management System developed using Spring Boot.

## Overview

The Inventory Management System aims to handle inventory operations such as deducting and adding inventory quantities based on various operations such as order fulfillment, restocking, and product returns.

## Dependencies

- Spring Boot
- Spring Data JPA
- H2 Database (In-memory)
- Maven (Build tool)

## Setup

1. **Clone the repository:**
   git clone https://github.com/aayush-king/InventorySystem.git
2. **Build the project:**
      mvn clean install
3. **Run the application:**
   java -jar target/inventory-management-system.jar

**API Endpoints**
/api/inventory/deduct: POST endpoint to deduct inventory quantities.
/api/inventory/add: POST endpoint to add inventory quantities.
/api/inventory/transactions/{productId}: GET endpoint to retrieve transactions by product ID.
