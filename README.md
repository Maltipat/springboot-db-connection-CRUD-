
# Mini Leave Management System

 Problem Statement

We are building the MVP of a Leave Management System for a startup with
50 employees.\
The HR team should be able to:\
1. Add employees with details (Name, Email, Department, Joining Date).\
2. Apply, approve, and reject leave requests.\
3. Track leave balance for each employee.

Features Implemented (Part 1)

-   Add Employee (with default leave balance = 20).\
-   Apply for Leave (validations added).\
-   Approve / Reject Leave.\
-   Fetch Leave Balance per Employee.\
-   Edge Cases handled:
    -   Invalid dates (end date before start date).\
    -   Leave before joining date.\
    -   More days than available balance.\
    -   Employee not found.\
    -   Overlapping approved leaves.

Tech Stack

-   **Backend**: Spring Boot (Java)\
-   **Database**: H2 / MySQL (configurable)\
-   **Frontend**: (Future) React / Angular / Simple HTML UI

API Endpoints

### Employee APIs

-   `POST /employees` → Add Employee\
    Example:

    ``` json
    {
      "name": "Ravi Kumar",
      "email": "ravi@example.com",
      "department": "IT",
      "joiningDate": "2025-09-01"
    }
    ```

-   `GET /employees` → Get All Employees\

-   `GET /employees/{id}` → Get Employee by ID\

-   `GET /employees/{id}/balance` → Get Leave Balance

Leave APIs

-   `POST /leaves` → Apply for Leave\
-   `GET /leaves` → Get All Leaves\
-   `GET /leaves/{id}` → Get Leave by ID\
-   `PUT /leaves/{id}/approve` → Approve Leave\
-   `PUT /leaves/{id}/reject` → Reject Leave

 High Level Design



📦 Setup Instructions

1.  Clone the repo\
2.  Run using IntelliJ / Eclipse or `mvn spring-boot:run`\
3.  Test using `curl` or Postman.

🔮 Future Improvements

-   Add Authentication (HR login).\
-   Build Frontend Dashboard.\
-   Email notifications for leave status.\
-   Deploy on cloud (Heroku/Render).
