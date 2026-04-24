# WhatsApp Chatbot Backend

Spring Boot REST API for a WhatsApp-style chatbot that provides general cancer health information for Jarurat Care Foundation.

## Overview

The application exposes a single webhook endpoint:

- `POST /webhook`

It accepts JSON input with:

- `sender`
- `message`

It returns a JSON response with:

- `reply`

The chatbot supports keyword-based responses for cancer awareness, screening, emotional support, nutrition, treatment, and financial assistance. All incoming messages are logged in the console.

## Tech Stack

- Java 21
- Spring Boot 3.5.13
- Maven
- JUnit 5 / MockMvc

## Project Structure

```text
src/
  main/
    java/com/example/whatsappchatbot/
      WhatsappChatbotApplication.java
      controller/WebhookController.java
      model/IncomingMessage.java
      model/OutgoingMessage.java
      service/ChatbotService.java
    resources/
      application.properties
  test/
    java/com/example/whatsappchatbot/
      WebhookControllerTest.java
WhatsApp-Chatbot.postman_collection.json
pom.xml
README.md
```

## Configuration

Current application settings from `src/main/resources/application.properties`:

```properties
server.port=${PORT:8888}
spring.application.name=whatsapp-chatbot
logging.level.com.example=DEBUG
```

The app runs on:

`http://localhost:8888`

On Render, the app will automatically use the platform-provided `PORT`.

## How to Run

Using Maven wrapper on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Using Maven directly:

```bash
mvn spring-boot:run
```

## How to Test

Run automated tests:

```powershell
.\mvnw.cmd test
```

## Deploy on Render

Use a **Web Service** on Render for this Spring Boot app.

Render currently does **not** provide a native Spring Boot / Java runtime for this app flow, so deploy it with **Docker**.

### 1. Push the project to GitHub

Render deploys from a Git repository, so push this folder to GitHub first.

### 2. Create the Render service

In Render:

- click **New +**
- choose **Web Service**
- connect your GitHub repository
- select this repository

### 3. Choose the runtime

Use these values:

- **Environment**: `Docker`
- **Dockerfile Path**: `./Dockerfile`

### 4. Deploy

Click **Create Web Service**. After the build finishes, Render will give you a public URL such as:

`https://your-service-name.onrender.com`

Your webhook endpoint will then be:

`https://your-service-name.onrender.com/webhook`

### 5. Test the deployed API

Example:

```bash
curl -X POST https://your-service-name.onrender.com/webhook \
  -H "Content-Type: application/json" \
  -d "{\"sender\":\"+91-9876543210\",\"message\":\"help\"}"
```

### Common Render issue

If the app fails to start on Render, the usual cause is a fixed port. This project is already configured to use:

```properties
server.port=${PORT:8888}
```

That means:

- locally it runs on `8888`
- on Render it uses the port Render assigns automatically

Current verification status:

- `11` tests passing
- webhook endpoint returns JSON replies
- logging is enabled

## API

### Endpoint

```http
POST http://localhost:8888/webhook
Content-Type: application/json
```

### Sample Request

```json
{
  "sender": "+91-9876543210",
  "message": "Tell me about breast cancer"
}
```

### Sample Response

```json
{
  "reply": "BREAST CANCER INFORMATION ..."
}
```

## Supported Query Types

Examples of messages the chatbot currently handles:

- greetings: `hi`, `hello`, `hey`
- help menu: `help`
- breast cancer: `breast cancer`, `mammogram`
- lung cancer: `lung cancer`
- cervical cancer: `cervical`, `pap smear`
- colorectal cancer: `colon`, `colorectal`
- symptoms: `symptoms`, `warning signs`
- prevention: `prevention`, `prevent`
- treatment: `treatment`, `chemotherapy`, `radiation`
- emotional support: `support`, `counseling`, `mental health`
- screening: `screening`, `checkup`
- financial help: `financial`, `cost`, `expense`
- staging: `stage`, `grade`, `prognosis`
- nutrition: `diet`, `nutrition`, `food`
- farewell: `bye`, `goodbye`, `thanks`

For unknown queries, the chatbot returns a general help response with the medical disclaimer.

For blank input, it returns:

```text
Please enter a valid question. Type 'help' for available options.
```

## Logging

Incoming messages are logged in `ChatbotService` with sender and timestamp, for example:

```text
[2026-04-23T22:04:15.153120200] Message from [+91-9876543210]: Hello
```

Responses are also logged.

## Postman Collection

Import:

- [WhatsApp-Chatbot.postman_collection.json](D:/WhatsAppchatbotbackend/WhatsApp-Chatbot.postman_collection.json)

Collection details:

- base URL variable is set to `http://localhost:8888`
- includes ready-to-run webhook requests
- includes Postman test scripts that validate `200 OK` and key response text

## Example curl

```bash
curl -X POST http://localhost:8888/webhook \
  -H "Content-Type: application/json" \
  -d "{\"sender\":\"+91-9876543210\",\"message\":\"help\"}"
```

## Notes

- This chatbot provides general informational responses only.
- Medical advice must come from a qualified doctor or oncologist.
- The current content is more advanced than the original basic assignment and is centered on cancer health guidance.
