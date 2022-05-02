package br.gov.mutants.verifier.client.v1;

import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

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