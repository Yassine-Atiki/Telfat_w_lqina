# 🏆 Telfat W Lqina - Gestion des Objets Perdus/Trouvés

<div align="center">

![Java](https://img.shields.io/badge/Java-24-orange?logo=openjdk)
![JavaFX](https://img.shields.io/badge/JavaFX-21.0.6-FF4081?logo=javafx)
![MySQL](https://img.shields.io/badge/MySQL-9.5.0-4479A1?logo=mysql)
![Hibernate](https://img.shields.io/badge/Hibernate-6.4.4-59666C?logo=hibernate)
![Maven](https://img.shields.io/badge/Maven-3.6+-C71A36?logo=apachemaven)

**Application desktop moderne de gestion des objets perdus et trouvés pour événements sportifs**

[🎯 Présentation](#-présentation) • [✨ Fonctionnalités](#-fonctionnalités) • [🏗️ Architecture](#️-architecture) • [🚀 Installation](#-installation-rapide)

</div>

---

## 📋 Sommaire
- [🎯 Présentation](#-présentation-du-projet)
- [✨ Fonctionnalités](#-fonctionnalités)
- [🏗️ Architecture](#️-architecture)
- [⚙️ Prérequis](#️-prérequis)
- [🚀 Installation Rapide](#-installation-rapide)
- [🗄️ Configuration Base de Données](#-configuration-de-la-base-de-données)
- [💻 Utilisation](#-utilisation)
- [👥 Équipe](#-équipe)
- [❓ FAQ](#-faq)
- [🐛 Dépannage](#-dépannage)
- [📁 Structure du projet](#-structure-du-projet)
- [📚 Annexes Techniques](#-annexes-techniques)

---

## 🎯 Présentation du projet

**Telfat W Lqina** (CAN 2025) est une application desktop JavaFX destinée à digitaliser, sécuriser et optimiser la gestion des objets perdus et trouvés dans les stades et sites sportifs lors d'événements de grande ampleur (ex : CAN 2025, Coupe du Monde 2030).

L'application vise à réduire les pertes, accélérer la restitution, fournir des outils de suivi et produire des rapports statistiques exploitables par les responsables opérationnels.

### 🎯 Objectifs principaux
- Automatiser le processus de gestion des objets perdus/trouvés.
- Réduire le temps de restitution aux propriétaires.
- Assurer la traçabilité et l'audit des opérations.
- Fournir des tableaux de bord et rapports pour la prise de décision.

### 👥 Public ciblé
- Administrateurs : gestion des comptes, configuration des sites, supervision et reporting.
- Agents de terrain : enregistrement des objets trouvés, traitement des réclamations et restitution.

---

## ✨ Fonctionnalités

### 🔐 Sécurité & Authentification
- Authentification avec rôles (ADMIN / AGENT).
- Création automatique du compte administrateur initial (si absent).
- Hachage des mots de passe (BCrypt).
- Contrôles d'accès sur les actions sensibles.

### 📦 Gestion des Objets
- Enregistrement détaillé des objets trouvés (description, photos, lieu, date).
- Recherche avancée multi-critères (catégorie, date, site, mot-clé).
- Support d'images (JPG/PNG) avec stockage optimisé.
- Catégorisation et étiquetage pour un matching plus rapide.
- Workflow de réclamation et restitution.

### 🏟️ Gestion des Sites (stades)
- Création/édition de stades et zones de dépôt.
- Attribution d'agents par site.
- Paramètres spécifiques par site (horaires, procédures).

### 📊 Statistiques & Rapports
- Tableaux de bord par rôle (Admin / Agent).
- Graphiques: objets restitués vs en attente, temps moyen de restitution.
- Export de rapports (PDF, Excel, CSV).
- Filtres par période, site, type d'objet.

### 🔄 Workflow complet
1. Signalement d'un objet trouvé par un agent.
2. Enregistrement dans la base (avec photos).
3. Recherche par le propriétaire via interface.
4. Validation/identification par l'agent.
5. Restitution et archivage de la transaction.

---

## 🏗️ Architecture

### 📐 Stack technique (principal)

```yaml
Frontend:
  - JavaFX 21.0.6 (FXML + CSS)
  - Contrôleurs suivant le pattern MVC

Backend:
  - Java 24 (comportement attendu selon pom.xml)
  - Hibernate ORM 6.4.4
  - Jakarta Persistence API 3.1.0
  - Services métier et DAOs

Base de données:
  - MySQL Connector/J 9.5.0 (driver)
  - HikariCP 5.1.0 (pool de connexions)
  - Schéma utf8mb4_unicode_ci (recommandé)

Outils:
  - Maven 3.6+ (build et plugins)
  - SLF4J (logging)
  - JUnit (tests)
```

### 🏛️ Organisation des packages

```
com.firstproject.telfat_w_lqina/
├── controller/          # Contrôleurs JavaFX
├── model/               # Entités JPA (@Entity)
├── service/             # Logique métier
├── dao/                 # Accès aux données (Repository)
├── util/                # Utilitaires (HibernateUtil, PasswordUtil...)
└── MainApp.java         # Point d'entrée

resources/
├── fxml/                # Vues
├── css/                 # Styles
├── images/              # Ressources
└── database.properties  # Config locale (non committée)
```

---

## ⚙️ Prérequis

### ✅ Checklist rapide
- [ ] JDK 24 installé et `JAVA_HOME` configuré (ou JDK compatible si vous modifiez le `pom.xml`).
- [ ] Maven 3.6+ installé.
- [ ] MySQL (ou autre SGBD) disponible.
- [ ] IDE (IntelliJ recommandé).

### 🔧 Vérification (PowerShell / terminal)
```powershell
# Vérifier Java
java -version
# Vérifier Maven
mvn -v
# Vérifier Git
git --version
```

---

## 🚀 Installation rapide

### 1) Récupérer le projet

```bash
# Clone depuis le dépôt (ou dézippez l'archive locale)
git clone [URL_DU_PROJET]
cd Telfat_w_lqina
```

### 2) Préparer la configuration DB
- Copier/adapter `src/main/resources/database.properties` (ou créer `database.properties.local`) et renseigner vos identifiants.
- Attention : ne pas committer vos identifiants.

Exemple (MySQL, création auto) :

```properties
# src/main/resources/database.properties
db.driver=com.mysql.cj.jdbc.Driver
db.url=jdbc:mysql://localhost:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
db.username=root
db.password=VotreMotDePasse
hibernate.dialect=org.hibernate.dialect.MySQLDialect
hibernate.hbm2ddl.auto=create
hibernate.show_sql=true
hibernate.format_sql=true
```

> Après la première exécution réussie, changez `hibernate.hbm2ddl.auto=create` en `update`.

### 3) Compiler et exécuter (PowerShell)

```powershell
# Nettoyage et compilation
mvn clean package

# Lancer via plugin JavaFX
mvn javafx:run

# OU exécuter le JAR (si jar exécutable)
java -jar target\telfat_w_lqina-1.0-SNAPSHOT.jar
```

Si vous utilisez IntelliJ : ouvrez le projet, rechargez Maven, configurez le SDK et exécutez la classe `com.firstproject.telfat_w_lqina.Launcher` ou `MainApp`.

---

## 🗄️ Configuration de la base de données (détaillé)

### Méthode recommandée : création automatique (développement)
- Configurez `database.properties` avec `?createDatabaseIfNotExist=true` et `hibernate.hbm2ddl.auto=create`.
- Lancez l'application ; elle créera la base et les tables.
- Inspectez les logs pour vérifier la création et la création du compte admin.

### Méthode manuelle : SQL (MySQL)

```sql
CREATE DATABASE IF NOT EXISTS telfat_w_lqina CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
CREATE USER IF NOT EXISTS 'telfat_app'@'localhost' IDENTIFIED BY 'MotDePasseSecure123!';
GRANT ALL PRIVILEGES ON telfat_w_lqina.* TO 'telfat_app'@'localhost';
FLUSH PRIVILEGES;
```

Puis adaptez `database.properties` pour utiliser cet utilisateur.

### Configuration de production (exemples)

```properties
# Utiliser des variables d'environnement pour les secrets
db.username=${DB_USER}
db.password=${DB_PASSWORD}
db.url=jdbc:mysql://prod-db.example.com:3306/telfat_w_lqina_prod?useSSL=true&requireSSL=true&serverTimezone=UTC
hibernate.hbm2ddl.auto=validate
hibernate.show_sql=false
```

---

## 💻 Utilisation

### Première connexion

1. Lancer l'application :
```powershell
mvn javafx:run
# ou
java -jar target\telfat_w_lqina-1.0-SNAPSHOT.jar
```
2. Identifiants (par défaut, vérifier dans le code si modifiés) :
- Nom d'utilisateur : `Admin`
- Mot de passe : `Admin`

3. Changez immédiatement le mot de passe administrateur.

### Workflow courant (agents)
- Enregistrer un objet trouvé → Ajouter photos et métadonnées → Informer le propriétaire potentiel → Gérer la réclamation → Restituer et archiver.

---

## 👥 Équipe

| Membre | Contact |
|--------|---------|
| Mohamed Amine Nihmatouallah | [LinkedIn](https://ma.linkedin.com/in/mohamed-amine-nihmatouallah) |
| Yassine Atiki | [LinkedIn](https://ma.linkedin.com/in/yassine-atiki-b8a815332) |

> Pour demandes techniques, partagez : version de l'app, logs, étapes pour reproduire.

---

## ❓ FAQ

Q : Comment réinitialiser la base ?

A : Mettre `hibernate.hbm2ddl.auto=create`, redémarrer l'application, puis repasser à `update`.

Q : L'application ne se lance pas — étape de base ?

A : 1) `java -version`, 2) `mvn clean compile`, 3) vérifier `database.properties`, 4) lancer `mvn javafx:run`.

---

## 🐛 Dépannage rapide

### Logs à surveiller (console)
```
>>> INIT() STARTED - Initialisation de l'application...
>>> Hibernate chargé avec succès
>>> Base de données initialisée
>>> Compte admin créé: admin / admin123
>>> START() CALLED - Chargement login.fxml
```

### Erreurs courantes & solutions
- Connection refused → MySQL non démarré.
- Driver not found → lancer `mvn clean install` pour télécharger les dépendances.
- Unknown database → ajouter `createDatabaseIfNotExist=true` à l'URL ou créer la base manuellement.
- JavaFX not found → exécuter via `mvn javafx:run` ou configurer module-path dans IntelliJ.

### Commandes de diagnostic

```powershell
# Nettoyage
mvn clean
Remove-Item -Recurse -Force target\

# Vérifier dépendances
mvn dependency:tree

# Exécuter avec logs détaillés
mvn javafx:run -X
```

---

## 📁 Structure du projet (résumé)

```
TELFAT_W_LQINA/
├── src/
│   ├── main/
│   │   ├── java/com/firstproject/telfat_w_lqina/
│   │   │   ├── controller/
│   │   │   ├── model/
│   │   │   ├── service/
│   │   │   ├── dao/
│   │   │   ├── util/
│   │   │   └── MainApp.java
│   │   └── resources/
│   │       ├── fxml/
│   │       ├── css/
│   │       ├── images/
│   │       └── database.properties
├── pom.xml
├── README.md
└── scripts/
```

---

## 📚 Annexes Techniques

### A. Extraits recommandés `hibernate` pour perf

```properties
hibernate.jdbc.batch_size=20
hibernate.order_inserts=true
hibernate.order_updates=true
hibernate.jdbc.fetch_size=100
hibernate.cache.use_second_level_cache=true
hibernate.cache.use_query_cache=true
```

### B. Variables d'environnement utiles (exemples)

```powershell
# Windows PowerShell
$env:DB_USER = "telfat_app"
$env:DB_PASSWORD = "MotDePasseSecure123!"
$env:JAVA_OPTS = "-Xmx2G -Xms512M -XX:+UseG1GC"
```

---

## 📞 Support & Contact

- Issues / Bugs : ouvrir une issue sur le dépôt GitHub.
- Documentation : dossier `/docs/` (à créer/compléter).
- Contact équipe : via LinkedIn (liens plus haut).

---

<div align="center">

## 🏆 Prêt pour la CAN 2025 !

**Telfat W Lqina** - La solution professionnelle de gestion des objets perdus pour les grands événements

</div>

---

*Fichier généré localement. Ne commitez pas vos secrets (database.properties) vers un dépôt public.*
