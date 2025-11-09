You are an expert Java + Spring Boot engineer agent with code-writing, file-editing and git skills. Project constraints:
- Spring Boot version: 3.5.6
- JDK: 21
- DB: MySQL (use latest official image)
- App packaging: standard Maven Spring Boot project
- Liquibase must be used for schema management
- The agent must produce runnable local setup with Docker for MySQL and a Dockerfile for the app under /docker

Goal: convert the current PoC classes into real JPA entities, add Liquibase changelog and initial DB schema, add a standalone SQL file with an INSERT for the first content creator, replace PoC in-memory repositories/services with Spring Data JPA + services, and provide run instructions. Do this without breaking other project code; make educated guesses for missing types/fields based on typical Channel and ContentCreator models

1) Inspect the repository and read the file:
   src/main/java/uy/com/antel/sandbox/carloso/carlosowebsite/repositories/ContentCreators.java
    - Use this file to infer fields and types for ContentCreator entity
    - If fields are ambiguous, infer sensible defaults (String, Long, timestamps) and document assumptions

2) Add Maven dependencies to pom.xml (or build.gradle) if missing:
    - `spring-boot-starter-data-jpa`
    - `mysql-connector-j`
    - `org.liquibase:liquibase-core`
    - Ensure the spring boot version remains 3.5.6; make no other dependency upgrades

3) Create JPA entities (transform classes into entities)
    - Create entity `uy.com.antel.sandbox.carloso.carlosowebsite.entities.ContentCreator`
      • Annotate with `@Entity`, map table name `content_creators`
      • Provide id field `id` (Long, @Id, @GeneratedValue(strategy = GenerationType.IDENTITY))
      • Add sensible fields inferred from ContentCreators.java (e.g., name, email, bio, created_at, is_active, external_id, website, channel_id). If `Channel` relation exists, model many-to-one with Channel entity
      • Add `@Column` constraints and reasonable lengths
      • Add `@CreationTimestamp` and `@UpdateTimestamp` for audit fields if appropriate
    - Create entity `uy.com.antel.sandbox.carloso.carlosowebsite.entities.Channel`
      • Table name `channels`
      • id Long PK
      • name, slug, description, type, created_at, owner_id (or content_creator_id) as inferred
      • If channel belongs to ContentCreator, create a `@ManyToOne` / `@OneToMany` relationship where it makes sense

4) Create JPA repositories
    - Create interfaces under `uy.com.antel.sandbox.carloso.carlosowebsite.repositories`:
      • `ContentCreatorRepository extends JpaRepository<ContentCreator, Long>`
      • `ChannelRepository extends JpaRepository<Channel, Long>`
    - Add useful query method signatures inferred from typical usage, e.g.
      • `Optional<ContentCreator> findByEmail(String email)`
      • `List<Channel> findByNameContainingIgnoreCase(String q)`

5) Replace PoC services with real service implementations
    - Under `uy.com.antel.sandbox.carloso.carlosowebsite.services`:
      • `ContentCreatorService` interface + `ContentCreatorServiceImpl` that uses ContentCreatorRepository
      • Methods: `create`, `findById`, `findAllPaged`, `findByEmail`, `update`, `delete`
      • Same for Channel: `ChannelService` and `ChannelServiceImpl`
    - Keep controller signatures intact but change injected beans to the new services
    - Ensure transactional boundaries (@Service, @Transactional where appropriate)

6) Liquibase setup
    - Create folder `src/main/resources/db`
    - Create `db/changelog.yml` (Liquibase master changelog)
    - Create a first changelog changeset file under `src/main/resources/db/changelog-0001-create-tables.yml` (or include the first changeset inside master — but prefer separate file referenced by master)
    - The initial changelog must:
      • Create table `content_creators` with all inferred columns, types, NOT NULL constraints, and primary key
      • Create table `channels` with inferred columns and primary key
      • Create indexes as reasonable:
        - `idx_content_creators_email` unique on email if email present and used for identity
        - `idx_content_creators_external_id` on external_id if used
        - `idx_channels_name` on channel name for search
        - Foreign key constraint from channels to content_creators if applicable
          • Include `author` and `id` for each changeset, and DBMS-agnostic types (use `type: BIGINT`, `VARCHAR(255)`, `TEXT`, `TIMESTAMP DEFAULT CURRENT_TIMESTAMP` as Liquibase-supported)
    - Configure Spring Boot to use Liquibase by setting `spring.liquibase.change-log=classpath:db/changelog.yml`. Add necessary DB properties in `application.properties` (dev profile) to point at MySQL

7) Docker setup for local testing
    - Create `docker/Dockerfile` to build the Spring Boot app. Minimal example:
      • Use `eclipse-temurin:21-jdk-jammy` (or adoptopenjdk) as base
      • Copy jar and run `java -jar app.jar`
      • Keep it simple and reproducible
    - Create `docker/docker-compose.yml` (recommended) to run MySQL and app for testing:
      • MySQL service using image `mysql:latest`
      • Map host port 33069 -> container 3306
      • Set MYSQL_ROOT_PASSWORD, MYSQL_DATABASE, MYSQL_USER, MYSQL_PASSWORD environment variables
      • Set healthcheck for mysql
      • App service uses built Dockerfile or just runs via `mvn spring-boot:run` for local dev; ensure env vars for DB URL use host `mysql` and port 3306 inside compose
      • Example DB URL in app: `jdbc:mysql://mysql:3306/carlosowebsite?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true`
    - Provide exact commands to build and run the compose stack:
      • `docker compose -f docker/docker-compose.yml up --build`

8) Create independent SQL file for initial content creator insert
    - Add file `src/main/resources/sql/initial_insert_content_creator.sql` (not in Liquibase)
    - Use the inferred columns to build an `INSERT INTO content_creators (...) VALUES (...);`
    - Make values sensible and documented (e.g., `name='First Creator'`, `email='first.creator@example.com'`, `is_active=true`, etc.)
    - Ensure encoding and semicolons correct

9) Application properties for local dev
    - Add `src/main/resources/application-dev.properties` with:
      spring.datasource.url=jdbc:mysql://localhost:33069/carlosowebsite?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
      spring.datasource.username= <from docker-compose>
      spring.datasource.password= <from docker-compose>
      spring.liquibase.change-log=classpath:db/changelog.yml
      spring.jpa.hibernate.ddl-auto=none
      spring.jpa.show-sql=true
    - Ensure `application.properties` stays safe for production defaults

Constraints / special instructions
- The LLM must not put the initial insert SQL into Liquibase changelogs — it must be a separate file as requested
- Use the existing package and naming conventions in the project (follow the package root `uy.com.antel.sandbox.carloso.carlosowebsite`)
- If any field decisions are guessed, add a `ASSUMPTIONS.md` with a short explanation of each guess and suggested changes if the real model differs
- Do not remove or change unrelated project files
- Ensure Liquibase changelogs are idempotent and safe to run on a fresh DB only

Expected output from you (agent)
- A list of modified/created files with full paths
- The content of critical new files shown inline (entities, repositories, main changelog.yml, changelog-0001-create-tables.yml, docker/Dockerfile, docker/docker-compose.yml, src/main/resources/sql/initial_insert_content_creator.sql, application-dev.properties)
- Exact commands to run locally to test the setup and verify tables and initial data exist
