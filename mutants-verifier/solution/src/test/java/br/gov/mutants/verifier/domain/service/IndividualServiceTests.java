package br.gov.mutants.verifier.domain.service;

import br.gov.mutants.verifier.commons.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.commons.v1.dto.StatsDTO;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import br.gov.mutants.verifier.application.api.mappers.IndividualMapper;
import br.gov.mutants.verifier.domain.entity.Individual;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;

@SpringBootTest
public class IndividualServiceTests {

    /**
     *
     */
    @Autowired
    IndividualMapper individualMapper;

    /**
     *
     */
    @Autowired
    private IndividualService individualService;

    /**
     *
     */
    @Test
    @Sql("/dataset/truncate.sql")
    void saveHumanTest() {
        final Individual individual = individualService.save(individualMapper.convert(new IndividualDTO(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT)));
        Assertions.assertThat(individual.isMutant()).isFalse();
    }

    /**
     *
     */
    @Test
    @Sql("/dataset/truncate.sql")
    void saveMutantTest() {
        final Individual individual = individualService.save(individualMapper.convert(new IndividualDTO(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT)));
        Assertions.assertThat(individual.isMutant()).isTrue();
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
    public void statsTest() {
        final StatsDTO statsDTO = individualService.stats();
        Assertions.assertThat(statsDTO).isNotNull();
        Assertions.assertThat(statsDTO.getCountHumanDna()).isEqualTo(4);
        Assertions.assertThat(statsDTO.getCountMutantDna()).isEqualTo(10);
        Assertions.assertThat(statsDTO.getRatio()).isEqualTo(new BigDecimal("2.50"));
    }

}
