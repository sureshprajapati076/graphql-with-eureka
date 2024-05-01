package com.server.b;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/service/b")
public class BApplication {

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${server.port}")
	String portNo;

	private static int count=0;

	public static void main(String[] args) {
		SpringApplication.run(BApplication.class, args);
	}


	public record ResponseB(String result, String status, String method, Integer count) {
	}

	@GetMapping("/get")
	public ResponseEntity<?> get(){
		return ResponseEntity.ok("""
                {
                    "result":"this is from api B",
                    "status":"OK",
                    "method":"GET"
                }
                """);
	}

	@GetMapping("/reset")
	public ResponseEntity<?> reset(){
		count=0;
		return ResponseEntity.ok("RESET COMPLETE");
	}

	@PostMapping("/post")
	public ResponseEntity<?> post(@RequestBody Map<String,String> reqMap) throws JsonProcessingException, InterruptedException {

		if(reqMap.containsKey("waitTime")){
			Thread.sleep(Integer.parseInt(reqMap.get("waitTime")));
		}

		if(reqMap.containsKey("decision")){
			count++;
			if(count==Integer.parseInt(reqMap.get("decision"))){
				count=101;
			}
		}

		return ResponseEntity.ok(
				objectMapper.readValue("""
                {
                    "result":"this is from api B %s",
                    "status":"OK",
                    "method":"POST",
                    "count": %d
                }
                """.formatted(portNo,count),
						ResponseB.class));
	}

}
