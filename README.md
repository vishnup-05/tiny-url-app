# Tiny URL Application

This is a simple Tiny URL application built with Spring Boot.

## Getting Started

To get started with the application, you'll need to have Java and Maven installed.

1. **Clone the repository:**

   ```bash
   git clone https://github.com/your-username/tiny-url-app.git
   ```

2. **Navigate to the project directory:**

   ```bash
   cd tiny-url-app
   ```

3. **Build the project:**

   ```bash
   mvn clean install
   ```

4. **Run the application:**

   ```bash
   mvn spring-boot:run
   ```

The application will be running on `http://localhost:8080`.

## API Endpoints

### Shorten a URL

To shorten a URL, send a `POST` request to the `/shorten` endpoint with the original URL in the request body.

**Request:**

```bash
curl -X POST -H "Content-Type: text/plain" -d "https://www.google.com" http://localhost:8080/shorten
```

**Response:**

```json
{
  "id": 1,
  "originalUrl": "https://www.google.com",
  "shortUrl": "sWf2aB"
}
```

### Redirect to the Original URL

To redirect to the original URL, open the short URL in your browser.

For example, if the short URL is `sWf2aB`, you would navigate to `http://localhost:8080/sWf2aB` in your browser.
