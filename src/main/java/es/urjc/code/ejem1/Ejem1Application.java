package es.urjc.code.ejem1;

import es.urjc.code.ejem1.domain.dto.FullCartExpenditureDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

import java.util.Random;
import java.util.function.Supplier;

@SpringBootApplication
public class Ejem1Application {

	public static final Logger log = LoggerFactory.getLogger(Ejem1Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Ejem1Application.class, args);
	}

	@Bean
	public Supplier<FullCartExpenditureDTO> saveExpenditure() {

		Random random = new Random();

		Supplier<FullCartExpenditureDTO> cartExpenditureSupplier = () -> {
			Integer randomIntId = random.nextInt(50);
			Long randomLong = randomIntId.longValue();
			Integer randomIntExpenditure = random.nextInt(500);
			Double randomDouble = randomIntExpenditure.doubleValue();
			FullCartExpenditureDTO cartExpenditureDTO = new FullCartExpenditureDTO(randomLong, randomDouble);
			log.info("Publico en la cola: {}, {}", randomLong, randomDouble);
			return cartExpenditureDTO;
		};

		return cartExpenditureSupplier;
	}
}
