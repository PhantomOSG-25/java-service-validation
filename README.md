# Java Service Validation and Unit Testing

**CS-320 Software Test Automation | Java, JUnit, Validation Testing**

This repository contains a Java testing project built around three small service areas: contacts, tasks, and appointments. Each area includes domain rules, service operations, and unit tests designed to check both valid behavior and rejected input.

The project strengthened my ability to translate written requirements into testable conditions and to use repeatable tests when checking software changes.

## Project Areas

| Component | Tested responsibilities |
| --- | --- |
| Contact service | Contact creation, field validation, updates, deletion, and identifiers |
| Task service | Task creation, field constraints, updates, lookup, and deletion |
| Appointment service | Appointment creation, date validation, description rules, and updates |

## Testing Approach

The test suite includes examples of:

- Positive tests for valid object creation and expected service behavior.
- Negative tests for null values and values outside stated length limits.
- Exception assertions for invalid constructor and setter input.
- Tests covering add, update, retrieve, and delete operations.
- Requirement-based checks for identifiers, phone numbers, descriptions, and appointment dates.

## Repository Contents

- `CS320SoftwareTest.zip` - Java source, service classes, and JUnit test classes
- `CS320ContactService` - original contact-service project package
- `CS320SoftwareTest` - original software-test project package
- Supporting journals, reports, and a development screenshot

The original course packages are preserved so the submitted work remains intact. The next technical cleanup will expose the reviewed Java source and tests directly in standard `src/main` and `src/test` folders after the test cases are re-run and corrected where necessary.

## What I Learned

Testing is more than checking whether the normal path works. Reliable testing also examines boundaries, invalid input, exception behavior, and the effect of service operations on stored data. This project taught me to connect each test to a specific requirement and to use failures as evidence for what needs correction.

## Skills Demonstrated

Java, JUnit, unit testing, input validation, exception testing, requirement analysis, defect identification, debugging, and software quality assurance.

## Author

Michael B. Wood  
Bachelor of Science in Computer Science, Software Engineering concentration  
Southern New Hampshire University | Coursework completing August 2026
