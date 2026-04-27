# Inventory Management API

This is the backend API for the Inventory Management system. It provides endpoints for authentication and managing inventory products.

## Authentication Endpoints

The API uses JWT (JSON Web Token) for securing the endpoints. You must first register or login to receive a token, and then include this token in the `Authorization` header of subsequent requests to the `/inventory` endpoints.

### 1. Register a new user

- **URL:** `/auth/register`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  ```
- **Response:**
  - `201 Created`: User registered successfully.
  - `400 Bad Request`: If username already exists or input is invalid.

### 2. Login

- **URL:** `/auth/login`
- **Method:** `POST`
- **Body:**
  ```json
  {
    "username": "your_username",
    "password": "your_password"
  }
  ```
- **Response:**
  - `200 OK`: Returns the JWT token.
    ```json
    {
      "message": "Login successful",
      "username": "your_username",
      "token": "eyJhbGciOiJIUzI1NiJ9..."
    }
    ```
  - `401 Unauthorized`: Invalid credentials.

## Inventory Endpoints

**Note:** All `/inventory` endpoints require the JWT token to be provided in the `Authorization` header.

`Authorization: Bearer <your_jwt_token>`

### Product Object structure

When creating or updating a product, use the following structure:

```json
{
  "sku": "ITEM-123",
  "quantity": 50,
  "name": "Widget",
  "price": 1999, // Note: integer price, e.g., in cents
  "unit": "pieces"
}
```

### 1. Get All Products

- **URL:** `/inventory`
- **Method:** `GET`
- **Response:** Returns an array of Product objects.

### 2. Get a Product by SKU

- **URL:** `/inventory/{sku}`
- **Method:** `GET`
- **Response:**
  - `200 OK`: Returns the Product object.
  - `404 Not Found` (or `500` depending on error handler): If product is not found.

### 3. Create a Product

- **URL:** `/inventory`
- **Method:** `POST`
- **Body:** Product object (see structure above).
- **Response:** Returns the created Product object.

### 4. Update a Product

- **URL:** `/inventory/{sku}`
- **Method:** `PUT`
- **Body:** Product object with updated fields.
- **Response:**
  - `200 OK`: Returns the updated Product object.
  - `404 Not Found` (or `500` depending on error handler): If product to update is not found.

### 5. Delete a Product

- **URL:** `/inventory/{sku}`
- **Method:** `DELETE`
- **Response:** `200 OK` (Product deleted).
