package com.payments.paymentprocessor;

import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.repository.PaymentRepository;
import org.modelmapper.ModelMapper;
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
			repository.save(new Payment(15.0, "EE356437904816712537"));
			repository.save(new Payment(10.0, "LV017367904816708337"));
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(PaymentProcessorApplication.class, args);
	}

}
