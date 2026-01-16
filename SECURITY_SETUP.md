# Configuration Sécurisée de la Base de Données

## ⚠️ IMPORTANT - Configuration Initiale

Ce projet utilise un fichier `database.properties` pour stocker les informations de connexion à la base de données. **Ce fichier ne doit JAMAIS être commité dans Git** car il contient des informations sensibles.

## 📋 Instructions de Configuration

### 1. Copier le fichier template

```bash
cp .env.example src/main/resources/database.properties
```

### 2. Modifier avec vos vraies valeurs

Ouvrez `src/main/resources/database.properties` et remplacez les valeurs par vos vraies informations :

```properties
# Pour développement local
db.url=jdbc:mysql://localhost:3306/telfat_w_lqina?createDatabaseIfNotExist=true&useSSL=false&serverTimezone=UTC
db.username=root
db.password=VOTRE_VRAI_MOT_DE_PASSE

# Pour production (Railway, AWS, etc.)
# Décommentez et remplissez avec vos vraies valeurs
```

### 3. Vérifier que le fichier est ignoré

```bash
git status
```

Le fichier `database.properties` ne doit PAS apparaître dans la liste des fichiers modifiés.

## 🔒 Sécurité

- ✅ Le fichier `database.properties` est dans `.gitignore`
- ✅ Utilisez `.env.example` comme template
- ✅ Ne partagez JAMAIS vos mots de passe dans le code
- ✅ Utilisez des variables d'environnement pour la production

## 🚨 En cas de fuite de credentials

Si vous avez accidentellement commité des credentials :

1. **Changez immédiatement vos mots de passe** sur Railway/votre service
2. Supprimez le fichier de l'historique Git :
   ```bash
   git filter-branch --force --index-filter \
   "git rm --cached --ignore-unmatch src/main/resources/database.properties" \
   --prune-empty --tag-name-filter cat -- --all
   ```
3. Force push (⚠️ attention si vous travaillez en équipe) :
   ```bash
   git push origin --force --all
   ```

## 📝 Pour les nouveaux développeurs

1. Clonez le projet
2. Copiez `.env.example` vers `src/main/resources/database.properties`
3. Demandez les credentials au chef de projet (ne les partagez pas publiquement)
4. Configurez votre base de données locale
