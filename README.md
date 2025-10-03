# Inventory-Management-System-API

## Description
A backend API to manage products and inventory in a warehouse.  

**Features:**
- Full CRUD operations for products (name, description, stock quantity).  
- Increase and decrease stock with proper validation.  
- Low stock threshold per product, with an endpoint to list products below their threshold.  
- Proper error handling (400 Bad Request for invalid stock, 404 Not Found for missing products).

---

## Setup Instructions

- Clone the repository:
1. git clone <your-repo-link>
2. cd Inventory-Management-System-API

- Configure MySQL database in application.properties:
1. spring.datasource.url=jdbc:mysql://localhost:3306/Inventory-Management-System-API
2. spring.datasource.username=<your-username>
3. spring.datasource.password=<your-password>

- Build and run the project using Maven:
1. mvn clean install
2. mvn spring-boot:run

- API will be available at:
1. http://localhost:7777/api/v1/ims/product

**API Endpoints**

- Create Product(POST)
1. /create
   
2. Body Example:
    {
  "name": "Laptop Charger",
  "description": "65W USB-C charger for laptops",
  "stock_quantity": 20,
  "low_stock_threshold": 10
}

- Get Product by ID (GET)
1. /{productId}

- Update Product (PUT)
1. /{productId}
   
2. Body Example: 
   {
  "name": "Laptop Charger - Updated",
  "description": "65W USB-C fast charger",
  "stock_quantity": 25,
  "low_stock_threshold": 15
 }

- Delete Product (DELETE)
1. /{productId}

- Increase Stock(PUT)
1. /{productId}/increaseQuantity?amount=10

- Decrease Stock(PUT)
1. /{productId}/decreaseQuantity?amount=5

- Get All Products Below Low Stock Threshold(GET)
1. /getAll

**Assumptions / Design Choices**

 1. Stock quantity cannot go below 0.

 2. Negative amounts for increase/decrease throw InvalidStockException.

 3. Low stock threshold is stored per product.

 4. /getAll endpoint automatically lists products below their threshold.

**Tested with postman**

- I have uploaded postman collection to test the system with pre configured requests and parameters.

## Postman Collection
- A Postman collection with all API endpoints is included in the `postman/` folder:  
- `postman/Inventory-Management-System-API.postman_collection.json`
  
**How to use:**
1. Open Postman.
2. Click **Import** → choose this JSON file.
3. All endpoints, request bodies, and parameters are pre-configured.
4. Send requests to test the API locally.
