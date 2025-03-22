
# Currency Converter API

A Spring Boot application that converts currencies using exchange rates from the ExchangeRate API.

## Features
- Convert amounts between different currencies
- Real-time exchange rates
- RESTful API endpoints
- Error handling and logging
- Unit tests

## Tech Stack
- Java 11
- Spring Boot 2.5.4
- JUnit 5
- SLF4J for logging

## API Endpoint

```
GET /convert?from={sourceCurrency}&to={targetCurrency}&amount={value}
```

Parameters:
- `from`: Source currency code (e.g., USD)
- `to`: Target currency code (e.g., EUR)
- `amount`: Amount to convert

Example:
```
GET /convert?from=USD&to=EUR&amount=100
```

## Getting Started

1. Clone the repository
2. Build the project: `mvn clean install`
3. Run the application: `mvn spring-boot:run`

The API will be available at `https://currency-converter.your-username.repl.co`

## Testing
Run tests using: `mvn test`
