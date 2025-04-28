# Pick-Spot Container Placement Service

A Spring Boot application that determines optimal placement locations for containers in a port yard based on distance, size compatibility, refrigeration requirements, and slot availability.

## Overview

This service implements a mathematical algorithm to calculate scores for potential container placement locations. It accepts container details and a yard map, then returns the optimal placement coordinates based on the following criteria:

- Manhattan distance from current position
- Container size compatibility
- Refrigeration requirements
- Slot availability

## Running the Application

### Prerequisites
- Java 11 or higher
- Maven

### Build and Run
```bash
# Build the project command
mvn clean package

# Run the application
mvn spring-boot:run

# Alternative: Run the JAR directly
java -jar target/pick-spot-0.0.1-SNAPSHOT.jar
```

## API Documentation

### POST /pickSpot

Determines the best yard slot for a given container.

**Request Format**
```json
{
  "container": {
    "id": "C1",
    "size": "small",     // "small" or "big"
    "needsCold": false,  // true if refrigeration needed
    "x": 1,             // current x coordinate
    "y": 1              // current y coordinate
  },
  "yardMap": [
    {"x": 1, "y": 2, "sizeCap": "small", "hasColdUnit": false, "occupied": false},
    {"x": 2, "y": 2, "sizeCap": "big", "hasColdUnit": true, "occupied": false}
  ]
}
```

**Response Format (Success)**
```json
{
  "containerId": "C1",
  "targetX": 1,
  "targetY": 2
}
```

**Response Format (No suitable slot)**
```json
{
  "error": "no suitable slot"
}
```

## Algorithm

The service calculates a score for each available yard slot using:

```
score = distance + sizePenalty + coldPenalty + occupiedPenalty
```

Where:
- distance = Manhattan distance (|x1-x2| + |y1-y2|)
- sizePenalty = 0 if container fits in slot; 10,000 otherwise
- coldPenalty = 0 if cold requirements are met; 10,000 otherwise
- occupiedPenalty = 10,000 if slot is occupied; 0 otherwise

The slot with the lowest score is selected. If all slots have a score ≥ 10,000, no suitable slot is available.

## Sample Curl Command

```bash
curl -X POST -H "Content-Type: application/json" -d '{
  "container": {
    "id": "C1", 
    "size": "small", 
    "needsCold": false, 
    "x": 1, 
    "y": 1
  },
  "yardMap": [
    {"x": 1, "y": 2, "sizeCap": "small", "hasColdUnit": false, "occupied": false},
    {"x": 2, "y": 2, "sizeCap": "big", "hasColdUnit": true, "occupied": false}
  ]
}' http://localhost:8080/pickSpot
```

## Performance

The service is optimized for speed, with response times typically under 50ms for yard maps of reasonable size.

<img width="479" alt="image" src="https://github.com/user-attachments/assets/8d6049d2-f30a-4628-a6da-7a8d5711fbec" />
