# Script de nettoyage des fichiers FXML
# Supprime le BOM (Byte Order Mark) qui peut causer l'erreur "Content is not allowed in prolog"

$fxmlFiles = @(
    "src\main\resources\fxml\ViewLostObjectsAdmin.fxml",
    "src\main\resources\fxml\ViewStadiums.fxml",
    "src\main\resources\fxml\AddStadium.fxml",
    "src\main\resources\fxml\AddUsers.fxml",
    "src\main\resources\fxml\Admin.fxml",
    "src\main\resources\fxml\UpdateStadium.fxml",
    "src\main\resources\fxml\UpdateLostObjectAdmin.fxml"
)

Write-Host "🔍 Vérification et nettoyage des fichiers FXML..." -ForegroundColor Cyan
Write-Host ""

foreach ($file in $fxmlFiles) {
    if (Test-Path $file) {
        Write-Host "📄 Traitement de: $file" -ForegroundColor Yellow

        # Lire le contenu avec UTF8 sans BOM
        $content = Get-Content -Path $file -Raw -Encoding UTF8

        # Supprimer le BOM s'il existe
        $content = $content.TrimStart([char]0xFEFF)

        # Écrire le contenu sans BOM
        $utf8NoBom = New-Object System.Text.UTF8Encoding $false
        [System.IO.File]::WriteAllText((Resolve-Path $file), $content, $utf8NoBom)

        Write-Host "   ✅ Nettoyé" -ForegroundColor Green
    } else {
        Write-Host "   ❌ Fichier non trouvé: $file" -ForegroundColor Red
    }
}

Write-Host ""
Write-Host "✨ Nettoyage terminé!" -ForegroundColor Green
Write-Host ""
Write-Host "Vous pouvez maintenant compiler le projet avec:" -ForegroundColor Cyan
Write-Host ".\mvnw.cmd clean compile" -ForegroundColor White

