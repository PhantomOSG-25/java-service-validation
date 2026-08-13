# Java Service Validation and Unit Testing

**Java 17 | JUnit 5 | Maven | GitHub Actions**

This repository presents a maintained Java implementation built around three service areas: contacts, tasks, and appointments. Each area includes domain rules, service operations, and unit tests designed to check valid behavior, rejected input, boundary conditions, unique identifiers, and missing records.

The project strengthened my ability to translate written requirements into testable conditions and to use repeatable tests when checking software changes.

## Maintained Implementation

The reviewable implementation follows the standard Maven layout:

- [`src/main/java`](src/main/java) - contact, task, and appointment models and services
- [`src/test/java`](src/test/java) - deterministic JUnit 5 model and service tests
- [`pom.xml`](pom.xml) - Java 17 and JUnit build configuration
- [`.github/workflows/test.yml`](.github/workflows/test.yml) - automated tests on pushes and pull requests

The original Eclipse project and course-delivery artifacts remain for historical comparison, but they are not the maintained implementation.

## Project Areas

| Component | Tested responsibilities |
| --- | --- |
| Contact service | Contact creation, field validation, updates, deletion, and unique identifiers |
| Task service | Task creation, field constraints, updates, lookup, deletion, and unique identifiers |
| Appointment service | Appointment creation, stable date validation, description rules, lookup, and deletion |

## Testing Approach

The test suite includes examples of:

- Positive tests for valid object creation and expected service behavior.
- Negative tests for null values and values outside stated length limits.
- Exception assertions for invalid constructor and setter input.
- Tests covering add, update, retrieve, and delete operations.
- Requirement-based checks for identifiers, phone numbers, descriptions, and appointment dates.
- Fixed-clock appointment tests that do not expire as calendar time advances.
- Duplicate-identifier and missing-record behavior.

## Run the Tests

Requirements: JDK 17 and Maven 3.9 or later.

```bash
mvn test
```

GitHub Actions runs the same command automatically. A successful workflow run is the evidence for the repository's test status.

## Repository Contents

- [`src/main/java`](src/main/java) - maintained production source
- [`src/test/java`](src/test/java) - maintained JUnit 5 suite
- [`CS320SoftwareTest.zip`](CS320SoftwareTest.zip) - original Eclipse project retained as a historical artifact
- `CS320ContactService` and `CS320SoftwareTest` - original course packages
- Supporting journals, report, and development screenshot

The maintained implementation corrects defects found in the archived project, including unassigned constructor fields, reference-based string comparisons, unstable historical dates, and mismatched assertions. The original packages remain intact so the progression is transparent.

## What I Learned

Testing is more than checking whether the normal path works. Reliable testing also examines boundaries, invalid input, exception behavior, and the effect of service operations on stored data. This project taught me to connect each test to a specific requirement and to use failures as evidence for what needs correction.

## Skills Demonstrated

Java, JUnit, unit testing, input validation, exception testing, requirement analysis, defect identification, debugging, and software quality assurance.

## Author

Michael B. Wood  
Bachelor of Science in Computer Science, Software Engineering concentration  
Southern New Hampshire University | Coursework completing August 2026
