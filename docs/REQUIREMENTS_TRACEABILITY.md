# Requirements Traceability

This matrix connects each maintained requirement to its implementation and regression evidence.

| ID | Requirement | Implementation | Primary test evidence |
| --- | --- | --- | --- |
| CON-01 | Contact ID is required, immutable, and no longer than 10 characters. | `Contact` constructor and final `id` field | `ContactTest.acceptsBoundaryValuesAndUpdatesEveryMutableField`; `rejectsInvalidConstructorValues` |
| CON-02 | First and last names are required and no longer than 10 characters. | `Contact.setFirstName`; `setLastName` | `ContactTest.rejectsInvalidConstructorValues`; `rejectsInvalidSetterValuesWithoutChangingState` |
| CON-03 | Phone number contains exactly 10 digits. | `Contact.setPhoneNumber` | `ContactTest.rejectsInvalidConstructorValues`; `rejectsInvalidSetterValuesWithoutChangingState` |
| CON-04 | Address is required and no longer than 30 characters. | `Contact.setAddress` | `ContactTest.acceptsBoundaryValuesAndUpdatesEveryMutableField`; `rejectsInvalidConstructorValues` |
| CON-05 | Contact service supports unique add, lookup, field updates, delete, and list. | `ContactService` | `ContactServiceTest.supportsCreateReadUpdateDeleteWorkflow`; `rejectsNullDuplicatesAndMissingIdentifiers` |
| TSK-01 | Task ID is required, immutable, and no longer than 10 characters. | `Task` constructor and final `id` field | `TaskTest.acceptsBoundaryValuesAndUpdatesMutableFields`; `rejectsInvalidConstructorValues` |
| TSK-02 | Task name is required and no longer than 20 characters. | `Task.setName` | `TaskTest.acceptsBoundaryValuesAndUpdatesMutableFields`; `rejectsInvalidUpdatesWithoutChangingState` |
| TSK-03 | Task description is required and no longer than 50 characters. | `Task.setDescription` | `TaskTest.rejectsInvalidConstructorValues`; `rejectsInvalidUpdatesWithoutChangingState` |
| TSK-04 | Task service supports unique add, lookup, updates, delete, and list. | `TaskService` | `TaskServiceTest.supportsCreateReadUpdateDeleteWorkflow`; `rejectsNullDuplicatesAndMissingIdentifiers` |
| APT-01 | Appointment ID is required, immutable, and no longer than 10 characters. | `Appointment` constructor and final `id` field | `AppointmentTest.acceptsBoundaryValuesAndUpdates`; `rejectsInvalidConstructorValues` |
| APT-02 | Appointment date is required and cannot be in the past. | `Appointment.setDate` with injected `Clock` | `AppointmentTest.rejectsInvalidConstructorValues`; `rejectsInvalidUpdatesWithoutChangingState` |
| APT-03 | Appointment description is required and no longer than 50 characters. | `Appointment.setDescription` | `AppointmentTest.acceptsBoundaryValuesAndUpdates`; `rejectsInvalidConstructorValues` |
| APT-04 | Appointment service supports unique add, lookup, updates, delete, and list. | `AppointmentService` | `AppointmentServiceTest.supportsCreateReadUpdateDeleteWorkflow`; `rejectsNullDuplicatesAndMissingIdentifiers` |
| SRV-01 | Duplicate IDs are rejected. | `Map.putIfAbsent` in all services | Duplicate checks in all three service test classes |
| SRV-02 | Missing records produce an explicit failure. | `NoSuchElementException` in all services | Missing-ID checks in all three service test classes |
| SRV-03 | Callers cannot structurally mutate returned lists. | `List.copyOf` in all services | `UnsupportedOperationException` checks in all three service test classes |
| QA-01 | Tests do not depend on the real calendar or execution order. | Fixed `Clock`; fresh fixtures and services | All appointment model tests and all service tests |
| QA-02 | The automated build enforces runtime and coverage requirements. | Maven Enforcer and JaCoCo plugins | `mvn clean verify` and GitHub Actions workflow |
