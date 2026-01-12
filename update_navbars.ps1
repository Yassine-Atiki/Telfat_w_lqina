# Script pour uniformiser les navbars admin
$fxmlPath = "C:\Users\USER PC\IdeaProjects\Telfat w lqina\src\main\resources\fxml"

# Liste des fichiers à traiter avec leur bouton actif
$files = @(
    @{Name="AddStadium.fxml"; ActiveButton="Ajouter un stade"},
    @{Name="ViewStadiums.fxml"; ActiveButton="Liste des Stades"},
    @{Name="ViewLostObjectsAdmin.fxml"; ActiveButton="Objets Perdus"},
    @{Name="UpdateLostObjectAdmin.fxml"; ActiveButton="Objets Perdus"}
)

foreach ($file in $files) {
    $filePath = Join-Path $fxmlPath $file.Name
    Write-Host "Traitement de $($file.Name)..." -ForegroundColor Yellow

    if (Test-Path $filePath) {
        $content = Get-Content $filePath -Raw

        # Ajouter les imports manquants si nécessaire
        if ($content -notmatch 'javafx.scene.control.ScrollPane') {
            $content = $content -replace '(<\?import javafx.scene.control.Label\?>)', "`$1`n<?import javafx.scene.control.ScrollPane?>"
        }
        if ($content -notmatch 'javafx.scene.image.Image') {
            $content = $content -replace '(<\?import javafx.scene.effect.DropShadow\?>)', "`$1`n<?import javafx.scene.image.Image?>`n<?import javafx.scene.image.ImageView?>"
        }

        # Wrapper VBox avec ScrollPane si pas déjà fait
        if ($content -notmatch '<ScrollPane fitToWidth') {
            $content = $content -replace '(<left>\s*<VBox prefWidth="260\.0")', '<left>`n        <ScrollPane fitToWidth="true" hbarPolicy="NEVER" style="-fx-background-color: transparent; -fx-background: transparent;" vbarPolicy="AS_NEEDED">`n            <VBox prefWidth="260.0"'
            $content = $content -replace '(</StackPane>\s*</VBox>\s*</left>)', "`$1`n        </ScrollPane>`n    </left>" -replace '</VBox>\s*</left>', '</VBox>`n        </ScrollPane>`n    </left>'
        }

        Set-Content $filePath $content -Encoding UTF8
        Write-Host "✓ $($file.Name) mis à jour" -ForegroundColor Green
    } else {
        Write-Host "✗ Fichier non trouvé: $filePath" -ForegroundColor Red
    }
}

Write-Host "`nTerminé!" -ForegroundColor Cyan

