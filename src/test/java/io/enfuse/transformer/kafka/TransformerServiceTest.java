package io.enfuse.transformer.kafka;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransformerServiceTest {

	@Test
	void appendDateToPath_append() throws Exception {
		MessageRecord request = new MessageRecord("path/to/file.txt", null);

		TransformerService subject = new TransformerService();

		MessageRecord response = subject.appendDateToPath(request);

		assertNull(response.content());
		assertTrue(response.key().matches("path\\/to\\/\\d{4}-\\d{2}-\\d{2}-file.txt"));
	}

}
