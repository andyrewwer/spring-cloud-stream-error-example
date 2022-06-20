# Example

See this repo for an example where a JAVA class will fail but JAVA record will work.

```
$ docker-compose up -d
$ ./gradlew bootRun
```

```
$ curl -X POST http://localhost:8080/publish/output -d '{"content": "This is my fantastic content for you guys", "key": "Friday/file.txt"}' --header 'Content-Type: application/json'
```

Go in `StreamService` and swap which of these two lines are commented and it will work:

```java
//	fails
>>>	public boolean publish(@RequestBody MessageClass msg, @PathVariable String topic) {

//	works
>>>	public boolean publish(@RequestBody MessageRecord msg, @PathVariable String topic) {
		return this.streamBridge.send(topic, msg);
	}
```