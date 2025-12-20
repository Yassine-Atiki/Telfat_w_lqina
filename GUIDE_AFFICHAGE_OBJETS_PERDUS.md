# 📋 Guide : Affichage des Objets Perdus

## 🎯 Objectif
Récupérer et afficher tous les objets perdus (`LostObject`) stockés dans la base de données dans une interface moderne conforme au design de l'application "Telfat w lqina".

---

## ✅ Étapes Implémentées

### **Étape 1 : Couche DAO (Data Access Object)**

**Fichier modifié :** `LostObjectDAO.java`

**Méthode ajoutée :**
```java
public List<LostObject> getAllLostObjects()
```

**Fonctionnalités :**
- Utilise Hibernate/JPA pour exécuter une requête JPQL
- Récupère tous les objets perdus de la base de données
- Trie les résultats par date décroissante (`ORDER BY l.lostDate DESC`)
- Gère les exceptions et ferme proprement l'EntityManager
- Retourne une `List<LostObject>`

---

### **Étape 2 : Couche Service**

**Fichier modifié :** `LostObjectService.java`

**Méthode ajoutée :**
```java
public List<LostObject> getAllLostObjects()
```

**Fonctionnalités :**
- Appelle la méthode `getAllLostObjects()` du DAO
- Agit comme une couche intermédiaire entre le contrôleur et le DAO
- Permet d'ajouter de la logique métier si nécessaire (filtrage, transformation, etc.)

---

### **Étape 3 : Vue FXML - Interface Graphique**

**Fichier créé :** `ViewLostObjects.fxml`

**Composants principaux :**

#### **Header (Haut de page)**
- Logo "Telfat w lqina" avec couleurs CAN 2025
- Nom de l'agent connecté
- Bouton **Retour** (retour vers Agent.fxml)
- Bouton **Déconnexion** (retour vers Login.fxml)

#### **Section Centrale**
- **Titre** : "Objets Trouvés - CAN 2025"
- **Label Total** : Affiche le nombre total d'objets
- **TableView** avec 7 colonnes :
  - Type d'objet
  - Description
  - Date trouvé
  - Zone
  - Nom de l'agent
  - Téléphone
  - Email

#### **Boutons d'action**
- **Actualiser** : Rafraîchir les données (vert #006233)
- **Ajouter Nouvel Objet** : Naviguer vers AddLostObject.fxml (rouge #C1272D)

#### **Footer**
- Copyright "© 2025 Telfat w lqina - CAN 2025 Maroc"

**Style appliqué :**
- Fond général : `#f5f5f5` (gris clair)
- Cartes blanches avec ombres portées (DropShadow)
- Couleurs Maroc : Rouge `#C1272D`, Vert `#006233`, Jaune `#ffeb3b`
- Bordures arrondies : `border-radius: 8px`
- Design moderne et épuré

---

### **Étape 4 : Contrôleur JavaFX**

**Fichier créé :** `ViewLostObjectsController.java`

**Attributs FXML :**
- `tableViewLostObjects` : TableView principal
- `colType`, `colDescription`, `colDate`, etc. : Colonnes du tableau
- `labelAgent` : Nom de l'agent connecté
- `totalLabel` : Affichage du total d'objets

**Méthodes principales :**

#### **1. initialize()**
- Appelée automatiquement au chargement de la vue
- Récupère l'utilisateur connecté via `SessionManager`
- Configure les colonnes du TableView
- Charge les données depuis la base de données

#### **2. configureTableColumns()**
- Lie chaque colonne à la propriété correspondante du modèle `LostObject`
- Utilise `PropertyValueFactory` pour mapper automatiquement :
  - `colType` → `type`
  - `colDescription` → `description`
  - `colDate` → `lostDate`
  - `colZone` → `zone`
  - `colAgent` → `agentName`
  - `colPhone` → `phone`
  - `colEmail` → `email`
- Applique un style CSS au TableView (lignes alternées, couleurs de sélection)

#### **3. loadLostObjects()**
- Appelle `lostObjectService.getAllLostObjects()`
- Convertit la `List<LostObject>` en `ObservableList` (requis pour JavaFX)
- Assigne les données au TableView avec `setItems()`
- Met à jour le label du total
- Gère les exceptions

#### **4. Actions utilisateur :**
- `refreshTable()` : Recharge les données
- `addNewObject()` : Navigation vers AddLostObject.fxml
- `goBack()` : Retour vers Agent.fxml
- `seDeconnecter()` : Déconnexion et retour au login

---

### **Étape 5 : Liaison avec la page Agent**

**Fichiers modifiés :**
- `AgentController.java` : Ajout de la méthode `viewLostObjects()`
- `Agent.fxml` : Liaison du bouton "Voir la liste" avec `onAction="#viewLostObjects"`

**Résultat :**
Lorsque l'agent clique sur **"Voir la liste"**, il est redirigé vers `ViewLostObjects.fxml` qui affiche tous les objets perdus.

---

## 🎨 Design & UX

### **Palette de couleurs**
- 🔴 Rouge Maroc : `#C1272D` (boutons, accents)
- 🟢 Vert Maroc : `#006233` (titres, lignes)
- 🟡 Jaune CAN : `#ffeb3b` (accents secondaires)
- ⚪ Blanc : `#FFFFFF` (cartes, fond)
- ⚫ Gris : `#666666`, `#999999` (textes secondaires)

### **Typographie**
- Titres : **System Bold** 22-28px
- Boutons : **System Bold** 12-13px
- Texte normal : **System** 12-14px

### **Effets visuels**
- Ombres portées (DropShadow) sur les cartes et boutons
- Bordures arrondies (8px) pour un look moderne
- Transitions au survol (via CSS `:hover`)

---

## 🔄 Flux de Navigation

```
Login.fxml
    ↓
Agent.fxml (Page d'accueil agent)
    ↓
[Clic sur "Voir la liste"]
    ↓
ViewLostObjects.fxml (Liste complète)
    ↓
[Options disponibles]
    → Actualiser : Rafraîchir les données
    → Ajouter Nouvel Objet : AddLostObject.fxml
    → Retour : Agent.fxml
    → Déconnexion : Login.fxml
```

---

## 🗂️ Architecture (Pattern MVC)

```
┌─────────────────────────────────────────────┐
│  Vue (FXML)                                 │
│  ViewLostObjects.fxml                       │
│  - TableView                                │
│  - Boutons d'action                         │
└─────────────────┬───────────────────────────┘
                  │
                  ↓
┌─────────────────────────────────────────────┐
│  Contrôleur (Controller)                    │
│  ViewLostObjectsController.java             │
│  - initialize()                             │
│  - configureTableColumns()                  │
│  - loadLostObjects()                        │
└─────────────────┬───────────────────────────┘
                  │
                  ↓
┌─────────────────────────────────────────────┐
│  Service (Business Logic)                   │
│  LostObjectService.java                     │
│  - getAllLostObjects()                      │
└─────────────────┬───────────────────────────┘
                  │
                  ↓
┌─────────────────────────────────────────────┐
│  DAO (Data Access)                          │
│  LostObjectDAO.java                         │
│  - getAllLostObjects()                      │
│  - Requête JPQL                             │
└─────────────────┬───────────────────────────┘
                  │
                  ↓
┌─────────────────────────────────────────────┐
│  Base de Données (MySQL)                    │
│  Table: lost_objects                        │
└─────────────────────────────────────────────┘
```

---

## 📊 Structure du TableView

| Colonne      | Largeur | Propriété Java | Type        | Alignement |
|--------------|---------|----------------|-------------|------------|
| Type         | 120px   | `type`         | String      | Centre     |
| Description  | 200px   | `description`  | String      | Gauche     |
| Date Trouvé  | 110px   | `lostDate`     | LocalDate   | Centre     |
| Zone         | 150px   | `zone`         | String      | Centre     |
| Agent        | 140px   | `agentName`    | String      | Gauche     |
| Téléphone    | 120px   | `phone`        | String      | Centre     |
| Email        | 180px   | `email`        | String      | Gauche     |

**Total largeur :** ~1020px (fenêtre : 1100px)

---

## 🧪 Test de la fonctionnalité

### **1. Lancer l'application**
```bash
mvn clean javafx:run
```

### **2. Se connecter en tant qu'agent**
- Utiliser les identifiants d'un agent existant

### **3. Cliquer sur "Voir la liste"**
- Le TableView doit afficher tous les objets perdus
- Les données doivent être triées par date (plus récents en premier)

### **4. Tester les boutons**
- **Actualiser** : Doit recharger les données
- **Ajouter Nouvel Objet** : Navigue vers le formulaire
- **Retour** : Revenir à la page Agent
- **Déconnexion** : Retour au login

---

## 🔍 Points techniques importants

### **PropertyValueFactory**
```java
colType.setCellValueFactory(new PropertyValueFactory<>("type"));
```
- Utilise la réflexion Java pour appeler automatiquement `getType()`
- Le nom du paramètre doit correspondre exactement à la propriété du modèle

### **ObservableList**
```java
ObservableList<LostObject> list = FXCollections.observableArrayList(objects);
tableView.setItems(list);
```
- Permet la mise à jour automatique du TableView
- JavaFX écoute les changements sur cette liste

### **Gestion de session**
```java
User currentUser = SessionManager.getInstance().getCurrentUser();
```
- Récupère l'utilisateur connecté pour afficher son nom

---

## 🚀 Améliorations futures possibles

1. **Recherche et filtrage**
   - Ajouter un champ de recherche
   - Filtrer par type, zone, date

2. **Pagination**
   - Afficher 20 objets par page
   - Boutons "Précédent" / "Suivant"

3. **Actions sur les lignes**
   - Ajouter une colonne "Actions" avec boutons Modifier/Supprimer
   - Double-clic pour voir les détails

4. **Export de données**
   - Exporter la liste en PDF ou Excel

5. **Statistiques**
   - Graphiques par type d'objet
   - Évolution dans le temps

---

## 📝 Résumé

✅ **Couche DAO** : Méthode `getAllLostObjects()` implémentée  
✅ **Couche Service** : Méthode relais créée  
✅ **Vue FXML** : Interface moderne avec TableView  
✅ **Contrôleur** : Configuration et chargement des données  
✅ **Navigation** : Bouton "Voir la liste" fonctionnel  
✅ **Style** : Design conforme au thème CAN 2025 Maroc  

**Statut : ✅ FONCTIONNEL**

---

*Document créé le 20 décembre 2025*  
*Projet : Telfat w lqina - Gestion des Objets Perdus CAN 2025*

