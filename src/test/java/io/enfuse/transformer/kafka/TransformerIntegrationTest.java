package io.enfuse.transformer.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.stream.binder.test.InputDestination;
import org.springframework.cloud.stream.binder.test.OutputDestination;
import org.springframework.cloud.stream.binder.test.TestChannelBinderConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Import(TestChannelBinderConfiguration.class)
@ActiveProfiles("test")
class TransformerIntegrationTest {

	@Autowired
	private InputDestination source;

	@Autowired
	private OutputDestination target;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void save_savesFile() throws Exception {
		MessageRecord request = new MessageRecord("path/to/file.txt", "This is my file content");
		source.send(new GenericMessage<>(request));
		MessageRecord response = objectMapper.readValue(target.receive().getPayload(), MessageRecord.class);
		assertEquals(response.content(), "This is my file content");
		assertTrue(response.key().matches("path\\/to\\/\\d{4}-\\d{2}-\\d{2}-file.txt"));
	}
}
