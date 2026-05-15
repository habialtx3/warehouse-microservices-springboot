# 📦 Inventory API Spec

## Base URL

```
/api/inventories
```

---

## Inventory Object

```json
{
  "id": 1,
  "productId": 10,
  "stockQuantity": 150,
  "warehouseLocation": "Rak A-12"
}
```

---

## GET Inventories

**Endpoint:**

```
GET /api/inventories
```

**Query Params:**

* productId (String, optional)
* warehouseLocation (String, optional)

**Example Request:**

```
GET /api/inventories?productId=10
```

**Success Response:**

```json
{
  "data": [
    {
      "id": 1,
      "productId": 10,
      "stockQuantity": 150,
      "warehouseLocation": "Rak A-12"
    }
  ]
}
```

**Failed Response:**

```json
{
  "errors": "Failed to fetch inventories"
}
```

---

## GET Stock by Product

**Endpoint:**

```
GET /api/inventories/product/{productId}
```

**Example Request:**

```
GET /api/inventories/product/10
```

**Success Response:**

```json
{
  "data": {
    "id": 1,
    "productId": 10,
    "stockQuantity": 150,
    "warehouseLocation": "Rak A-12"
  }
}
```

**Failed Response:**

```json
{
  "errors": "Inventory for product not found"
}
```

---

## CREATE Inventory

**Endpoint:**

```
POST /api/inventories
```

**Request Body:**

```json
{
  "productId": 10,
  "stockQuantity": 50,
  "warehouseLocation": "Rak A-12"
}
```

**Success Response:**

```json
{
  "data": {
    "id": 1,
    "productId": 10,
    "stockQuantity": 50,
    "warehouseLocation": "Rak A-12"
  }
}
```

**Failed Response:**

```json
{
  "errors": "Validation failed"
}
```

---

## UPDATE Inventory Details

**Endpoint:**

```
PUT /api/inventories/{id}
```

**Request Body:**

```json
{
  "stockQuantity": 48,
  "warehouseLocation": "Rak B-05"
}
```

**Success Response:**

```json
{
  "data": {
    "id": 1,
    "productId": 10,
    "stockQuantity": 48,
    "warehouseLocation": "Rak B-05"
  }
}
```

**Failed Response:**

```json
{
  "errors": "Inventory not found"
}
```

---

## REDUCE STOCK

**Endpoint:**

```
PUT /api/inventories/reduce
```

**Request Body:**

```json
{
  "productId": 10,
  "qty": 2
}
```

**Success Response:**

```json
{
  "data": {
    "id": 1,
    "productId": 10,
    "stockQuantity": 148,
    "warehouseLocation": "Rak A-12"
  }
}
```

**Failed Response:**

```json
{
  "errors": "Insufficient stock or product not found"
}
```

---

## ADD / RESTOCK STOCK

**Endpoint:**

```
PUT /api/inventories/add
```

**Request Body:**

```json
{
  "productId": 10,
  "qty": 50
}
```

**Success Response:**

```json
{
  "data": {
    "id": 1,
    "productId": 10,
    "stockQuantity": 198,
    "warehouseLocation": "Rak A-12"
  }
}
```

**Failed Response:**

```json
{
  "errors": "Product not found"
}
```

---

## DELETE INVENTORY

**Endpoint:**

```
DELETE /api/inventories/{id}
```

**Success Response:**

```json
{
  "data": "OK"
}
```

**Failed Response:**

```json
{
  "errors": "Inventory not found"
}
```

---

## VALIDATION RULES

(To be defined)
