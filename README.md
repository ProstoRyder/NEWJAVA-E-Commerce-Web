# JAVA E-Commerce Web

## Lab 0: Readiness & Standardization

This project was prepared to satisfy the Lab 0 requirements:

- one-command build and test execution
- configuration through environment variables
- automatic database migrations with Liquibase
- dependency-aware health check
- JSON logging to STDOUT
- graceful shutdown

## Environment Variables

The application requires these environment variables:

- `DB_HOST`
- `DB_PORT`
- `DB_NAME`
- `DB_USER`
- `DB_PASSWORD`

The project reads these values from the `.env` file in the repository root.

## Database

The application requires a running PostgreSQL instance.

If Docker is available, PostgreSQL can be started with:

```powershell
docker compose up -d
```

If Docker is not used, run PostgreSQL locally and make sure the values in `.env` are correct.

## Run Application

```powershell
.\gradlew.bat bootRun
```

## Run Tests

```powershell
.\gradlew.bat test
```

## Database Migrations

Liquibase runs automatically on application startup and applies all pending migrations from:

- `src/main/resources/liquibase/changelog.yaml`

## Health Check

Endpoint:

- `GET /health`

Behavior:

- returns `200 OK` when the application is running and the database query succeeds
- returns `503 Service Unavailable` when the application is running but the database is unavailable

Check manually:

```powershell
curl -i http://localhost:8080/health
```

## JSON Logs

Application logs are written to STDOUT in JSON format.

Example:

```json
{"timestamp":"2026-04-06T19:10:24.9302363+03:00","level":"INFO","message":"Database is up to date, no changesets to execute"}
{"timestamp":"2026-04-06T19:10:28.5773826+03:00","level":"ERROR","message":"Application run failed"}
```

## Graceful Shutdown

The application handles shutdown gracefully.

Behavior:

- logs `SIGTERM received. Starting graceful shutdown...`
- stops accepting new requests
- allows in-flight requests to finish
- closes database connections
- exits normally

Example stop command on Windows:

```powershell
netstat -ano | findstr :8080
taskkill /PID <PID>
```

## Submission Evidence

### Health Check Screenshots

The screenshot below shows both required health check states:

- `200 OK` when the database is available
- `503 Service Unavailable` when the database is stopped

![Health check screenshots](images/Screenshot_1.png)

### Graceful Shutdown Screenshot

The screenshot below shows application shutdown logging after process termination.

![Graceful shutdown screenshot](images/Screenshot_2.png)

### What Is Included

This repository now includes:

1. Screenshot of `curl -i localhost:8080/health` returning `200` when the database is running.
2. Screenshot of `curl -i localhost:8080/health` returning `503` when the database is stopped.
3. Example JSON logs from application startup.
4. Screenshot of logs showing graceful shutdown after terminating the application process.
