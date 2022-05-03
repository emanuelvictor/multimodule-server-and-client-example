package br.gov.mutants.verifier.client.v1;

import br.gov.mutants.verifier.application.exceptions.GenesCannotBeNullExceptionInvalidException;
import br.gov.mutants.verifier.client.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class IndividualClientTests {

    /**
     *
     */
    private final IndividualClient individualClient;

    /**
     * @param individualClient IndividualClient
     */
    @Autowired
    public IndividualClientTests(final IndividualClient individualClient) {
        this.individualClient = individualClient;
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql"
    })
    @Test
    @DisplayName("Verify if a genetic matrix is a mutant with one mutant individual")
    public void verifyMutantWithMutantMustReturnTrue() {
        final IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setDna(GeneticMatrixHelper.generateMutantGeneticMatrix(20));
        final Boolean isMutant = individualClient.mutant(individualDTO);
        Assertions.assertThat(isMutant).isNotNull();
        Assertions.assertThat(isMutant).isTrue();
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql"
    })
    @Test
    @DisplayName("Verify if a genetic matrix is a mutant with one human individual")
    public void verifyMutantWithHumanMustReturnTrue() {
        final IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setDna(GeneticMatrixHelper.generateHumanGeneticMatrix(20));
        Assertions.assertThatThrownBy(() -> individualClient.mutant(individualDTO)).isInstanceOf(WebClientResponseException.Forbidden.class);
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql",
            "/dataset/insert_4_mutants.sql",
    })
    @Test
    public void ratioDivisionByZeroTests() {
        final StatsDTO statsDTO = individualClient.stats();
        Assertions.assertThat(statsDTO.getRatio()).isEqualTo(BigDecimal.ZERO);
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql",
            "/dataset/insert_4_mutants.sql",
            "/dataset/insert_10_humans.sql",
    })
    @Test
    public void ratioTestMustReturn040() {
        final StatsDTO statsDTO = individualClient.stats();
        Assertions.assertThat(statsDTO.getRatio()).isEqualTo(new BigDecimal("0.40"));
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql",
            "/dataset/insert_10_mutants.sql",
            "/dataset/insert_4_humans.sql",
    })
    @Test
    public void ratioTestMustReturn250() {
        final StatsDTO statsDTO = individualClient.stats();
        Assertions.assertThat(statsDTO.getRatio()).isEqualTo(new BigDecimal("2.50"));
    }
}