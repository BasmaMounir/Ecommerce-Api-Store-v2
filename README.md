# 🛒 Ecommerce API Store v2

A Spring Boot RESTful API for managing e-commerce data, including categories and (later) products.  
This project is created for learning and building backend skills using Java and Spring Boot.

---

## 📦 Features

- Category Management (Create, Read, Update, Delete)
- RESTful API following clean architecture (Controller, Service, Repository)
- Custom API Response structure
- Validation and error handling
- Ready for extension (Products, Users, Auth, etc.)

---

## 🧩 Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Postman (for testing)

---

## 🗂️ Category Module

The Category module allows managing different product categories in the system.

### ✅ Fields:

| Field | Type   | Description        |
|-------|--------|--------------------|
| id    | Long   | Auto-generated ID  |
| name  | String | Category name (unique) |
| slug  | String | URL-friendly version of name (unique) |
| image | String | URL to category image |

---

## 📋 API Endpoints

### 🔹 Get All Categories

- **GET** `/api/categories`
- Returns a list of all categories.

---

### 🔹 Get Category by ID

- **GET** `/api/categories/{id}`
- Returns a single category based on the ID.

---

### 🔹 Create a New Category

- **POST** `/api/categories`
- **Body:**
```json
{
  "name": "Fashion",
  "slug": "fashion",
  "image": "https://example.com/image.jpg"
}
```

---

### 🔹 Update a Category

- **PUT** `/api/categories/{id}`
- **Body (any field you want to update):**
```json
{
  "name": "New Fashion"
}
```

---

### 🔹 Delete a Category

- **DELETE** `/api/categories/{id}`

---

## 📂 SubCategories

SubCategories represent more specific divisions under each main Category.  
Each SubCategory is related to one Category and includes its own name, slug, and timestamps.

### 📌 Fields

- `id`: Unique identifier.
- `name`: Name of the subcategory (e.g., "Laptops", "Shoes").
- `slug`: URL-friendly version of the name (e.g., "laptops").
- `categoryId`: ID of the parent category.
- `createdAt`, `updatedAt`: Timestamps.

---

### ✅ Endpoints

#### 🔹 Get All SubCategories

- **URL**: `/api/subcategories`
- **Method**: `GET`
- **Description**: Returns a list of all subcategories.

📥 **Sample Response**:

```json
[
  {
    "id": 1,
    "name": "Laptops",
    "slug": "laptops",
    "categoryId": 7
  },
  {
    "id": 2,
    "name": "Shoes",
    "slug": "shoes",
    "categoryId": 5
  }
]
```

---

#### 🔹 Get SubCategories by Category

- **URL**: `/api/subcategories/category/{categoryId}`
- **Method**: `GET`
- **Description**: Returns all subcategories related to a specific category.

---

#### 🔹 Create SubCategory

- **URL**: `/api/subcategories`
- **Method**: `POST`
- **Description**: Create a new subcategory under a specific category.

📤 **Sample Request Body**:

```json
{
  "name": "Dresses",
  "slug": "dresses",
  "categoryId": 5
}
```

---

#### 🔹 Update SubCategory

- **URL**: `/api/subcategories/{id}`
- **Method**: `PUT`
- **Description**: Update a subcategory’s details.

---

#### 🔹 Delete SubCategory

- **URL**: `/api/subcategories/{id}`
- **Method**: `DELETE`
- **Description**: Delete a subcategory by its ID.

---

### 📎 Notes
- Each subcategory must be linked to an existing category.
- `name` field is unique and validated.
- The `slug` should be unique and meaningful for better SEO and frontend integration.

---


## 💻 Author

[Basma Mounir](https://github.com/BasmaMounir)

---
