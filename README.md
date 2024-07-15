# (SMS) POC Project

This project is a Proof of Concept (POC) for SMS built on Spring Boot and microservice architecture. 
It includes an Eureka server, API gateway, authentication server, and JWT server.

## Project Structure

The project is structured into the following components:

- **Eureka Server**: Service registry for locating services across the architecture.
- **API Gateway**: Single entry point for all client requests, routing them to the appropriate microservices.
- **Auth Server**: Validates subscriber details and triggers JWT generation.
- **JWT Server**: Manages JWT tokens for authenticated subscribers.

## Technologies Used

- **Spring Boot**: Framework for creating stand-alone, production-grade Spring-based applications.
- **Eureka**: Service registration and discovery.
- **JWT**: JSON Web Tokens for secure transmission of information between parties.

## Architecture Overview

The microservices architecture is designed as follows:

1. **Eureka Server**: 
   - Responsible for service discovery. All microservices register here.
   
2. **API Gateway**:
   - Acts as a single entry point for all requests from clients.
   - Routes requests to appropriate microservices based on the URL.

3. **Auth Server**:
   - Validates subscriber details.
   - Upon successful validation, requests JWT token generation from JWT Server.
   
4. **JWT Server**:
   - Generates and manages JWT tokens.
   - Validates incoming JWT tokens for expiry and integrity.
   - Provides token refresh functionality.

## Microservices Interaction Flow

1. **Client Request Flow**:
   - Client sends a request through the API Gateway.
   - API Gateway routes the request to the Auth Server.
   
2. **Auth Server**:
   - Validates subscriber details.
   - Checks partner number and secrets in database.
   - If valid, requests JWT token from JWT Server.
   - Returns JWT token to the client.

3. **JWT Server**:
   - Generates a new JWT token if the token is expired or does not exist for the corresponding partner number.
   - Returns the existing token if still valid.


