# IDEELL SOCIAL MEDIA #

This is root folder for Ideell's all services. Short description for subfolders

* api - Spring Boot REST API using JSON. Security with JWT provided by Auth0
* db - Local database folder for testing
* doc - Documentation including graphic profile
* www - 4 separate projects (Ideell start page and PWA apps for Vi, Konst, Kvitter)

### Local Setup ###

Try the following short description of how to start your local environment

# Install Java 15, MongoDB, MariaDB, Eclipse/Intellij (install Lombok for Eclipse), Maven, Npm
# Add admin to mongo:
## Start mongo with "> mongo"
## Show databases "show dbs"
## Create Ideell database "use ideell"
## Create admin database "use admin"
## Show users "db.getUsers();"
## Create Ideell admin user "db.createUser({ user: "ideell", pwd: "ideell", roles: []});"
## Grant Ideell admin role "db.grantRolesToUser("ideell", [{role: "readWrite", db: "ideell"}])"
# Clone repo at https://github.com/AIMMOTH/ideell 
# Obtain copy of Stockholm Boot (contact Aimmoth@Github) and install locally
# Import Maven project
# Set Stockholm Boot as dependency to Maven project
# Create project for Ideell start page, Vi, Konst and Kvitter
# Install Angular CLI in Vi, Konst and Kvitter (npm install -g @angular/cli)

### Run Locally ###

# Start MongoDB (in root folder, mongod --dbpath db)
# Start backend in Eclipse/Intellij
# Run you selected app with Angular Cli (ng serve)

### Deploy ###

# Copy start page into api/src/main/resources/static
# Build Vi/Konst/Kvitter (ng --prod build)
# Copy Vi/Konst/Kvitter build into api/src/main/resources/{app}
# Make Backend build (mvn package)
# Login to ElastX
# Select correct environment
# Upload build JAR-file (found in api/target)

### Contribution guidelines ###

* If you touch code, try to clean up and make it look better than before you started
* If you create something new, use a well known name for main logic and package with same name for smaller objects

### What to work with ###

Use the following priority

# Merge your PRs that are accepted
# Check for PRs to review
# Continue your features
# Check for selected issues that are specified well enough for you to start work with

### Halp! ###

You can contact Aimmoth@github
