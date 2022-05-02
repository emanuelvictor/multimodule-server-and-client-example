package br.gov.mutants.verifier.client.v1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author  Emanuel Victor
 * @version 1.0
 * @since   2022-04-29
 */
@NoArgsConstructor
@AllArgsConstructor
public class StatsDTO implements Serializable {

    /**
     *
     */
    private static final int DEFAULT_SCALE = 2;

    /**
     * Counter of individuals with mutant DNA
     */
    @Getter
    @JsonProperty("count_mutant_dna")
    private long countMutantDna;

    /**
     * Counter of individuals with human normal DNA
     */
    @Getter
    @JsonProperty("count_human_dna")
    private long countHumanDna;

    /**
     * Ratio of mutants to humans.
     * Example:
     * Count of Mutants: 2;
     * Count of Humans: 10;
     * Ratio: 0.20(the default scale is 2).
     * <p>
     * If the countHumanDna is '0', the return will be zero.
     *
     * @return {@link java.math.BigDecimal}
     */
    public BigDecimal getRatio() {
        if (countHumanDna == 0)
            return BigDecimal.ZERO;
        return new BigDecimal(countMutantDna).divide(new BigDecimal(countHumanDna), DEFAULT_SCALE, RoundingMode.UNNECESSARY);
    }

    /**
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "StatsDTO{" +
                "countMutantDna=" + countMutantDna +
                ", countHumanDna=" + countHumanDna +
                ", ratio=" + getRatio() +
                '}';
    }
}
