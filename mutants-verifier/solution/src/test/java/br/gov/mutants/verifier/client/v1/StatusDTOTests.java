package br.gov.mutants.verifier.client.v1;

import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StatusDTOTests {

    /**
     *
     */
    @Test
    public void statusDTOToStringTest() {
        final long countMutantDna = 10;
        final long countHumanDna = 50;
        final String expected = "StatsDTO{countMutantDna=" + countMutantDna + ", countHumanDna=" + countHumanDna + ", ratio=" + new BigDecimal(countMutantDna).divide(new BigDecimal(countHumanDna), 2, RoundingMode.UNNECESSARY) + "}";
        final StatsDTO statsDTO = new StatsDTO(countMutantDna, countHumanDna);
        Assertions.assertThat(expected).isEqualTo(statsDTO.toString());
    }

}