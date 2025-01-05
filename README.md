# Key Account Manager (KAM) Lead Management System
Key Access Manager (KAM) Lead Management System using SpringBoot and Postgres-DB. This application aims to resolve  Lead Management System for Key Account Managers (KAMs) who manage relationships with large restaurant accounts. This system will help track and manage leads, interactions, and account performance.

1. **Lead Management**: Add new restaurant leads, store basic info, and track their status.
2. **Contact Management**: Manage multiple POCs per restaurant with detailed contact info and roles.
3. **Interaction Tracking**: Log calls, track orders, and store interaction dates and notes.
4. **Call Planning**: Set call frequencies, view leads needing follow-up, and track call history.
5. **Performance Tracking**: Monitor account performance and flag underperformers.

## Documentation
AWS Hosted URL: https://13.201.74.214/
Swagger REST Docs: https://13.201.74.214/swagger-ui/index.html#/
Vercel Hosted Frontend URL: https://kam-frontend.vercel.app/

## Local Build and Run
### Install Java
Install Brew(If you don't have it already)
```shell
/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"
```
Install Java 17.0.13
```shell
brew install openjdk@17
```
Clone the Repository and open in your favourite IDE and Run the following commands
```shell
./mvnw clean package -DskipTests
```

```shell
docker-compose up --build
```
