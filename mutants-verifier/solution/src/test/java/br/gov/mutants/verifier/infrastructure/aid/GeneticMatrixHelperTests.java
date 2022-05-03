package br.gov.mutants.verifier.infrastructure.aid;

import br.gov.mutants.verifier.application.api.mappers.IndividualMapper;
import br.gov.mutants.verifier.application.api.mappers.IndividualMapperImpl;
import br.gov.mutants.verifier.commons.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.business.individual.SearchMutantGenesLogic;
import br.gov.mutants.verifier.domain.logics.validation.individual.InvalidGeneValidation;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 *
 */
public class GeneticMatrixHelperTests {

    /**
     *
     */
    @Test
    public void instanceOfGeneticMatrixHelper() {
        final GeneticMatrixHelper geneticMatrixHelper = new GeneticMatrixHelper();
        Assertions.assertThat(geneticMatrixHelper).isNotNull();
    }

    /**
     *
     */
    @Test
    public void generateRandomGeneTest() {
        final int sizeOfGene = 9;
        final String gene = GeneticMatrixHelper.generateRandomGene(sizeOfGene);
        Assertions.assertThat(gene).isNotNull();
        Assertions.assertThat(gene.length()).isEqualTo(sizeOfGene);
    }

    /**
     *
     */
    @Test
    public void generateGeneticMatrixTest() {
        final int sizeOfMatrix = 8;

        final String[] matrix = GeneticMatrixHelper.generateRandomGeneticMatrix(sizeOfMatrix);
        Assertions.assertThat(matrix).isNotNull();
        Assertions.assertThat(matrix.length).isEqualTo(sizeOfMatrix);
        Arrays.stream(matrix).forEach(gene -> Assertions.assertThat(gene.length()).isEqualTo(sizeOfMatrix));
    }

    /**
     *
     */
    @Test
    public void rotateMatrixTest() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT;

        final String[] rotatedMatrix = GeneticMatrixHelper.rotate(geneticMatrix);

        final String[] expected = new String[]{"GGGGTA", "TTGGGG", "AgGTTG", "gAATAT", "ACGTAA", "GCCCCG"};

        Arrays.stream(rotatedMatrix).forEach(line -> Assertions.assertThat(Arrays.asList(expected).contains(line)).isTrue());
    }

    /**
     *
     */
    @Test
    public void convertIndividualDTOToIndividualTest() {
        final String[] geneticMatrix = GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT;
        final IndividualMapper individualMapper = new IndividualMapperImpl();
        final Individual individual = individualMapper.convert(new IndividualDTO(geneticMatrix));

        Assertions.assertThat(individual).isNotNull();

        final String[] expectedGenes = new String[]{"AGTACG", "GTTGC", "GATC", "GTGTAG", "GGTAA", "GGTT", "AAGGT", "TCGG", "CTACT", "CGAA", "AGGTAG", "TGTAAC", "GGTTTC", "GGGAGC", "GTCACC", "GTATAG", "ATGgggG", "ggggtT", "gttgca", "TATAAT", "aatgca", "GCCCCG"};

        Assertions.assertThat(Arrays.stream(expectedGenes).anyMatch(diagonalGene -> Arrays.asList(individual.getGenes()).contains(diagonalGene))).isTrue();
    }

    /**
     *
     */
    @Test
    public void regexTest() {
        Assertions.assertThat(InvalidGeneValidation.isValid("gATgCa")).isTrue();
        Assertions.assertThat(InvalidGeneValidation.isValid("gggg")).isTrue();
        Assertions.assertThat(InvalidGeneValidation.isValid("AGTACG")).isTrue();
        Assertions.assertThat(InvalidGeneValidation.isValid("GTTGC")).isTrue();
        Assertions.assertThat(InvalidGeneValidation.isValid("GTTG1")).isFalse();
        Assertions.assertThat(InvalidGeneValidation.isValid("GTUTAG")).isFalse();
    }

    /**
     *
     */
    @Test
    public void generateHumanGeneticMatrixTest() {
        final int sizeOfGene = 500;
        final String[] humans = GeneticMatrixHelper.generateHumanGeneticMatrix(sizeOfGene);
        Assertions.assertThat(SearchMutantGenesLogic.isMutant(humans)).isFalse();

    }

    /**
     *
     */
    @Test
    public void generateMutantGeneticMatrixTest() {
        final int sizeOfGene = 500;
        final String[] mutants = GeneticMatrixHelper.generateMutantGeneticMatrix(sizeOfGene);
        Assertions.assertThat(SearchMutantGenesLogic.isMutant(mutants)).isTrue();
    }
}
