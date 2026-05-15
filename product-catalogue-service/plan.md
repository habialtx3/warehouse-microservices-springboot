# 🏗️ Microservices ERP Project Roadmap (Spring Boot)

## Planning
Struktur Arsitektur Simulasi Kamu
- Product Spring Boot Master data barang (SKU, Nama, Kategori).
- Inventory Spring Boot Transaksi stok (In/Out), lokasi rak, opname.
- Reporting Express.js Dashboard real-time & rekapitulasi data.
- Procurement/Admin dashboard Laravel Kelola Supplier & pemesanan barang baru.
- GatewaySpring Cloud Pintu masuk utama (Routing & Auth).

## 1. Arsitektur Komponen Utama

- **Config Server (Port 8888)**  
  Pusat konfigurasi `.yml` / `.properties`.

- **Eureka Server (Port 8761)**  
  Service Discovery (registrasi service otomatis).

- **API Gateway (Opsional tapi disarankan)**  
  Single entry point untuk semua service.

- **Inter-service Communication**  
  Menggunakan Spring Cloud OpenFeign (REST-based).

---

## 2. Definisi Services & Database

## A. Product Service (Master Data)

**Tujuan:** Katalog produk utama

**Tabel:** `products`

- id (Long, PK)
- name (String)
- description (String)
- price (BigDecimal)
- category_id (Long)

**Endpoints:**

- `GET /products` → List semua produk
- `GET /products/{id}` → Detail produk

---

## B. Inventory Service (Stock Management)

**Tujuan:** Mengelola stok gudang

**Tabel:** `inventories`

- id (Long, PK)
- product_id (Long, Indexed)
- stock (Integer)
- warehouse_location (String)

**Endpoints:**

- `GET /inventory/{productId}` → Cek stok

---

## C. Order Service (Orchestrator)

**Tujuan:** Menangani transaksi order

**Tabel:**

### orders

- id
- order_number
- customer_id
- total_price
- status

### order_items

- id
- order_id
- product_id
- quantity
- price_at_purchase

**Flow `POST /orders`:**

1. Call Product Service (validasi produk + harga terbaru)
2. Call Inventory Service (reduce stock)
3. Simpan `orders` + `order_items`
4. Call Notification Service (notifikasi sukses)

---

## D. Notification Service

**Tujuan:** Service stateless untuk notifikasi

**Endpoints:**

- `POST /notifications/send-email`

**Payload:**

```json
{
  "to": "string",
  "subject": "string",
  "body": "string"
}
```
