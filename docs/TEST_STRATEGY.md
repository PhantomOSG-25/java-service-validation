# Test Strategy

## Purpose

Verify that the contact, task, and appointment components enforce their written constraints and that their services behave predictably across valid, invalid, and state-changing operations.

## Quality Risks

The highest-risk behaviors in this project are:

1. Accepting records that violate field constraints.
2. Rejecting valid values at exact boundaries.
3. Allowing duplicate identifiers.
4. Mutating a record before an invalid update fails.
5. Returning inconsistent results for missing records.
6. Making appointment tests dependent on the current date.
7. Allowing callers to change a service collection outside its API.

## Test Levels

### Model unit tests

Model tests isolate constructor and setter rules:

- Required values reject null and blank input.
- Length-constrained values accept the exact maximum and reject the next character.
- Phone numbers require exactly ten digits.
- Appointment dates reject past values.
- Immutable identifiers remain unchanged after creation.
- Invalid setters leave the prior valid value intact.

### Service unit tests

Service tests exercise each model through its collection boundary:

- Create, read, update, and delete workflows
- Duplicate-identifier rejection
- Missing-record behavior
- Null-record rejection
- Propagation of model validation during updates
- Read-only list snapshots

## Test-Design Techniques

| Technique | Use in this project |
| --- | --- |
| Equivalence partitioning | Valid text, blank text, null values, oversized values, valid and invalid phone formats |
| Boundary-value analysis | Exact ID, name, address, and description limits plus one character beyond each limit |
| State-transition testing | Add, retrieve, update, delete, and retrieve-after-delete workflows |
| Negative testing | Duplicate IDs, missing IDs, past dates, null records, and invalid updates |
| Failure atomicity | Verify rejected updates do not replace existing valid state |
| Deterministic fixtures | Fixed clock for date-sensitive appointment tests and fresh service instances per test |
| Encapsulation checks | Verify returned lists cannot be structurally modified |

## Isolation and Repeatability

Every test creates its own service instance and test data. No static sequence, shared mutable collection, network call, database, system property, or execution order is required.

Appointment model tests use a fixed `Clock`. Service tests build appointments against the same fixed date baseline, so the suite does not become invalid as real time advances.

All fixture data is fictitious and exists only in memory.

## Automated Quality Gate

The supported command is:

```bash
mvn --batch-mode --no-transfer-progress clean verify
```

The build requires Java 17+ and Maven 3.9+. The JaCoCo gate requires at least:

- 85% line coverage
- 75% branch coverage

GitHub Actions runs `verify` for pull requests and pushes to `main`, then retains Surefire and JaCoCo reports as review evidence.

## Out of Scope

The project contains no persistence, user interface, external API, authentication, authorization, or concurrency control. Tests for those areas would imply behavior the application does not provide.
