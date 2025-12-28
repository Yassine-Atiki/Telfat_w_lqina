# 🏟️ Ajout du champ Stade dans le formulaire d'ajout d'utilisateur

## 📋 Résumé des modifications

Ce document décrit les modifications apportées pour permettre à l'administrateur d'associer un agent à un stade lors de sa création via le formulaire `AddUsers.fxml`.

---

## 🎯 Objectif

Permettre à l'administrateur de :
- **Sélectionner un stade** parmi la liste des stades existants dans la base de données
- **Associer ce stade à un agent** lors de sa création
- **Laisser le champ vide** (optionnel) si aucun stade n'est sélectionné

---

## 🔧 Modifications effectuées

### 1. **Modèle Agent** (`Agent.java`)

#### Modifications :
- ✅ Changement de `nullable = false` à `nullable = true` pour permettre la création d'un agent sans stade
- ✅ Ajout des **getters et setters** pour le champ `stadium`

```java
@ManyToOne
@JoinColumn(name = "agent_stadium_id", nullable = true)  // ← Maintenant optionnel
private Stadium stadium;

// Getters et Setters
public Stadium getStadium() {
    return stadium;
}

public void setStadium(Stadium stadium) {
    this.stadium = stadium;
}
```

---

### 2. **Modèle Stadium** (`Stadium.java`)

#### Modifications :
- ✅ Ajout du **getter pour l'ID** du stade

```java
public int getId() {
    return id;
}
```

---

### 3. **DAO Stadium** (`AddStadiumDAO.java`)

#### Modifications :
- ✅ Ajout de la méthode `getAllStadiums()` pour récupérer tous les stades de la base de données

```java
public java.util.List<Stadium> getAllStadiums() {
    EntityManager em = getEntityManager();
    try {
        return em.createQuery("SELECT s FROM Stadium s", Stadium.class)
                .getResultList();
    } finally {
        em.close();
    }
}
```

---

### 4. **Vue FXML** (`AddUsers.fxml`)

#### Modifications :
- ✅ Transformation du champ "Type d'utilisateur" : suppression de `GridPane.columnSpan="2"`
- ✅ Ajout d'un nouveau **ComboBox** pour sélectionner un stade
- ✅ Placement en **2 colonnes** : Type d'utilisateur (colonne 0) et Stade associé (colonne 1)

```xml
<!-- Type d'utilisateur -->
<VBox spacing="8" GridPane.columnIndex="0" GridPane.rowIndex="2">
    <HBox spacing="5">
        <Label text="Type d'utilisateur" .../>
        <Label text="*" textFill="#C1272D" .../>
    </HBox>
    <ComboBox fx:id="userTypeComboBox" promptText="Sélectionner le rôle" .../>
</VBox>

<!-- Stade associé (pour les agents uniquement) -->
<VBox spacing="8" GridPane.columnIndex="1" GridPane.rowIndex="2">
    <HBox spacing="5">
        <Label text="Stade associé" .../>
        <Label text="(optionnel)" textFill="#999999" .../>
    </HBox>
    <ComboBox fx:id="stadiumComboBox" promptText="Sélectionner un stade" .../>
</VBox>
```

---

### 5. **Contrôleur** (`AddUsersController.java`)

#### Modifications :
- ✅ Ajout du champ `@FXML` pour le `stadiumComboBox`
- ✅ Ajout de l'instance `AddStadiumDAO` pour accéder aux stades
- ✅ Modification de la méthode `initialize()` pour :
  - Charger les stades disponibles depuis la base de données
  - Configurer l'affichage du ComboBox pour montrer le nom du stade
- ✅ Ajout de la méthode `loadStadiums()` pour charger les stades
- ✅ Modification de la méthode `createUser()` pour associer le stade sélectionné à l'agent

```java
@FXML
private ComboBox<Stadium> stadiumComboBox;

AddStadiumDAO stadiumDAO = new AddStadiumDAO();

public void initialize() {
    userTypeComboBox.getItems().addAll("ADMIN", "AGENT");
    
    // Charger les stades disponibles
    loadStadiums();
    
    // Configurer l'affichage des stades
    stadiumComboBox.setCellFactory(...);
    stadiumComboBox.setButtonCell(...);
}

private void loadStadiums() {
    try {
        List<Stadium> stadiums = stadiumDAO.getAllStadiums();
        stadiumComboBox.getItems().clear();
        stadiumComboBox.getItems().addAll(stadiums);
    } catch (Exception e) {
        Alerts.errorAlert("Erreur", "Erreur de chargement", 
                         "Impossible de charger les stades.");
        e.printStackTrace();
    }
}

// Dans createUser() - pour les agents :
if (stadiumComboBox.getValue() != null) {
    agent.setStadium(stadiumComboBox.getValue());
}
```

---

## 🎨 Interface utilisateur

Le formulaire affiche maintenant :

```
┌─────────────────────────────────────────────────────────────┐
│  Nom d'utilisateur *        │  Mot de passe *               │
├─────────────────────────────────────────────────────────────┤
│  Adresse email *            │  Numéro de téléphone *        │
├─────────────────────────────────────────────────────────────┤
│  Type d'utilisateur *       │  Stade associé (optionnel)    │
│  [ADMIN ▼]                  │  [Sélectionner un stade ▼]    │
└─────────────────────────────────────────────────────────────┘
```

---

## ✅ Fonctionnalités

1. **Chargement automatique** des stades depuis la base de données au démarrage du formulaire
2. **Affichage du nom du stade** dans le menu déroulant (au lieu de l'objet Stadium)
3. **Association optionnelle** : Le champ peut rester vide
4. **Message de succès** lors de la création réussie d'un agent
5. **Gestion des erreurs** si le chargement des stades échoue

---

## 🔍 Points importants

- ⚠️ Le champ **Stade associé** est **optionnel** (`nullable = true`)
- 📊 Les stades sont chargés **dynamiquement** depuis la base de données
- 🎯 L'association stade-agent se fait **uniquement pour les agents**, pas pour les admins
- ✨ Le ComboBox affiche le **nom du stade** de façon conviviale

---

## 📝 Notes pour les développeurs

- Si vous ajoutez un nouveau stade, rafraîchissez la page pour le voir dans la liste
- La relation entre Agent et Stadium est de type **ManyToOne**
- Le champ est stocké dans la colonne `agent_stadium_id` de la table `agent`

---

**Date de modification** : 23 décembre 2025  
**Développeur** : GitHub Copilot  
**Version** : 1.0

