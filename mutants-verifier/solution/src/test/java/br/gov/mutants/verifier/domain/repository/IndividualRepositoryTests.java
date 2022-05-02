package br.gov.mutants.verifier.domain.repository;

import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@SpringBootTest
public class IndividualRepositoryTests {

    /**
     *
     */
    @Autowired
    IndividualRepository individualRepository;

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql",
            "/dataset/insert_10_humans.sql",
    })
    @Test
    public void getCountOfHumansTest() {
        final StatsDTO statsDTO = individualRepository.stats();
        Assertions.assertThat(statsDTO.getCountHumanDna()).isEqualTo(10);
        Assertions.assertThat(statsDTO.getCountMutantDna()).isEqualTo(0);
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql",
            "/dataset/insert_4_mutants.sql",
    })
    @Test
    public void getCountOfMutantsTest() {
        final StatsDTO statsDTO = individualRepository.stats();
        Assertions.assertThat(statsDTO.getCountHumanDna()).isEqualTo(0);
        Assertions.assertThat(statsDTO.getCountMutantDna()).isEqualTo(4);
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
        final StatsDTO statsDTO = individualRepository.stats();
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
        final StatsDTO statsDTO = individualRepository.stats();
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
        final StatsDTO statsDTO = individualRepository.stats();
        Assertions.assertThat(statsDTO.getRatio()).isEqualTo(new BigDecimal("2.50"));
    }

}
