package com.example.whatsappchatbot;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

/**
 * WebhookControllerTest - Cancer Health Consultant API Tests
 * Validates real cancer health consultation queries and responses
 */
@SpringBootTest
@AutoConfigureMockMvc
class WebhookControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@ParameterizedTest
	@MethodSource("cancerHealthQueries")
	void shouldReturnAppropriateHealthResponse(String query, String expectedKeyword) throws Exception {
		String requestBody = """
			{
			  "sender": "+91-9876543210",
			  "message": "%s"
			}
			""".formatted(query);

		mockMvc.perform(post("/webhook")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.reply").isString())
			.andExpect(jsonPath("$.reply").value(org.hamcrest.Matchers.containsStringIgnoringCase(expectedKeyword)));
	}

	private static Stream<org.junit.jupiter.params.provider.Arguments> cancerHealthQueries() {
		return Stream.of(
			// Greeting query
			org.junit.jupiter.params.provider.Arguments.of("Hello", "Jarurat Care Foundation"),

			// Breast cancer query
			org.junit.jupiter.params.provider.Arguments.of("Tell me about breast cancer", "BREAST CANCER"),

			// Symptoms query
			org.junit.jupiter.params.provider.Arguments.of("What are the symptoms?", "WARNING SIGNS"),

			// Prevention query
			org.junit.jupiter.params.provider.Arguments.of("How to prevent cancer?", "PREVENTION"),

			// Help menu
			org.junit.jupiter.params.provider.Arguments.of("help", "Available Topics"),

			// Cancer screening
			org.junit.jupiter.params.provider.Arguments.of("cancer screening", "Screening"),

			// Emotional support
			org.junit.jupiter.params.provider.Arguments.of("Need emotional support", "Counseling"),

			// Nutrition
			org.junit.jupiter.params.provider.Arguments.of("diet and nutrition", "Fruits"),

			// Financial assistance
			org.junit.jupiter.params.provider.Arguments.of("financial assistance", "Financial"),

			// Goodbye
			org.junit.jupiter.params.provider.Arguments.of("Goodbye", "healthy"),

			// Empty message
			org.junit.jupiter.params.provider.Arguments.of("   ", "Please enter a valid question")
		);
	}
}
