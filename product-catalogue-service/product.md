# 📦 Product API Spec

Base URL:

```http
/api/products
```

---

# Product Object

```json
{
  "id": 1,
  "name": "iPhone 15 Pro",
  "description": "Latest Apple smartphone",
  "price": 25000000,
  "categoryId": 2
}
```

---

# GET Products

Endpoint : GET /api/products

Query Params :

* name : String, optional
* categoryId : Long, optional

Example Request :

```http
GET /api/products?name=iphone
```

Response Body (Success) :

```json
{
  "data": [
    {
      "id": 1,
      "name": "iPhone 15 Pro",
      "description": "Latest Apple smartphone",
      "price": 25000000,
      "categoryId": 2
    }
  ]
}
```

Response Body (Failed) :

```json
{
  "errors": "Failed to fetch products"
}
```

---

# GET Product Detail

Endpoint : GET /api/products/{id}

Example Request :

```http
GET /api/products/1
```

Response Body (Success) :

```json
{
  "data": {
    "id": 1,
    "name": "iPhone 15 Pro",
    "description": "Latest Apple smartphone",
    "price": 25000000,
    "categoryId": 2
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Product not found"
}
```

---

# CREATE Product

Endpoint : POST /api/products

Request Body :

```json
{
  "name": "iPhone 15 Pro",
  "description": "Latest Apple smartphone",
  "price": 25000000,
  "categoryId": 2
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": 1,
    "name": "iPhone 15 Pro",
    "description": "Latest Apple smartphone",
    "price": 25000000,
    "categoryId": 2
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Validation failed"
}
```

---

# UPDATE Product

Endpoint : PUT /api/products/{id}

Request Body :

```json
{
  "name": "iPhone 15 Pro Max",
  "description": "Updated description",
  "price": 30000000,
  "categoryId": 2
}
```

Response Body (Success) :

```json
{
  "data": {
    "id": 1,
    "name": "iPhone 15 Pro Max",
    "description": "Updated description",
    "price": 30000000,
    "categoryId": 2
  }
}
```

Response Body (Failed) :

```json
{
  "errors": "Product not found"
}
```

---

# DELETE Product

Endpoint : DELETE /api/products/{id}

Response Body (Success) :

```json
{
  "data": "OK"
}
```

Response Body (Failed) :

```json
{
  "errors": "Product not found"
}
```

---

# Validation Rules

```text
name:
  - required
  - max 150 chars

price:
  - required
  - min 0

categoryId:
  - required
```
