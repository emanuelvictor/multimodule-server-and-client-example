package br.gov.mutants.verifier;

import br.gov.mutants.verifier.infrastructure.clients.configuration.EnableMutantsVerifierApiClient;
import lombok.Generated;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMutantsVerifierApiClient
public class MutantsSolutionApplication {

    @Generated
    public static void main(String[] args) {
        SpringApplication.run(MutantsSolutionApplication.class, args);
    }

}
