package io.pivotal.fe.rhardt.pythonmbpdemo;

import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.nio.charset.Charset;

@SpringBootApplication
public class PythonMbpDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(PythonMbpDemoApplication.class, args);
	}


	@org.springframework.stereotype.Controller
	public class Controller {

		@RequestMapping("/")
		public ResponseEntity<String> index() {
			return ResponseEntity.ok("Hello from Java.");
		}

		@RequestMapping("/python")
		public ResponseEntity<String> python() throws Exception{
			String scriptPath = new ClassPathResource("hello.py").getFile().getAbsolutePath();
			ProcessBuilder builder = new ProcessBuilder("python", scriptPath, "arg to the python script");
			String ret = IOUtils.toString(builder.start().getInputStream(), Charset.defaultCharset());
			return ResponseEntity.ok(ret);
		}


	}


}
