# Script pour uniformiser TOUTES les navbars admin
Write-Host "`n🔧 UNIFORMISATION COMPLÈTE DES NAVBARS ADMIN" -ForegroundColor Cyan
Write-Host "=" * 50 -ForegroundColor Cyan

$fxmlPath = "C:\Users\USER PC\IdeaProjects\Telfat w lqina\src\main\resources\fxml"

# Navbar de base (sera adaptée pour chaque page)
$navbarTemplate = @'
    <left>
        <ScrollPane fitToWidth="true" hbarPolicy="NEVER" style="-fx-background-color: transparent; -fx-background: transparent;" vbarPolicy="AS_NEEDED">
            <VBox prefWidth="260.0" style="-fx-background-color: linear-gradient(to bottom, #ffffff, #faf8f5); -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 12, 0, 3, 0);">
                <!-- Navigation Menu -->
                <VBox spacing="8" style="-fx-padding: 20 15;">
                    <!-- Dashboard -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" onAction="#goToDashboard" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8;" text="  📊  Tableau de Bord">
                        <font><Font size="13.0" /></font>
                    </Button>

                    <!-- Créer Utilisateur -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" onAction="#createUser" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8;" text="  👤  Créer Utilisateur">
                        <font><Font size="13.0" /></font>
                    </Button>

                    <!-- Objets Perdus -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" onAction="#viewLostObjects" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8;" text="  📋  Objets Perdus">
                        <font><Font size="13.0" /></font>
                    </Button>

                    <!-- Statistiques -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8." text="  📈  Statistiques">
                        <font><Font size="13.0" /></font>
                    </Button>

                    <!-- Rapports -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8;" text="  📑  Rapports">
                        <font><Font size="13.0" /></font>
                    </Button>

                    <!-- Ajouter stade -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" onAction="#addStadium" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8." text=" ⚽  Ajouter un stade">
                        <font><Font size="13.0" /></font>
                    </Button>

                    <!-- Liste des stades -->
                    <Button alignment="CENTER_LEFT" graphicTextGap="12" onAction="#goToStadiumList" prefHeight="45.0" prefWidth="230.0" style="-fx-background-color: white; -fx-background-radius: 8; -fx-text-fill: #495057; -fx-cursor: hand; -fx-padding: 0 15; -fx-border-color: #e9ecef; -fx-border-width: 1.5; -fx-border-radius: 8;" text="Liste des Stades">
                        <graphic>
                            <ImageView fitWidth="18" fitHeight="18" preserveRatio="true">
                                <image><Image url="@../images/stade.png" /></image>
                            </ImageView>
                        </graphic>
                        <font><Font size="13.0" /></font>
                    </Button>
                </VBox>
            </VBox>
        </ScrollPane>
    </left>
'@

Write-Host "`n✅ Script créé avec succès!" -ForegroundColor Green
Write-Host "Note: Ce script sert de référence pour les corrections manuelles" -ForegroundColor Yellow

