<div align="center">

# 🏆 Telfat W Lqina

### Lost & Found Management System for Major Sporting Events

<p align="center">
  <img src="https://img.shields.io/badge/Java-24-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 24"/>
  <img src="https://img.shields.io/badge/JavaFX-21.0.6-FF4081?style=for-the-badge&logo=java&logoColor=white" alt="JavaFX"/>
  <img src="https://img.shields.io/badge/MySQL-9.5.0-4479A1?style=for-the-badge&logo=mysql&logoColor=white" alt="MySQL"/>
  <img src="https://img.shields.io/badge/Hibernate-6.4.4-59666C?style=for-the-badge&logo=hibernate&logoColor=white" alt="Hibernate"/>
  <img src="https://img.shields.io/badge/Maven-3.6+-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white" alt="Maven"/>
  <img src="https://img.shields.io/badge/License-MIT-green?style=for-the-badge" alt="MIT License"/>
  <img src="https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge" alt="Status"/>
</p>

**A professional JavaFX desktop application for digitizing, securing, and optimizing the management of lost and found objects at stadiums and sporting events — built for CAN 2025 and FIFA World Cup 2030.**

[Features](#-features) • [Tech Stack](#-tech-stack) • [Getting Started](#-getting-started) • [Project Structure](#-project-structure) • [Demo](#-live-demo)

</div>

---

## 📸 Screenshots

<div align="center">

> *Screenshots of the application running — Login, Admin Dashboard, Agent Dashboard, Lost Objects Management*

<img src="src/main/resources/images/stade.png" alt="Stadium" width="300"/>
&nbsp;&nbsp;
<img src="src/main/resources/images/zellige.png" alt="Moroccan Zellige" width="300"/>

</div>

---

## ✨ Features

### 🔐 Security & Authentication
- Role-based authentication — **Admin** and **Agent** roles
- BCrypt password hashing via [jBCrypt](https://github.com/jeremyh/jBCrypt)
- Auto-creation of the first admin account on startup
- First-login password change enforcement
- Fine-grained access control for sensitive operations

### 📦 Lost Object Management
- Detailed registration of found objects (description, photos, location, date)
- Image upload and storage (JPG/PNG, stored as BLOB)
- Object categorization: Electronics, Documents, Personal Items, Clothing, Bags, Sports Equipment, Accessories, and more
- Multi-criteria advanced search (category, date, site, keyword)
- Full claim & retrieval workflow (report → log → match → verify → return)
- Object state tracking: In Storage, Returned, Claimed

### 🏟️ Stadium & Site Management
- Create, edit, and delete stadiums and drop zones
- Associate agents per stadium site
- City-level organization of venues

### 📋 Complaint Management
- File and track complaints linked to lost objects
- Proof-of-presence verification (photos, identity documents)
- Complaint status lifecycle: Pending → In Progress → Resolved

### 📊 Statistics & Reporting
- Role-specific dashboards (Admin vs. Agent views)
- Charts: objects returned vs. pending, average resolution time
- Filters by period, stadium, and object type
- Admin-level overview of all activity

### 🔄 Full Workflow
1. Agent registers a found object (with photos + metadata)
2. Object is logged in the database and assigned a tracking state
3. Owner files a claim through the interface
4. Agent verifies proof of identity and ownership
5. Object is handed over and transaction is archived

---

## 🛠️ Tech Stack

<div align="center">

[![My Skills](https://skillicons.dev/icons?i=java,mysql,maven,hibernate,idea&theme=dark)](https://skillicons.dev)

</div>

| Layer | Technology |
|-------|-----------|
| **UI** | JavaFX 21.0.6 (FXML + CSS) |
| **Language** | Java 24 |
| **ORM** | Hibernate 6.4.4 + Jakarta Persistence API 3.1.0 |
| **Database** | MySQL (via MySQL Connector/J 9.5.0) |
| **Connection Pool** | HikariCP 5.1.0 |
| **Security** | jBCrypt 0.4 |
| **Build** | Apache Maven 3.6+ |
| **Logging** | SLF4J Simple 2.0.7 |
| **Testing** | JUnit Jupiter 5.12.1 |

---

## 🚀 Getting Started

### Prerequisites

- ☕ [JDK 24](https://openjdk.org/) installed and `JAVA_HOME` configured
- 📦 [Apache Maven 3.6+](https://maven.apache.org/)
- 🗄️ [MySQL 8+](https://dev.mysql.com/downloads/) running locally
- 💡 [IntelliJ IDEA](https://www.jetbrains.com/idea/) (recommended) or any Java IDE

Verify your environment:
```bash
java -version    # should be 24+
mvn -v           # should be 3.6+
mysql --version  # should be 8+
```

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/Yassine-Atiki/Telfat_w_lqina.git
cd Telfat_w_lqina
```

### Environment Setup

```bash
# 2. Create your local database configuration
cp .env.example src/main/resources/database.properties
```

Edit `src/main/resources/database.properties` with your database credentials:

```properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=root
db.password=YOUR_DATABASE_PASSWORD

hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.hbm2ddl.auto=create   # Use 'update' after first run
hibernate.show_sql=true
hibernate.format_sql=true
```

> ⚠️ **Never commit** `database.properties` — it is already in `.gitignore`.  
> 💡 After the first successful run, change `hibernate.hbm2ddl.auto` from `create` to `update`.

#### (Optional) Manual database creation via MySQL CLI

```sql
CREATE DATABASE IF NOT EXISTS telfat_w_lqina
  CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

### Run Locally

```bash
# 3. Build and run the application
mvn clean javafx:run
```

Or in IntelliJ: open the project → reload Maven → run `com.firstproject.telfat_w_lqina.Launcher`.

### First Login

| Username | Password |
|----------|----------|
| `Admin`  | `Admin`  |

> 🔒 You will be prompted to change the default password on first login.

---

## 📁 Project Structure

```
Telfat_w_lqina/
├── src/
│   ├── main/
│   │   ├── java/com/firstproject/telfat_w_lqina/
│   │   │   ├── controllers/          # JavaFX FXML controllers (Admin, Agent, Login, etc.)
│   │   │   ├── dao/                  # Data Access Objects (UserDAO, LostObjectDAO, etc.)
│   │   │   ├── Enum/                 # Enumerations (UserType, TypeObjet, TypeState, etc.)
│   │   │   ├── exception/            # Custom exceptions (validation, creation, not-found)
│   │   │   ├── models/               # JPA entities (User, LostObject, Complaint, Stadium, etc.)
│   │   │   ├── service/              # Business logic (AuthService, UserService, etc.)
│   │   │   ├── test/                 # Connection test utilities
│   │   │   ├── util/                 # Helpers (HibernateUtil, Security, NavigationUtil, etc.)
│   │   │   ├── Launcher.java         # JavaFX launch entry point
│   │   │   └── MainApp.java          # Application lifecycle (init, start, stop)
│   │   └── resources/
│   │       ├── css/                  # Application stylesheets
│   │       ├── fxml/                 # FXML view definitions (14 screens)
│   │       ├── images/               # Image assets
│   │       ├── META-INF/
│   │       │   └── persistence.xml   # JPA persistence unit configuration
│   │       └── database.properties   # ⚠️ Local DB config (not committed)
├── .env.example                      # Template for database.properties
├── .gitignore
├── LICENSE
├── pom.xml                           # Maven build configuration
├── README.md
└── SECURITY_SETUP.md                 # Security & DB setup guide
```

---

## 🌐 Live Demo

> 🔗 **[https://your-live-demo-url.com](https://your-live-demo-url.com)**  
> *(Desktop application — no hosted web demo available. Clone and run locally.)*

---

## 👤 Authors

<table>
  <tr>
    <td align="center">
      <strong>Mohamed Amine Nihmatouallah</strong><br/>
      <a href="https://ma.linkedin.com/in/mohamed-amine-nihmatouallah">LinkedIn</a>
    </td>
    <td align="center">
      <strong>Yassine Atiki</strong><br/>
      <a href="https://github.com/Yassine-Atiki">GitHub</a> •
      <a href="https://ma.linkedin.com/in/yassine-atiki-b8a815332">LinkedIn</a>
    </td>
  </tr>
</table>

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| `Connection refused` | Ensure MySQL is running on port 3306 |
| `Unknown database` | Add `createDatabaseIfNotExist=true` to the URL in `database.properties` |
| `Driver not found` | Run `mvn clean install` to download all dependencies |
| `JavaFX not found` | Always run via `mvn javafx:run`, not `java -jar` |
| App won't start | Check the console for `>>> ERREUR` messages and verify `database.properties` |

---

## 📄 License

This project is licensed under the **MIT License** — see the [LICENSE](LICENSE) file for details.

---

<div align="center">

### 🏆 Built for CAN 2025 & FIFA World Cup 2030 Morocco

*Making lost & found management effortless at the world's biggest sporting events.*

</div>

