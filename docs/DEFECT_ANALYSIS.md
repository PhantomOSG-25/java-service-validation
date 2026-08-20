# Defect Analysis and Remediation

## Context

The maintained portfolio implementation began with the contacts, tasks, and appointments requirements used in CS 320 Software Test Automation. A static review of the historical Eclipse submission identified several defects that made the original delivery unsuitable as the employer-facing version.

The raw submission remains private provenance. This document records the engineering lessons without publishing course archives, desktop screenshots, grades, institutional details, or instructor information.

## Material Findings

| Historical defect | Quality risk | Maintained remediation | Regression evidence |
| --- | --- | --- | --- |
| Appointment constructor checked an unassigned field and did not reliably assign supplied values. | Valid appointments could fail or lose input. | Constructor validates and assigns ID, date, description, and clock through explicit invariant checks. | `AppointmentTest.acceptsBoundaryValuesAndUpdates` |
| Some service lookups compared strings by reference rather than value. | Equal IDs could fail lookup depending on object identity. | Services use `Map<String, ...>` key equality and explicit missing-record behavior. | Create/read/delete tests in every service test class |
| Appointment tests used fixed historical dates that eventually became invalid. | Tests changed from passing to failing as time advanced. | The appointment model accepts an injected `Clock`; tests use a fixed instant. | All `AppointmentTest` methods |
| Assertions contradicted supplied values or checked the wrong field. | A passing or failing result did not reliably represent the requirement. | Assertions are mapped to named requirements and verify the exact updated field and expected state. | `REQUIREMENTS_TRACEABILITY.md` and the maintained JUnit suite |
| Task tests depended on a process-wide identifier sequence and execution order. | Isolated or reordered tests produced different results. | IDs are explicit fixtures; each test creates fresh models and services. | Every maintained test can execute independently |
| Test discovery was inconsistent because some test-shaped methods lacked JUnit annotations or mixed framework versions. | Expected checks might never execute. | The project uses JUnit 5 consistently through a single Maven dependency and standard test layout. | Maven Surefire discovery during `verify` |
| Original delivery lacked a reproducible build descriptor. | Reviewers could not reliably compile or run the project. | `pom.xml` pins the compiler, test, enforcer, and coverage plugins; CI runs the same verification command. | GitHub Actions and Maven `verify` |
| Historical evidence was mixed with ZIPs, Word submissions, empty placeholders, and a desktop screenshot. | Privacy exposure and a cluttered employer review experience. | The professional tree contains only maintained source, tests, build automation, and concise technical documentation. | Proposed publication manifest |

## QA Lessons

- A test is useful only when it is discoverable, deterministic, and tied to a requirement.
- Boundary checks should prove both sides of a limit: the exact maximum succeeds and the next value fails.
- Invalid updates should be checked for failure behavior and for preservation of the prior valid state.
- Date and time are dependencies. Injecting a clock makes that dependency controllable.
- Historical defects can support a portfolio when they are explained as evidence of diagnosis and improvement, not presented as production-ready code.
