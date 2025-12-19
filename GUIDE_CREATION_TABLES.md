# 🗄️ Guide : Création Automatique des Tables avec Hibernate

## ✅ Configuration Complète

Votre projet est maintenant configuré pour créer automatiquement les tables dans la base de données MySQL.

## 📋 Ce qui a été fait

### 1. **Configuration de `persistence.xml`**
Les entités ont été déclarées dans le fichier `META-INF/persistence.xml` :

```xml

<class>com.firstproject.telfat_w_lqina.models.User</class>
<class>com.firstproject.telfat_w_lqina.models.Admin</class>
<class>com.firstproject.telfat_w_lqina.models.Agent</class>
```

### 2. **Configuration de `database.properties`**
Le paramètre `hibernate.hbm2ddl.auto=update` permet à Hibernate de :
- ✅ Créer automatiquement les tables si elles n'existent pas
- ✅ Mettre à jour les tables existantes si vous modifiez vos entités
- ✅ Conserver les données existantes

### 3. **Annotations JPA dans les Models**
Toutes les classes (User, Admin, Agent) ont les bonnes annotations :
- `@Entity` : Marque la classe comme une entité JPA
- `@Table(name = "...")` : Définit le nom de la table
- `@Id` : Définit la clé primaire
- `@GeneratedValue` : Génération automatique de l'ID
- `@Column` : Configuration des colonnes
- `@Inheritance` : Stratégie d'héritage JOINED

## 🚀 Comment créer les tables automatiquement

### Méthode 1 : Exécuter TestHibernate (Recommandé)

1. **Assurez-vous que MySQL est démarré** et que la base de données existe :
   ```sql
   CREATE DATABASE IF NOT EXISTS telfat_w_lqina;
   ```

2. **Dans IntelliJ IDEA** :
   - Ouvrez `src/main/java/com/firstproject/telfat_w_lqina/util/TestHibernate.java`
   - Clic droit → `Run 'TestHibernate.main()'`
   
3. **Résultat attendu** :
   ```
   🔌 Test de connexion à la base de données...
   ✅ Connexion MySQL + Hibernate réussie !
   📊 Base de données : telfat_w_lqina
   📝 Les tables suivantes ont été créées automatiquement :
      - user (table parent)
      - admin (hérite de user)
      - agent (hérite de user)
   💾 Insertion d'exemples de données...
   ✅ Admin créé avec ID: 1
   ✅ Agent créé avec ID: 2
   ```

### Méthode 2 : Lancer l'application JavaFX

Les tables seront créées automatiquement au démarrage de `MainApp` car `HibernateUtil` est initialisé au premier appel.

1. **Exécutez Launcher** :
   ```
   Run 'Launcher.main()'
   ```

2. Les tables seront créées en arrière-plan avant l'ouverture de la fenêtre JavaFX.

## 📊 Structure des tables créées

### Table `user` (Parent)
```sql
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    telephone VARCHAR(255)
);
```

### Table `admin` (Enfant)
```sql
CREATE TABLE admin (
    admin_id BIGINT PRIMARY KEY,
    FOREIGN KEY (admin_id) REFERENCES user(id)
);
```

### Table `agent` (Enfant)
```sql
CREATE TABLE agent (
    agent_id BIGINT PRIMARY KEY,
    FOREIGN KEY (agent_id) REFERENCES user(id)
);
```

## 🔧 Options de `hibernate.hbm2ddl.auto`

Dans `database.properties`, vous pouvez changer la valeur :

- **`update`** (actuel) : Crée ou met à jour les tables, conserve les données
- **`create`** : Supprime et recrée les tables à chaque démarrage (⚠️ perte de données)
- **`create-drop`** : Crée au démarrage, supprime à l'arrêt (pour les tests)
- **`validate`** : Vérifie que les tables correspondent aux entités (ne modifie rien)
- **`none`** : Aucune action automatique

## 📝 Ajouter de nouvelles entités

Pour ajouter une nouvelle entité (par exemple `Client`) :

1. **Créer la classe** dans `Models/Client.java` :
   ```java
   @Entity
   @Table(name = "client")
   public class Client {
       @Id
       @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;
       
       @Column(name = "nom", nullable = false)
       private String nom;
       
       // Constructeurs, getters, setters...
   }
   ```

2. **Déclarer dans `persistence.xml`** :
   ```xml
   <class>com.firstproject.telfat_w_lqina.models.Client</class>
   ```

3. **Relancer l'application** → La table `client` sera créée automatiquement !

## ⚠️ Dépannage

### Problème : Les tables ne sont pas créées

1. Vérifiez que MySQL est démarré
2. Vérifiez les informations de connexion dans `database.properties`
3. Vérifiez que `hibernate.hbm2ddl.auto=update` est bien défini
4. Consultez les logs dans la console pour voir les erreurs

### Problème : Erreur de connexion

Vérifiez :
```properties
db.url=jdbc:mysql://localhost:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=root
db.password=votre_mot_de_passe
```

## 🎯 Prochaines étapes

1. ✅ Les tables sont créées automatiquement
2. 📝 Créer les classes DAO pour gérer les opérations CRUD
3. 🔧 Créer les Services pour la logique métier
4. 🎨 Connecter les Controllers aux Services

Bon développement ! 🚀

