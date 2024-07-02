package teamx.musiccollabbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class MusicCollabBackendApplication {

	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(MusicCollabBackendApplication.class);

	//For now please only turn this on when developing locally!
			app.setDefaultProperties(Collections.
					singletonMap("server.port", "8085"));

		app.run(args);

		System.out.println("Hello world!");
	}

}
