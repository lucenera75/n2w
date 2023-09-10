# Number to Words Converter

This application provides a simple web interface to convert numeric values into their word representation, specifically formatted for currency (dollars and cents).

you can run the current published version
```
docker run -p 8080:8080 lucenera75/number-converter-rdf
```

## Features

- **Web Interface**: Users can input their name and a numeric value.
- **Conversion**: The numeric value is converted into words, formatted as currency.
- **Result Display**: The application displays the user's name along with the word representation of the entered numeric value.

For example:
```
Input: John Smith
123.45
Output: John Smith
ONE HUNDRED AND TWENTY THREE DOLLARS AND FORTY FIVE CENTS
```



## Technologies Used

- **Backend**: Java with Spring Boot framework.
- **Frontend**: Thymeleaf templates with TailwindCSS for styling.
- **Interactive Elements**: htmx for AJAX-like behavior without writing JavaScript.
- **Testing**: JUnit for unit tests.
- **Containerization**: Docker.

## Setup & Installation

### Running Locally

1. Ensure you have Java 17 and Maven installed.
2. mvn clean package
3. Open a browser and visit `http://localhost:8080`

### Running with Docker
1. Build the Docker image: 
```
docker build -t number-converter .

```
2. Run the application inside a Docker container:
```
docker run -p 8080:8080 number-converter

```

## Testing

- **Unit Testing**: 
- Run `mvn test`


## CI/CD

The application uses GitHub Actions for CI/CD. On every push to the main branch:
- The application is built and tested.
- A Docker image is created and pushed to DockerHub.

