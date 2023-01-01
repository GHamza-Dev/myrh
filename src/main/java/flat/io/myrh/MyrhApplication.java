package flat.io.myrh;

import flat.io.myrh.files.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class MyrhApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyrhApplication.class, args);
	}

}
