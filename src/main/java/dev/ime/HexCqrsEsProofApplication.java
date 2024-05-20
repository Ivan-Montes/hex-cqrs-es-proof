package dev.ime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@OpenAPIDefinition (info =
@Info(
          title = "hex-cqrs-es-proof",
          version = "1.0",
          description = "hex-cqrs-es-proof project",
          license = @License(name = "GNU GPLv3", url = "https://choosealicense.com/licenses/gpl-3.0/"),
          contact = @Contact(url = "https://github.com/Ivan-Montes", name = "IvanM", email = "ivan@github.com")
  )
)
@SpringBootApplication
public class HexCqrsEsProofApplication {

	public static void main(String[] args) {
		SpringApplication.run(HexCqrsEsProofApplication.class, args);
	}

}
