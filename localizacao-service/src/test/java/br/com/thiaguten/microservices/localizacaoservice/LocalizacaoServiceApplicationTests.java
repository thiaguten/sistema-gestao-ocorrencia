package br.com.thiaguten.microservices.localizacaoservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = EmbeddedMongoAutoConfiguration.class)
@ActiveProfiles("test")
@SpringBootTest
class LocalizacaoServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
