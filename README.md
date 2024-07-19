# Husksheets

## Overview

Husksheets is a distributed collaborative spreadsheet application.

1. **Server**: Manages REST API requests, maintains a persistent store, and handles user authentication.
2. **Client**: Allows users to create, open, and edit spreadsheets.
3. **User Interface**: Displays spreadsheets and provides interactive editing capabilities.

### Key Features
- **User Authentication**: Basic authentication using username and password.
- **Spreadsheet Management**: Create, retrieve, update, and delete spreadsheets.
- **Real-time Collaboration**: Subscribe to and publish updates for spreadsheets.

## Running the Program

### Prequisites to run application

1. JDK version 22 
2. Maven version 3.9.7
3. Install Docker Desktop

### Steps to Run
1. go to path /Users/josephgeorge/working/Husksheets_Project-2---java-main/Husksheets_Server
2. To run the application type "make start"
3. To run the application type "make stop"
4. The url to access the application on the browser is http://localhost:8080


1. **Compose the Project**:
   Run the `compose.yaml` file to connect to the database.
   
2. **Run the Server**:
   Navigate to the `Husksheets_Server` directory in your IDE, then the `main` package, then the `java` package, and run the `HusksheetsApplication.java` file.

3. **Access the Application**:
   Open your web browser and navigate to `https://localhost:8080` to interact with the Husksheets application.

## Server Specification V1 Implementation

The Husksheets server implements the following REST API endpoints as per the provided specifications:

1. **register()**: Registers a new publisher.
   - **URL**: `/api/v1/register`
   - **Method**: GET
   - **Auth**: Basic Authentication
   - **Description**: This endpoint registers a new user as a publisher. It extracts the username and password from the Basic authentication header and creates a new publisher if the username does not already exist.

2. **getPublishers()**: Retrieves all registered publishers.
   - **URL**: `/api/v1/getPublishers`
   - **Method**: GET
   - **Auth**: Basic Authentication
   - **Description**: This endpoint returns a list of all registered publishers.

3. **createSheet(Argument)**: Creates a new sheet.
   - **URL**: `/api/v1/createSheet`
   - **Method**: POST
   - **Auth**: Basic Authentication
   - **Description**: This endpoint creates a new spreadsheet for the authenticated user.

4. **getSheets(Argument)**: Retrieves all sheets for a given publisher.
   - **URL**: `/api/v1/getSheets`
   - **Method**: POST
   - **Auth**: Basic Authentication
   - **Description**: This endpoint returns a list of all spreadsheets owned by the specified publisher.

5. **deleteSheet(Argument)**: Deletes a specified sheet.
   - **URL**: `/api/v1/deleteSheet`
   - **Method**: POST
   - **Auth**: Basic Authentication
   - **Description**: This endpoint deletes the specified spreadsheet if the authenticated user is the owner.

6. **getUpdatesForSubscription(Argument)**: Retrieves updates for a subscribed sheet.
   - **URL**: `/api/v1/getUpdatesForSubscription`
   - **Method**: GET
   - **Auth**: Basic Authentication
   - **Description**: This endpoint returns updates for a sheet that the user is subscribed to.

7. **getUpdatesForPublished(Argument)**: Retrieves updates for a published sheet.
   - **URL**: `/api/v1/getUpdatesForPublished`
   - **Method**: GET
   - **Auth**: Basic Authentication
   - **Description**: This endpoint returns updates for sheets published by the user.

8. **updatePublished(Argument)**: Updates a published sheet.
   - **URL**: `/api/v1/updatePublished`
   - **Method**: POST
   - **Auth**: Basic Authentication
   - **Description**: This endpoint updates the content of a sheet published by the user.

9. **updateSubscription(Argument)**: Updates a subscription.
   - **URL**: `/api/v1/updateSubscription`
   - **Method**: POST
   - **Auth**: Basic Authentication
   - **Description**: This endpoint updates the subscription details for a user's subscribed sheet.

## Design Patterns

### Controller-Service-Repository Pattern
The project predominantly follows the Controller-Service-Repository pattern to structure the backend logic. This design pattern separates the concerns of the application into three main components:

1. **Controllers**: These handle HTTP requests, map them to appropriate services, and return the results as HTTP responses.
   - `PersonsController`: Manages user registration and authentication.
   - `SheetsController`: Handles sheet creation, deletion, and data retrieval.
   - `SubscriptionController`: Manages subscriptions and related updates.

2. **Services**: These contain the business logic of the application. They process requests from controllers and interact with repositories for data persistence.
   - `PersonsService`: Contains business logic for user management.
   - `SheetsService`: Implements operations for managing sheets.
   - `SubscriptionService`: Manages sheet subscriptions.

3. **Repositories**: These interact with the database. They perform CRUD operations and return data to the services.
   - `PersonsRepository`: Database interactions for user data.
   - `SheetsRepository`: Manages sheet data persistence.
   - `SubscriptionRepository`: Handles subscription data.

### Singleton Pattern
The Singleton pattern is used to ensure that certain service classes have only one instance throughout the application lifecycle. This is crucial for services that manage shared resources or configurations.

- **Usage**: In the `ControllerUtilService` and some repository classes to ensure that only one instance is created and shared across the application.

### Factory Pattern
The Factory pattern is used to create instances of complex objects such as sheets and subscriptions. This ensures a clean separation of object creation from the business logic.

- **Usage**: In the creation of `SheetEvent`, `Argument`, and other model classes to encapsulate the instantiation logic.

## Languages and Frameworks

### Backend
- **Java**: Primary language for server-side logic.
- **Spring Boot**: Framework for building the REST API and managing dependencies.
- **Java Persistence API**: Used for Object-Relational Mapping to interact with the database.

### Frontend
- **HTML/CSS**: Used for the basic structure and styling of the web interface.
- **JavaScript**: Provides interactivity on the client side.

## Class Descriptions

### Model Classes
- **Argument**: Represents the data structure for API requests and responses. It contains fields for publisher, sheet, id, and payload.
- **CellModel**: Represents individual cells in a spreadsheet. It includes attributes for row index, column index, value, and evaluation logic.
- **PersonsModel**: Represents user data, including username and password.
- **SheetEvent**: Captures events related to sheet updates. It includes attributes for event ID, type, and associated sheet ID.
- **SheetsModel**: Represents a spreadsheet. It contains attributes for sheet ID, name, row count, column count, owner, and lists of cells.
- **SubscribedCellModel**: Represents subscribed cells in a spreadsheet. It extends CellModel to include subscription-specific attributes.

### V1 Model Classes
- **Argument**: Similar to the main Argument class, tailored for version 1 API. It includes additional logic for handling API-specific payloads.
- **Cell**: Represents cell data for version 1 API. Contains attributes for sheet ID, column index, row index, and cell value.
- **InputCell**: Handles input cell data for updates. It includes a list of Cell objects.
- **RegistrationRequest**: Handles registration data. It includes fields for publisher, sheet, id, and payload.
- **Result**: Represents the structure of API responses for version 1. It contains fields for success status, message, and value (a list of Argument objects).

### Repository Classes
- **CellsRepository**: Manages persistence of cell data. It provides methods for finding cells by sheet ID, row index, and column index.
- **PersonsRepository**: Handles user data interactions with the database. It includes methods for finding users by username and password.
- **SheetEventRepository**: Manages sheet event data. It provides methods for finding and deleting sheet events.
- **SheetsRepository**: Handles CRUD operations for sheets. It includes methods for finding sheets by name, owner, and ID.
- **SubscriptionRepository**: Manages subscription data. It includes methods for finding subscriptions by sheet ID and deleting subscriptions.

### Service Classes
- **ControllerUtilService**: Provides utility functions for controllers, such as checking authorization.
- **SheetsService**: Implements business logic for sheets, including creation, deletion, and updates. It interacts with the SheetsRepository and other related repositories.
- **SubscriptionService**: Manages subscription logic, including saving and checking subscriptions, and retrieving updates for subscribed sheets.
