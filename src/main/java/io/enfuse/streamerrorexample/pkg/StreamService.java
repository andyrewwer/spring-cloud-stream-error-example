package io.enfuse.streamerrorexample.pkg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class StreamService {

	@Autowired
	private StreamBridge streamBridge;

	@PostMapping("/publish/{topic}")
//	fails
	public boolean publish(@RequestBody MessageClass msg, @PathVariable String topic) {
//	works
//	public boolean publish(@RequestBody MessageRecord msg, @PathVariable String topic) {
		return this.streamBridge.send(topic, msg);
	}

}
