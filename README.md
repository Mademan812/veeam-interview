# Veeam API Automation Framework

A scalable, containerized test automation framework built using **Java 21**, **RestAssured**, and **TestNG**. This project serves as a comprehensive proof-of-concept (PoC) demonstrating multiple testing layers (API, Integration, Contract, E2E) coupled with a robust enterprise-ready observability strategy.

---

## 🏗️ Architecture & Core Strategy

The framework is structured around four primary pillars of testing:
* **Component API Tests:** Validating isolated endpoint behaviors and response constraints.
* **Integration Tests:** Verifying integration boundaries between sequential API interactions.
* **Contract Tests:** Enforcing schema validity using OpenAPI/Swagger spec filters.
* **User-Story (E2E) Tests:** Simulating real-world end-user behaviors spanning complex workflows.

### 📊 Observability & Multi-Channel Reporting
The framework decouples test execution from reporting, routing test lifecycles dynamically through a custom TestNG listener (`CustomTestListener`) into three distinct channels:

1.  **Console Logging (Log4j2):** Beautifully formatted, synchronous request/response logging via RestAssured filters, tailored for local debugging.
2.  **ExtentReports *(Omitted/Stubbed)*:** Aggregated HTML execution dashboards tailored for human consumption post-run.
3.  **ElasticSearch *(Omitted/Stubbed)*:** Structural log shipping to facilitate historical trend analysis and long-term infrastructure health telemetry.

> ⏳ **Timeboxing Note:** Database validations, state cleanup hooks, ExtentReports attachments, and ElasticSearch clients are architecturally represented via inline code comments and stubs to fit development time constraints.

---

## 🛠️ Technology Stack
* **Language:** Java 21 (utilizing modern String templates and records)
* **Test Runner:** TestNG (Chosen over JUnit for superior test suite grouping, parameterization, and native XML controls)
* **HTTP Client:** RestAssured 5.x
* **Containerization:** Docker (Alpine JRE optimized distribution)
* **CI/CD Pipeline:** GitHub Actions

---

## 🚀 Getting Started Locally

### Prerequisites
* Java 21 JDK
* Maven 3.9+
* Docker Desktop (Optional, for container runs)

### Running Locally via Maven
To trigger a specific test suite execution from your terminal:
```bash
mvn clean test -Denv=default -Dsuite=testng.xml
```

## 🐳 Containerization & CI/CD Pipeline

The framework is designed to be completely infrastructure-agnostic, running reliably inside isolated containers.

### Dockerized Execution
The project uses a lightweight `eclipse-temurin:21-jre-alpine` runtime base to optimize image size and performance footprints.

To build and execute a suite locally via Docker:
```bash
# Build the automation image
docker build -t veeam-api-tests .

# Run a specific suite via environment variables
docker run --rm -e SUITE="testng.xml" veeam-api-tests
```

### GitHub Actions Integration

A parameterized workflow is configured under `.github/workflows/run-tests.yml`. It provides an enum-based dropdown choice menu directly inside the GitHub interface, allowing manual trigger execution across different suites (e.g., `testng.xml`, `smoke-suite.xml`).

---

### Test Management via `testng.xml`

Test methods are targeted and isolated cleanly at the class level, with `CustomTestListener` mapping executions dynamically using Java Method reflections to avoid `null` naming outputs. Individual XML configuration files are used to bind these components together, allowing teams to isolate, group, and run specific sets of tests (such as smoke or regression test suites) dynamically.

---

### Contract Testing via Schema Validation

Contract testing is actively enforced using an automated validation layer. Rather than relying on rigid, hardcoded assertions for every JSON field, the framework dynamically validates the shape, format, and structure of API responses against the official API specification.

* **Live Specification Sync:** The system utilizes an active `OpenApiValidationFilter` registered directly within the RestAssured request specification flow.
* **Automated Schema Drift Protection:** If an upstream service updates an endpoint and violates the defined data contracts (e.g., changes an integer to a string, drops a required object key, or changes field bounds), the filter immediately fails the active test with a detailed schema divergence report.
* **Minimized Test Maintenance:** This decouples structural regressions from individual functional assertions, providing instant contract verification out of the box for every endpoint integrated into the pipeline.

---

### 📢 Framework Documentation Notice

> **Crucial Compliance & Integrity Statement**
>
> Please note that **only this `README.md` file** was generated with the assistance of Generative AI.
>
> Due to strict project execution timeboxes, manual creation of user-facing markdown templates was evaluated as a poor use of development velocity. To optimize delivery constraints, an AI engine was granted temporary read-only access to the finished codebase repository alongside the author's raw architectural notes to assemble this polished, human-reviewed text layout.
>
> **Absolutely no Generative AI tools were used during the conception, architecture, design, or coding of this repository.** Every single operational component listed below is the direct product of original engineering, manual implementation, and hands-on domain expertise:
> * All functional test methodologies, design patterns, and suite orchestration loops.
> * The underlying Java 21 logic, custom TestNG listener routing, and RestAssured filter hooks.
> * The multi-channel reporting structure, schema contract validation logic, and containerization layers.
> * Pipeline automation configuration, environment state profiles, and code comments.
>
> This documentation acts solely as a generated window into an entirely bespoke, handcrafted engineering implementation.

---