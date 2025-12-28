# 📘 Guide : Ajouter une Nouvelle Page avec Sidebar et Header

## 📋 Table des matières
1. [Introduction](#introduction)
2. [Structure générale](#structure-générale)
3. [Étapes détaillées](#étapes-détaillées)
4. [Exemple complet](#exemple-complet)
5. [Points importants](#points-importants)
6. [Troubleshooting](#troubleshooting)

---

## 🎯 Introduction

Ce guide vous explique comment ajouter une nouvelle page FXML dans l'application **Telfat w lqina** en conservant :
- ✅ La **sidebar** (menu latéral gauche)
- ✅ Le **header** (en-tête personnalisé)
- ✅ Le **footer** (pied de page)
- ✅ Le **style cohérent** avec le reste de l'application

**Exemple de référence** : `AddStadium.fxml` et `AddStadiumController.java`

---

## 🏗️ Structure générale

Chaque page de l'application suit cette structure **BorderPane** :

```
┌─────────────────────────────────────────────────┐
│                   TOP (Header)                  │
│  Titre de la page + Description + Stats         │
├──────────┬──────────────────────────────────────┤
│          │                                      │
│   LEFT   │         CENTER                       │
│ (Sidebar)│     (Votre contenu)                  │
│          │                                      │
│  Menu    │    Formulaires, Tableaux,            │
│  Nav.    │    Cards, etc.                       │
│          │                                      │
├──────────┴──────────────────────────────────────┤
│                BOTTOM (Footer)                  │
│         Copyright + Motifs décoratifs           │
└─────────────────────────────────────────────────┘
```

---

## 📝 Étapes détaillées

### Étape 1 : Créer le fichier FXML

**Emplacement** : `src/main/resources/fxml/VotreNouvellePage.fxml`

**Structure de base** :

```xml
<?xml version="1.0" encoding="UTF-8"?>

<?import java.lang.Double?>
<?import javafx.geometry.Insets?>
<?import javafx.scene.control.Button?>
<?import javafx.scene.control.Label?>
<?import javafx.scene.effect.DropShadow?>
<?import javafx.scene.layout.BorderPane?>
<?import javafx.scene.layout.HBox?>
<?import javafx.scene.layout.StackPane?>
<?import javafx.scene.layout.VBox?>
<?import javafx.scene.shape.Polygon?>
<?import javafx.scene.shape.Rectangle?>
<?import javafx.scene.text.Font?>

<BorderPane prefHeight="700.0" prefWidth="1200.0" 
            style="-fx-background-color: #faf8f5;" 
            xmlns="http://javafx.com/javafx/25" 
            xmlns:fx="http://javafx.com/fxml/1" 
            fx:controller="com.firstproject.telfat_w_lqina.controllers.VotreController">

    <!-- SIDEBAR (LEFT) -->
    <left>
        <!-- Copier la sidebar depuis AddStadium.fxml -->
    </left>

    <!-- CONTENU PRINCIPAL (CENTER) -->
    <center>
        <BorderPane style="-fx-background-color: transparent;">
            
            <!-- HEADER (TOP) -->
            <top>
                <!-- Copier et personnaliser le header -->
            </top>

            <!-- VOTRE CONTENU (CENTER) -->
            <center>
                <!-- Votre formulaire, tableau, etc. -->
            </center>
        </BorderPane>
    </center>

    <!-- FOOTER (BOTTOM) -->
    <bottom>
        <!-- Copier le footer depuis AddStadium.fxml -->
    </bottom>

</BorderPane>
```

---

### Étape 2 : Copier la Sidebar (LEFT)

**📍 Copier depuis** : `AddStadium.fxml` (lignes 18-216)

**⚠️ Modifications nécessaires** :

1. **Changer le bouton actif** : Mettez votre nouvelle page en style actif (rouge) et les autres en normal (blanc).

```xml
<!-- Exemple : Si votre page est "Statistiques" -->

<!-- Dashboard - NORMAL -->
<Button alignment="CENTER_LEFT" graphicTextGap="12" onAction="#goToDashboard" 
        prefHeight="45.0" prefWidth="230.0" 
        style="-fx-background-color: white; -fx-background-radius: 8; 
               -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; 
               -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8;" 
        text="  📊  Tableau de Bord">
    <font>
        <Font size="13.0" />
    </font>
</Button>

<!-- Statistiques - ACTIF (votre nouvelle page) -->
<Button alignment="CENTER_LEFT" graphicTextGap="12" 
        prefHeight="45.0" prefWidth="230.0" 
        style="-fx-background-color: linear-gradient(to right, #C1272D, #d63844); 
               -fx-background-radius: 8; -fx-text-fill: white; 
               -fx-cursor: hand; -fx-padding: 0 15;" 
        text="  📈  Statistiques">
    <font>
        <Font name="System Bold" size="13.0" />
    </font>
    <effect>
        <DropShadow color="#C1272D40" offsetY="2.0" radius="4.0" />
    </effect>
</Button>
```

2. **Ajouter les actions de navigation** : Assurez-vous que tous les boutons ont un `onAction` qui pointe vers une méthode de votre contrôleur.

```xml
<Button onAction="#goToDashboard" ... />
<Button onAction="#createUser" ... />
<Button onAction="#viewLostObjects" ... />
<Button onAction="#addStadium" ... />
```

3. **Mettre à jour le label admin** :

```xml
<Label fx:id="labelAdmin" text="Nom Administrateur" ... />
```

---

### Étape 3 : Personnaliser le Header (TOP)

**📍 Copier depuis** : `AddStadium.fxml` (lignes 220-267)

**✏️ Personnaliser** :

```xml
<top>
    <StackPane prefHeight="80.0">
        <!-- Motif zellige (garder tel quel) -->
        <HBox alignment="CENTER_RIGHT" opacity="0.04" spacing="-8" 
              style="-fx-padding: 0 50 0 0;">
            <!-- Polygones décoratifs -->
        </HBox>

        <HBox alignment="CENTER_LEFT" spacing="20" 
              style="-fx-background-color: white; -fx-padding: 20 35; 
                     -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.08), 10, 0, 0, 3);">
            
            <VBox spacing="2">
                <!-- MODIFIER LE TITRE -->
                <Label text="Votre Titre de Page" textFill="#212529">
                    <font>
                        <Font name="System Bold" size="24.0" />
                    </font>
                </Label>
                
                <HBox alignment="CENTER_LEFT" spacing="6">
                    <Rectangle arcHeight="3" arcWidth="3" fill="#C1272D" 
                               height="3.0" width="30.0" />
                    <Rectangle arcHeight="3" arcWidth="3" fill="#006233" 
                               height="3.0" width="8.0" />
                    
                    <!-- MODIFIER LA DESCRIPTION -->
                    <Label text="Description de votre page" textFill="#6c757d">
                        <font>
                            <Font size="13.0" />
                        </font>
                    </Label>
                </HBox>
            </VBox>

            <HBox HBox.hgrow="ALWAYS" />

            <!-- STATISTIQUES OPTIONNELLES -->
            <HBox spacing="25">
                <VBox alignment="CENTER" spacing="2" 
                      style="-fx-background-color: #fef2f2; -fx-background-radius: 8; 
                             -fx-padding: 12 20;">
                    <Label fx:id="stat1Label" text="0" textFill="#C1272D">
                        <font>
                            <Font name="System Bold" size="22.0" />
                        </font>
                    </Label>
                    <Label text="Stat 1" textFill="#C1272D">
                        <font>
                            <Font name="System Bold" size="10.0" />
                        </font>
                    </Label>
                </VBox>
            </HBox>
        </HBox>
    </StackPane>
</top>
```

---

### Étape 4 : Créer votre contenu (CENTER)

**💡 C'est ici que vous ajoutez VOTRE contenu personnalisé !**

#### Option A : Formulaire (comme AddStadium)

```xml
<center>
    <StackPane>
        <!-- Background zellige subtil (optionnel) -->
        <StackPane opacity="0.08" 
                   style="-fx-background-image: url('file:src/main/resources/images/zellige.png'); 
                          -fx-background-repeat: no-repeat; 
                          -fx-background-size: cover; 
                          -fx-background-position: center;" />

        <VBox alignment="CENTER" spacing="30" style="-fx-padding: 45 50;">
            
            <!-- Card avec formulaire -->
            <StackPane maxWidth="600.0">
                <VBox spacing="30" 
                      style="-fx-background-color: white; -fx-background-radius: 15; 
                             -fx-padding: 50 60; 
                             -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 18, 0, 0, 5); 
                             -fx-border-color: #e9ecef; -fx-border-width: 1.5; 
                             -fx-border-radius: 15;">

                    <!-- Icône et titre -->
                    <VBox alignment="CENTER" spacing="12">
                        <Label text="🎯" textFill="#006233">
                            <font>
                                <Font size="42.0" />
                            </font>
                        </Label>
                        <Label text="Titre de votre formulaire" textFill="#212529">
                            <font>
                                <Font name="System Bold" size="22.0" />
                            </font>
                        </Label>
                    </VBox>

                    <!-- Ligne décorative -->
                    <HBox alignment="CENTER" spacing="4">
                        <Rectangle arcHeight="2" arcWidth="2" fill="#C1272D" 
                                   height="2.0" width="60.0" />
                        <Rectangle arcHeight="2" arcWidth="2" fill="#006233" 
                                   height="2.0" width="15.0" />
                        <Rectangle arcHeight="2" arcWidth="2" fill="#C1272D" 
                                   height="2.0" width="60.0" />
                    </HBox>

                    <!-- Vos champs de formulaire -->
                    <VBox spacing="25">
                        <!-- Champ exemple -->
                        <VBox spacing="10">
                            <Label text="Nom du champ" textFill="#495057">
                                <font>
                                    <Font name="System Bold" size="14.0" />
                                </font>
                            </Label>
                            <TextField fx:id="monChamp" prefHeight="48.0" 
                                       promptText="Saisissez ici..." 
                                       style="-fx-background-color: #f8f9fa; 
                                              -fx-border-color: #dee2e6; 
                                              -fx-border-width: 2; 
                                              -fx-border-radius: 8; 
                                              -fx-background-radius: 8; 
                                              -fx-padding: 12 18; 
                                              -fx-font-size: 14px;" />
                        </VBox>

                        <!-- Message d'erreur -->
                        <Label fx:id="errorLabel" textFill="#dc3545" 
                               visible="false" wrapText="true">
                            <font>
                                <Font name="System Bold" size="12.0" />
                            </font>
                        </Label>

                        <!-- Boutons d'action -->
                        <HBox alignment="CENTER" spacing="15" 
                              style="-fx-padding: 10 0 0 0;">
                            <Button onAction="#annuler" prefHeight="48.0" 
                                    prefWidth="200.0" 
                                    style="-fx-background-color: white; 
                                           -fx-border-color: #dee2e6; 
                                           -fx-border-width: 2; 
                                           -fx-border-radius: 8; 
                                           -fx-background-radius: 8; 
                                           -fx-text-fill: #495057; 
                                           -fx-cursor: hand;" 
                                    text="↩️  Annuler">
                                <font>
                                    <Font name="System Bold" size="14.0" />
                                </font>
                            </Button>

                            <Button onAction="#enregistrer" prefHeight="48.0" 
                                    prefWidth="200.0" 
                                    style="-fx-background-color: linear-gradient(to right, #006233, #007d42); 
                                           -fx-background-radius: 8; 
                                           -fx-cursor: hand; 
                                           -fx-text-fill: white;" 
                                    text="✓  Enregistrer">
                                <font>
                                    <Font name="System Bold" size="14.0" />
                                </font>
                                <effect>
                                    <DropShadow color="#00623340" offsetY="3.0" radius="8.0" />
                                </effect>
                            </Button>
                        </HBox>
                    </VBox>
                </VBox>
            </StackPane>
        </VBox>
    </StackPane>
</center>
```

#### Option B : Tableau (comme ViewLostObjects)

```xml
<center>
    <VBox spacing="20" style="-fx-padding: 30;">
        
        <!-- Titre -->
        <Label text="Liste des éléments" textFill="#212529">
            <font>
                <Font name="System Bold" size="20.0" />
            </font>
        </Label>

        <!-- TableView -->
        <TableView fx:id="tableView" prefHeight="400.0">
            <columns>
                <TableColumn fx:id="colonne1" text="Colonne 1" prefWidth="200" />
                <TableColumn fx:id="colonne2" text="Colonne 2" prefWidth="200" />
                <TableColumn fx:id="colonne3" text="Colonne 3" prefWidth="200" />
            </columns>
        </TableView>

        <!-- Boutons d'action -->
        <HBox spacing="15">
            <Button text="Ajouter" 
                    style="-fx-background-color: #006233; 
                           -fx-text-fill: white; 
                           -fx-padding: 12 30; 
                           -fx-border-radius: 8; 
                           -fx-background-radius: 8;" />
            <Button text="Modifier" 
                    style="-fx-background-color: #C1272D; 
                           -fx-text-fill: white; 
                           -fx-padding: 12 30; 
                           -fx-border-radius: 8; 
                           -fx-background-radius: 8;" />
        </HBox>
    </VBox>
</center>
```

#### Option C : Cards (comme Admin)

```xml
<center>
    <StackPane>
        <VBox alignment="CENTER" spacing="35" style="-fx-padding: 45 50;">
            
            <VBox alignment="CENTER" spacing="8">
                <Label text="Actions Rapides" textFill="#495057">
                    <font>
                        <Font name="System Bold" size="18.0" />
                    </font>
                </Label>
                <HBox alignment="CENTER" spacing="4">
                    <Rectangle fill="#C1272D" height="3.0" width="40.0" />
                    <Rectangle fill="#006233" height="3.0" width="12.0" />
                    <Rectangle fill="#C1272D" height="3.0" width="40.0" />
                </HBox>
            </VBox>

            <!-- Grid de cards -->
            <HBox alignment="CENTER" spacing="30">
                
                <!-- Card 1 -->
                <VBox alignment="CENTER" spacing="18" 
                      style="-fx-background-color: white; 
                             -fx-background-radius: 12; 
                             -fx-padding: 30 25; 
                             -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 14, 0, 0, 4); 
                             -fx-border-color: #e9ecef; 
                             -fx-border-width: 1.5; 
                             -fx-border-radius: 12;" 
                      prefHeight="220.0" prefWidth="260.0">
                    
                    <Label text="📊" textFill="#006233">
                        <font>
                            <Font size="42.0" />
                        </font>
                    </Label>

                    <VBox alignment="CENTER" spacing="6">
                        <Label text="Titre Card" textFill="#212529">
                            <font>
                                <Font name="System Bold" size="18.0" />
                            </font>
                        </Label>
                        <Label text="Description" textFill="#6c757d">
                            <font>
                                <Font size="13.0" />
                            </font>
                        </Label>
                    </VBox>

                    <Button onAction="#action1" prefHeight="44.0" prefWidth="210.0" 
                            style="-fx-background-color: linear-gradient(to right, #006233, #007d42); 
                                   -fx-background-radius: 8; 
                                   -fx-cursor: hand; 
                                   -fx-text-fill: white;" 
                            text="Action">
                        <font>
                            <Font name="System Bold" size="14.0" />
                        </font>
                    </Button>
                </VBox>

                <!-- Ajoutez d'autres cards ici -->
            </HBox>
        </VBox>
    </StackPane>
</center>
```

---

### Étape 5 : Copier le Footer (BOTTOM)

**📍 Copier depuis** : `AddStadium.fxml` (lignes 406-455)

**⚠️ Garder tel quel** : Le footer est identique pour toutes les pages.

```xml
<bottom>
    <StackPane prefHeight="50.0">
        <!-- Motif zellige -->
        <HBox alignment="CENTER" opacity="0.04" spacing="15">
            <Polygon fill="#C1272D" rotate="0">
                <!-- Points du polygone -->
            </Polygon>
            <!-- Autres polygones -->
        </HBox>

        <HBox alignment="CENTER" 
              style="-fx-background-color: white; 
                     -fx-border-color: #e9ecef; 
                     -fx-border-width: 1.5 0 0 0;">
            <VBox alignment="CENTER" spacing="4" style="-fx-padding: 8 0;">
                <HBox alignment="CENTER" spacing="4">
                    <Rectangle fill="#C1272D" height="2.5" width="25.0" />
                    <Rectangle fill="#006233" height="2.5" width="6.0" />
                    <Rectangle fill="#C1272D" height="2.5" width="25.0" />
                </HBox>
                <Label text="© 2025 Telfat w lqina - CAN 2025 Maroc - Tous droits réservés" 
                       textFill="#6c757d">
                    <font>
                        <Font size="11.0" />
                    </font>
                </Label>
            </VBox>
        </HBox>
    </StackPane>
</bottom>
```

---

### Étape 6 : Créer le Contrôleur Java

**Emplacement** : `src/main/java/com/firstproject/telfat_w_lqina/controllers/VotreController.java`

**Structure de base** :

```java
package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.util.LogoutUtil;
import com.firstproject.telfat_w_lqina.util.NavigationUtil;
import com.firstproject.telfat_w_lqina.util.SessionManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
// Ajoutez d'autres imports selon vos besoins

import java.io.IOException;

public class VotreController {
    
    // ========== FXML COMPONENTS ==========
    
    @FXML
    private Label labelAdmin;  // Pour afficher le nom de l'admin
    
    @FXML
    private TextField monChamp;  // Exemple de champ
    
    @FXML
    private Label errorLabel;  // Pour les messages d'erreur/succès
    
    // Ajoutez d'autres composants FXML
    
    
    // ========== INITIALIZATION ==========
    
    @FXML
    public void initialize() {
        // Récupérer l'utilisateur connecté
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            labelAdmin.setText(currentUser.getUsername());
        }
        
        // Autres initialisations (charger des données, etc.)
    }
    
    
    // ========== SIDEBAR NAVIGATION ==========
    
    @FXML
    public void goToDashboard(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/Admin.fxml");
        // Ou Agent.fxml selon le type d'utilisateur
    }
    
    @FXML
    public void createUser(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/AddUsers.fxml");
    }
    
    @FXML
    public void viewLostObjects(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/ViewLostObjects.fxml");
    }
    
    @FXML
    public void addStadium(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/AddStadium.fxml");
    }
    
    @FXML
    public void logout(ActionEvent event) throws IOException {
        LogoutUtil.logout(event);
    }
    
    
    // ========== YOUR BUSINESS LOGIC ==========
    
    @FXML
    public void enregistrer(ActionEvent event) {
        // Réinitialiser les erreurs
        errorLabel.setVisible(false);
        
        // Récupérer les données du formulaire
        String valeur = monChamp.getText().trim();
        
        // Validation
        if (valeur.isEmpty()) {
            showError("⚠️ Ce champ est requis!");
            return;
        }
        
        try {
            // Votre logique métier ici
            // Exemple : Sauvegarder dans la BD
            
            showSuccess("✓ Enregistrement réussi!");
            
            // Optionnel : Réinitialiser le formulaire
            monChamp.clear();
            
        } catch (Exception e) {
            showError("❌ Erreur : " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    @FXML
    public void annuler(ActionEvent event) throws IOException {
        goToDashboard(event);
    }
    
    
    // ========== HELPER METHODS ==========
    
    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setTextFill(javafx.scene.paint.Color.web("#dc3545"));
        errorLabel.setVisible(true);
    }
    
    private void showSuccess(String message) {
        errorLabel.setText(message);
        errorLabel.setTextFill(javafx.scene.paint.Color.web("#28a745"));
        errorLabel.setVisible(true);
    }
}
```

---

### Étape 7 : Ajouter la navigation depuis une autre page

**Dans le contrôleur de la page d'origine** (ex: `AdminController.java`) :

```java
@FXML
public void allerVersNouvellePage(ActionEvent event) throws IOException {
    NavigationUtil.navigate(event, "/fxml/VotreNouvellePage.fxml");
}
```

**Dans le FXML de la page d'origine** (ex: `Admin.fxml`) :

```xml
<Button onAction="#allerVersNouvellePage" text="Ma Nouvelle Page" ... />
```

---

## 🎨 Palette de couleurs

Utilisez ces couleurs pour la cohérence visuelle :

| Élément | Couleur | Code Hex |
|---------|---------|----------|
| **Vert principal** | Drapeau marocain | `#006233` |
| **Rouge principal** | Drapeau marocain | `#C1272D` |
| **Blanc cassé** | Fond | `#faf8f5` |
| **Blanc** | Cards | `#ffffff` |
| **Gris clair** | Bordures | `#e9ecef` |
| **Gris foncé** | Texte | `#212529` |
| **Gris moyen** | Texte secondaire | `#6c757d` |
| **Vert foncé** | Hover | `#007d42` |
| **Rouge foncé** | Hover | `#d63844` |
| **Vert succès** | Messages | `#28a745` |
| **Rouge erreur** | Messages | `#dc3545` |

---

## 📚 Exemple complet : Page "Statistiques"

### 1. Créer `Statistics.fxml`

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!-- Imports... -->

<BorderPane prefHeight="700.0" prefWidth="1200.0" 
            style="-fx-background-color: #faf8f5;" 
            xmlns:fx="http://javafx.com/fxml/1" 
            fx:controller="com.firstproject.telfat_w_lqina.controllers.StatisticsController">

    <!-- LEFT: Copier la sidebar de AddStadium.fxml -->
    <!-- Modifier le bouton "Statistiques" pour qu'il soit actif -->
    
    <center>
        <BorderPane style="-fx-background-color: transparent;">
            
            <!-- TOP: Header personnalisé -->
            <top>
                <StackPane prefHeight="80.0">
                    <!-- Motif... -->
                    <HBox>
                        <VBox>
                            <Label text="Statistiques et Rapports" />
                            <Label text="Visualisez les données de l'application" />
                        </VBox>
                        
                        <!-- Stats -->
                        <HBox>
                            <VBox>
                                <Label fx:id="totalObjets" text="0" />
                                <Label text="Total Objets" />
                            </VBox>
                        </HBox>
                    </HBox>
                </StackPane>
            </top>

            <!-- CENTER: Votre contenu -->
            <center>
                <VBox spacing="30" style="-fx-padding: 40;">
                    
                    <!-- Titre -->
                    <Label text="Rapports mensuels" />
                    
                    <!-- Grid de statistiques -->
                    <HBox spacing="20">
                        <!-- Card stat 1 -->
                        <VBox style="-fx-background-color: white; 
                                     -fx-padding: 30; 
                                     -fx-background-radius: 12;">
                            <Label text="📊" />
                            <Label text="150" />
                            <Label text="Objets retrouvés" />
                        </VBox>
                        
                        <!-- Card stat 2 -->
                        <!-- ... -->
                    </HBox>
                    
                    <!-- Graphique ou tableau -->
                    <TableView fx:id="statsTable">
                        <!-- Colonnes... -->
                    </TableView>
                    
                </VBox>
            </center>
        </BorderPane>
    </center>

    <!-- BOTTOM: Copier le footer de AddStadium.fxml -->

</BorderPane>
```

### 2. Créer `StatisticsController.java`

```java
package com.firstproject.telfat_w_lqina.controllers;

import com.firstproject.telfat_w_lqina.models.User;
import com.firstproject.telfat_w_lqina.util.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.io.IOException;

public class StatisticsController {
    
    @FXML
    private Label labelAdmin;
    
    @FXML
    private Label totalObjets;
    
    @FXML
    private TableView<?> statsTable;
    
    @FXML
    public void initialize() {
        User currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser != null) {
            labelAdmin.setText(currentUser.getUsername());
        }
        
        // Charger les statistiques
        loadStatistics();
    }
    
    private void loadStatistics() {
        // Logique pour charger les stats depuis la BD
        totalObjets.setText("150");
    }
    
    // Méthodes de navigation
    @FXML
    public void goToDashboard(ActionEvent event) throws IOException {
        NavigationUtil.navigate(event, "/fxml/Admin.fxml");
    }
    
    @FXML
    public void logout(ActionEvent event) throws IOException {
        LogoutUtil.logout(event);
    }
    
    // Autres méthodes...
}
```

### 3. Ajouter le lien dans `AdminController.java`

```java
@FXML
public void viewStatistics(ActionEvent event) throws IOException {
    NavigationUtil.navigate(event, "/fxml/Statistics.fxml");
}
```

### 4. Activer le bouton dans `Admin.fxml`

```xml
<Button onAction="#viewStatistics" text="  📈  Statistiques" ... />
```

---

## ⚠️ Points importants

### 1. **Cohérence visuelle**
- ✅ Utilisez toujours les mêmes couleurs (vert #006233, rouge #C1272D)
- ✅ Gardez les mêmes tailles de police
- ✅ Utilisez les mêmes espacements (padding, spacing)
- ✅ Conservez les effets d'ombre (DropShadow)

### 2. **Structure BorderPane**
```
BorderPane (root)
├── LEFT: Sidebar (260px)
├── CENTER: BorderPane
│   ├── TOP: Header (80px)
│   ├── CENTER: Votre contenu
│   └── BOTTOM: (vide)
└── BOTTOM: Footer (50px)
```

### 3. **Bouton actif dans la sidebar**
- Le bouton de la page actuelle doit avoir :
  - Background : `linear-gradient(to right, #C1272D, #d63844)`
  - Texte : blanc
  - Font : Bold
  - Effet : DropShadow rouge

### 4. **fx:id nécessaires**
- `labelAdmin` : Pour afficher le nom de l'utilisateur
- `errorLabel` : Pour les messages (optionnel)
- Vos composants spécifiques (TextField, TableView, etc.)

### 5. **Méthodes de navigation obligatoires**
```java
@FXML public void goToDashboard(ActionEvent event) throws IOException
@FXML public void createUser(ActionEvent event) throws IOException
@FXML public void viewLostObjects(ActionEvent event) throws IOException
@FXML public void logout(ActionEvent event) throws IOException
```

### 6. **Initialize pattern**
```java
@FXML
public void initialize() {
    // 1. Récupérer l'utilisateur
    User currentUser = SessionManager.getInstance().getCurrentUser();
    labelAdmin.setText(currentUser.getUsername());
    
    // 2. Charger les données
    loadData();
    
    // 3. Configurer les listeners
    setupListeners();
}
```

---

## 🐛 Troubleshooting

### Problème 1 : "Location is required"
**Erreur** : `javafx.fxml.LoadException: Location is required`

**Solution** : Vérifiez le chemin du fichier FXML
```java
NavigationUtil.navigate(event, "/fxml/VotrePage.fxml");
// Le "/" au début est OBLIGATOIRE
```

### Problème 2 : "Controller not found"
**Erreur** : `javafx.fxml.LoadException: Controller ... not found`

**Solution** : Vérifiez le `fx:controller` dans le FXML
```xml
fx:controller="com.firstproject.telfat_w_lqina.controllers.VotreController"
<!-- Le package COMPLET est requis -->
```

### Problème 3 : "Cannot resolve method"
**Erreur** : Les méthodes `onAction` ne sont pas reconnues

**Solution** : Assurez-vous que :
1. Les méthodes sont annotées `@FXML`
2. Les méthodes acceptent `ActionEvent event`
3. Les méthodes lancent `IOException` si elles naviguent

```java
@FXML
public void maMethode(ActionEvent event) throws IOException {
    // ...
}
```

### Problème 4 : NullPointerException au chargement
**Erreur** : `java.lang.NullPointerException` dans `initialize()`

**Solution** : Vérifiez que :
1. Tous les `fx:id` dans le FXML correspondent aux `@FXML` dans le contrôleur
2. Les composants sont initialisés AVANT d'être utilisés
3. `SessionManager.getInstance().getCurrentUser()` ne retourne pas `null`

### Problème 5 : Sidebar ne s'affiche pas
**Erreur** : La sidebar est vide ou invisible

**Solution** : 
1. Vérifiez que `<left>` est bien dans le `BorderPane` racine
2. Vérifiez le `prefWidth="260.0"` de la VBox
3. Vérifiez qu'il n'y a pas d'erreur dans les imports

### Problème 6 : Boutons pas alignés
**Erreur** : Les boutons de la sidebar sont mal alignés

**Solution** : Vérifiez :
```xml
<Button alignment="CENTER_LEFT" 
        graphicTextGap="12" 
        prefHeight="45.0" 
        prefWidth="230.0" ... />
```
Tous les boutons doivent avoir la même `prefWidth` et `prefHeight`.

---

## 📋 Checklist avant de tester

Avant de lancer votre nouvelle page, vérifiez :

- [ ] Le fichier FXML existe dans `src/main/resources/fxml/`
- [ ] Le contrôleur existe dans `src/main/java/.../controllers/`
- [ ] Le `fx:controller` dans le FXML pointe vers le bon contrôleur
- [ ] La sidebar est copiée et le bon bouton est actif (rouge)
- [ ] Le header est personnalisé avec le bon titre
- [ ] Le footer est copié tel quel
- [ ] Tous les `fx:id` sont déclarés dans le contrôleur avec `@FXML`
- [ ] La méthode `initialize()` existe et configure `labelAdmin`
- [ ] Toutes les méthodes de navigation existent (`goToDashboard`, etc.)
- [ ] Les boutons `onAction` pointent vers les bonnes méthodes
- [ ] Le fichier est ajouté à Git si vous utilisez un versioning

---

## 🎯 Résumé rapide

**Pour ajouter une nouvelle page :**

1. **Copier** `AddStadium.fxml` → `VotreNouvellePage.fxml`
2. **Modifier** le `fx:controller` en haut du fichier
3. **Personnaliser** le header (titre + description)
4. **Changer** le bouton actif dans la sidebar
5. **Remplacer** le contenu central par votre formulaire/tableau/cards
6. **Créer** le contrôleur Java correspondant
7. **Ajouter** la méthode `initialize()` avec `labelAdmin`
8. **Implémenter** les méthodes de navigation
9. **Ajouter** le lien depuis une autre page
10. **Tester** ! 🚀

---

## 📚 Ressources

- **Fichiers de référence** :
  - `AddStadium.fxml` : Formulaire avec sidebar
  - `ViewLostObjects.fxml` : TableView avec sidebar
  - `Admin.fxml` : Cards avec sidebar
  - `AddStadiumController.java` : Contrôleur complet

- **Utilitaires** :
  - `NavigationUtil.java` : Pour la navigation entre pages
  - `SessionManager.java` : Pour gérer la session utilisateur
  - `LogoutUtil.java` : Pour la déconnexion

- **Documentation externe** :
  - [JavaFX Documentation](https://openjfx.io/)
  - [FXML Reference](https://docs.oracle.com/javafx/2/api/javafx/fxml/doc-files/introduction_to_fxml.html)

---

**📅 Dernière mise à jour** : 22 décembre 2025  
**✍️ Auteur** : GitHub Copilot  
**🏆 Projet** : Telfat w lqina - CAN 2025 Maroc

---

**💡 Conseil final** : Commencez toujours par copier une page existante similaire à ce que vous voulez créer, puis modifiez progressivement. C'est plus rapide et moins sujet aux erreurs que de partir de zéro !

**🎉 Bon développement !**

