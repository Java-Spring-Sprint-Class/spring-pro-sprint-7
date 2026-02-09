# Homework: Spring Security Basics

## Task Description
In this assignment, you will learn how to **secure a web application**, restrict access to pages based on user roles, and configure in-memory users.

The application has three access levels:

- **Public API**: Accessible to everyone (even unauthenticated users).
- **Private API**: Accessible to any authenticated user (USER or ADMIN).
- **Admin API**: Accessible **ONLY** to users with the ADMIN role.

## Goals
- Create a **SecurityFilterChain** configuration.
- Configure **HTTP Basic Authentication**.
- Create an **In-Memory UserDetailsService** with two users:
    - **User:** `user / password` (Role: USER)
    - **Admin:** `admin / admin` (Roles: USER, ADMIN)
- Configure authorization rules using `requestMatchers`.

## Technical Requirements

### 1. Controller (AppController)
The controller is already implemented. It has three endpoints:

- `GET /public/info`
- `GET /private/data`
- `GET /admin/settings`

### 2. Configuration (SecurityConfig)
You need to implement the class in the `config` package.

- Create a **UserDetailsService** bean using `InMemoryUserDetailsManager`.
    - Important: Passwords must be encoded or marked as "noop" (`{noop}password`) for testing purposes.
- Create a **SecurityFilterChain** bean.

Authorization rules:

| URL Pattern   | Access Rule               |
|---------------|---------------------------|
| /public/**    | permitAll()               |
| /admin/**     | hasRole('ADMIN')          |
| All others    | authenticated()           |

Enable `httpBasic()` so it can be tested via Postman or browser popup.

## How to Verify
1. Run the application.
2. Test via browser or Postman:
    - `http://localhost:8080/public/info` → Opens without password
    - `http://localhost:8080/private/data` → Requests password; log in as `user`
    - `http://localhost:8080/admin/settings` → Log in as `user` → 403 Forbidden; log in as `admin` → 200 OK
