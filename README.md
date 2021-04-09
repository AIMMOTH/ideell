# IDEELL SOCIAL MEDIA #

This is root folder for Ideell's all services. Short description for subfolders

* api - Spring Boot REST API using JSON. Security with JWT provided by Auth0
* db - Local database folder for testing
* doc - Documentation including graphic profile
* www - 4 separate projects (Ideell start page and PWA apps for Vi, Konst, Kvitter)

### Local Setup ###

Try the following short description of how to start your local environment

# Install Java 15, MongoDB, MariaDB, Eclipse/Intellij (install Lombok for Eclipse), Maven, Npm

1. Add admin to mongo:
    1. Start mongo with "> mongo"
    1. Show databases "show dbs"
    1. Create Ideell database "use ideell"
    1. Create admin database "use admin"
    1. Show users "db.getUsers();"
    1. Create Ideell admin user "db.createUser({ user: "ideell", pwd: "ideell", roles: []});"
    1. Grant Ideell admin role "db.grantRolesToUser("ideell", [{role: "readWrite", db: "ideell"}])"
2. Clone repo at https://github.com/AIMMOTH/ideell 
3. Obtain copy of Stockholm Boot (contact Aimmoth@Github) and install locally
4. Import Maven project
5. Set Stockholm Boot as dependency to Maven project
6. Create project for Ideell start page, Vi, Konst and Kvitter
7. Install Angular CLI in Vi, Konst and Kvitter (npm install -g @angular/cli)

### Run Locally ###

1. Start MongoDB (in root folder, mongod --dbpath db)
1. Start backend in Eclipse/Intellij
1. Run you selected app with Angular Cli (ng serve)

### Deploy ###

1. Copy start page into api/src/main/resources/static
1. Build Vi/Konst/Kvitter (ng --prod build)
1. Copy Vi/Konst/Kvitter build into api/src/main/resources/{app}
1. Make Backend build (mvn package)
1. Login to ElastX
1. Select correct environment
1. Upload build JAR-file (found in api/target)

### Contribution guidelines ###

* If you touch code, try to clean up and make it look better than before you started
* If you create something new, use a well known name for main logic and package with same name for smaller objects

### What to work with ###

Use the following priority

1. Merge your PRs that are accepted
1. Check for PRs to review
1. Continue your features
1. Check for selected issues that are specified well enough for you to start work with

### Halp! ###

You can contact Aimmoth@github
