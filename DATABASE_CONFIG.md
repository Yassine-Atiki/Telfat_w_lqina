# Configuration de la Base de Données - Telfat w lqina

## 🔧 Configuration Initiale pour les Développeurs

### Étape 1 : Configurer la base de données

1. Allez dans le dossier `src/main/resources/`
2. Copiez le fichier `database.properties.template`
3. Renommez la copie en `database.properties`
4. Modifiez les paramètres selon votre configuration MySQL locale

### Étape 2 : Paramètres à modifier

Ouvrez `database.properties` et modifiez :

```properties
# Votre nom d'utilisateur MySQL
db.username=root

# Votre mot de passe MySQL
db.password=

# L'URL de votre base (modifiez le port si nécessaire)
db.url=jdbc:mysql://localhost:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
```

### 📋 Configurations Courantes

#### XAMPP/WAMP (Windows)
```properties
db.url=jdbc:mysql://localhost:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=root
db.password=
```

#### MySQL avec mot de passe
```properties
db.username=root
db.password=VotreMotDePasse
```

#### Serveur distant
```properties
db.url=jdbc:mysql://adresse_ip:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=votre_user
db.password=votre_password
```

### ⚠️ Important

- **NE COMMITTEZ JAMAIS** le fichier `database.properties` (il est dans `.gitignore`)
- Partagez uniquement le fichier template `database.properties.template`
- Chaque développeur garde sa configuration locale

### 🚀 Utilisation

Une fois configuré, Hibernate utilisera automatiquement ces paramètres au démarrage de l'application.

```java
// Exemple d'utilisation
EntityManager em = HibernateUtil.getEntityManager();
// ... votre code ...
```

La base de données sera créée automatiquement si elle n'existe pas.

