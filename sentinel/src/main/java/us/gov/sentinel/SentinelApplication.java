package us.gov.sentinel;

import br.gov.mutants.verifier.infrastructure.clients.configuration.EnableMutantsVerifierApiClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMutantsVerifierApiClient
public class SentinelApplication {
    public static void main(String[] args) {
        SpringApplication.run(SentinelApplication.class, args);
    }
}
