# stockTracker
# **📈 Stock Portfolio Manager**  
A **Spring Boot**-powered application for managing a stock portfolio. Users can register, log in, track their stocks, and monitor real-time stock prices.  

---

## **🚀 Features**
✔️ **User Authentication (JWT-based)** – Secure login and registration  
✔️ **Stock Management** – Add/remove stocks to/from your portfolio   
✔️ **Portfolio Tracking** – Monitor your total investment value  
✔️ **Real-time Stock Prices** – (Upcoming: API integration for live stock prices)  
✔️ **REST API** – Fully functional backend for integration with frontend apps  

---

## **🛠️ Tech Stack**
- **Backend:** Spring Boot, Spring Security (JWT), JPA (Hibernate)  
- **Database:** PostgreSQL  
- **Authentication:** Spring Security, JWT  
- **API Integration:** Yahoo Finance / Alpha Vantage (Upcoming)  
- **Build Tool:** Maven  

---

## **📦 Installation & Setup**

### **1️⃣ Clone the Repository**
```bash
git clone https://github.com/your-username/stock-portfolio-manager.git
cd stock-portfolio-manager
```

### **2️⃣ Configure the Database (PostgreSQL)**
1. Ensure **PostgreSQL is installed** and running.
2. Create a new database:
   ```sql
   CREATE DATABASE stock_portfolio_db;
   ```
3. Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/stock_portfolio_db
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   ```

### **3️⃣ Build & Run the Project**
```bash
mvn clean install
mvn spring-boot:run
```

The app will start at **`http://localhost:8080`** 🚀  

---

## **📌 API Endpoints**
| HTTP Method | Endpoint                 | Description            | Auth Required |
|------------|-------------------------|------------------------|--------------|
| `POST`     | `/api/users/register`    | Register a new user    | ❌ No        |
| `POST`     | `/api/auth/login`        | Login and get JWT      | ❌ No        |
| `GET`      | `/api/stocks/all`        | Get all stocks         | ✅ Yes       |
| `POST`     | `/api/portfolio/add`     | Add stock to portfolio | ✅ Yes       |
| `GET`      | `/api/portfolio/{username}` | View user portfolio | ✅ Yes       |

---

## **🔐 Authentication (JWT)**
1. **Register a user:** `POST /api/users/register`
2. **Login:** `POST /api/auth/login` → Receives a **JWT Token**
3. **Use JWT for protected routes:**
   - Add to **Authorization header**:
     ```
     Authorization: Bearer <your_token_here>
     ```

---

## **🛠️ Upcoming Features**
- ✅ **Stock Price API Integration** (Live market data)  
- ✅ **Portfolio Analytics** (Profit/Loss calculations)  
- ✅ **Frontend (React/Angular)**  

---

## **📜 License**
This project is **MIT Licensed**. Feel free to modify and use it!  

---

## **🤝 Contributing**
Contributions are welcome! Fork the repo, make changes, and submit a PR.  

---

## **📬 Contact**
Have questions? Connect with me:  
📧 **Email:** your-wechulijac@gmail.com  
🐦 **Twitter:** [@wechulijacob](https://twitter.com/wechulijacob)  
🔗 **LinkedIn:** [jacob-jwechuli](https://linkedin.com/in/jacob-jwechuli)  

---
