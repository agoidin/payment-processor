package com.payments.paymentprocessor;

import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.repository.PaymentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class PaymentProcessorApplication {

	@Bean
	CommandLineRunner commandLineRunner(
			PaymentRepository repository) {
		return args -> {
			repository.save(new Payment("EE356437904816712537", 15.0));
			repository.save(new Payment("LV017367904816708337", 10.0));
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentProcessorApplication.class, args);
	}

}
