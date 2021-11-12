# IDEELL SOCIAL MEDIA #

This is root folder for Ideell's all services. Short description for subfolders

* api - Spring Boot REST API using JSON. Security with JWT provided by Auth0
* db - Local database folder for testing
* doc - Documentation including graphic profile
* www - 4 separate projects (Ideell start page and PWA apps for Vi, Konst, Kvitter)

## Local Setup ##

Try the following short description of how to start your local environment

### Prerequirements ###

Install

* Java 15
* Docker Compose
* Intellij or Eclipse (install Lombok and Spring Tools 4 for Eclipse)
* Maven
* Npm

### Code ###

1. Clone repo at https://github.com/AIMMOTH/ideell
1. Import Maven project
1. Create project for Ideell start page, Vi, Konst and Kvitter
1. Install Angular CLI in Vi, Konst and Kvitter (npm install -g @angular/cli)

### Run Locally ###

1. Start MongoDB and MariaDB with Docker Compose (docker compose up). This will create folder db
1. Run you selected app with Angular Cli (ng serve)
1. Start Ideell API with either Maven (mvn spring:boot run) or your IDE

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
