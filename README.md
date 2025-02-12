## CourierAppTestFramework

Tests are written in the **Cucumber framework** using the **Gherkin syntax**. They are implemented in **Java**.
All dependencies are managed by **Maven**.

- **Selenium WebDriver** is used for browser automation in UI tests.
- **Rest Assured** is used for API testing.

### Installation & Prerequisites

Before starting, make sure you have the following installed:

1. **JDK 21** (Ensure that the Java class path is properly set)
2. **Maven** (Ensure that the .m2 class path is properly set)
3. **Intellij IDEA Community** (optional, if you want to use an IDE to run tests)
4. **Chrome/Firefox** browser installed (for running tests locally).
5. **Docker Desktop** (for running tests using `docker-compose`)

## Framework Setup

To set up the framework, you can clone the repository from [here](https://github.com/kamilZ01/CourierAppTestFramework),
or download the ZIP file and set it up in your local workspace.

## Available tests

**GUI**; Tracking a parcel by its number and verifying its status - Test can be run using `@GUI` tag.

**API**; Search for Parcel Lockers for provided city/several cities and save details to the file - Test can be run using
`@API` tag.

## Environment configuration

Tests use `.env` configuration files that allow you to set different environments. Available files include:

- `.env.test` (default)
- `.env.production` (for the production environment)

The `TEST_ENV` value should match the name of the file (without the `.env` prefix). If not set, the default
configuration
from `.env.test` will be used.

To switch environments, set the `TEST_ENV` environment variable before running tests. For example:
`export TEST_ENV=production`

This will use the configuration from `.env.production`.

## Running Tests

Access the CLI of your operating system (e.g. Terminal for MacOS) and navigate to the project directory.

### Running Test Remotely (Using Selenium Grid)
To run tests remotely with Docker Compose:

1. **Build the Docker images**: Before running the tests, first build the Docker images by executing:
   `docker compose build`
2. **Run the tests**: After the images are built, run the tests with: `docker-compose run tests`
   This command will run all available tests using the default configuration `.env.test`.
3. **Running Tests with Tags**: You can specify which tests to run by setting the `TEST_TAGS` environment variable. For
   example:

- **Run only GUI tests**: `docker-compose run -e TEST_TAGS="(@GUI)" tests`
- **Run only API tests**: `docker-compose run -e TEST_TAGS="(@API)" tests`
- **Run both GUI and API tests**: `docker-compose run -e TEST_TAGS="(@GUI or @API)" tests` or `docker-compose run tests`

### Running Tests Locally
To run tests locally, on your machine:

1. Make sure `run.mode` is set to `local` in your `.env` file (e.g., `.env.test`).
2. **Run the tests using Maven**: `mvn clean test`
3. **Running Tests Locally with Tags**: You can run tests with specific tags (e.g., only tests with the `@GUI` or `@API`
   tag).

- To run only GUI tests: `mvn clean test "-Dcucumber.filter.tags=(@GUI)"`
- To run only API tests: `mvn clean test "-Dcucumber.filter.tags=(@API)"`
- To run both GUI and API tests: `mvn clean test "-Dcucumber.filter.tags=(@GUI or @API)"` or `mvn clean test`

Please note that browser drivers are not included as part of this framework. Browser drivers are automatically managed
by WebDriverManager library.

## Reporting

- **HTML Test Report**: Generated reports are stored in:
  `/target/htmlreport/`
- **API Test Outputs**: API test-generated files are saved in:
  `/target/test-outputs/`

If any test scenario fail, screenshots from the browser will be included in the report to help diagnose the issue.

### Copy Test Reports from Docker Container

If running tests via Docker, you need to copy these files from the container using `docker cp`.

1. Get the `courierapptestframework` container ID

To retrieve the latest container ID for `courierapptestframework`, use the following command:

- **MacOS/Linux (Bash)**:
  `docker ps -aq --filter "name=^courierapptestframework" | head -n 1`

- **Windows (PowerShell)**:
  `docker ps -aq --filter "name=^courierapptestframework" | Select-Object -First 1`

2. Copy Test Reports from the Container

Once you have the container ID, use docker cp to copy the necessary files:

- **Copy HTML report files**: `docker cp <container_id>:/app/target/htmlreport ./htmlreport`
- **Copy API test outputs**: `docker cp <container_id>:/app/target/test-outputs ./test-outputs`

**Example: One-liner for MacOS/Linux**
```
docker cp $(docker ps -aq --filter "name=^courierapptestframework" | head -n 1):/app/target/htmlreport/ ./htmlReport
```

**Example: One-liner for PowerShell (Windows)**

```
$containerId = docker ps -aq --filter "name=^courierapptestframework" | Select-Object -First 1;  docker cp $containerId":"/app/target/htmlreport/ ./htmlReport
```