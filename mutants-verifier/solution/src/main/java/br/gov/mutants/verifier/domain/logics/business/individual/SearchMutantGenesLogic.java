package br.gov.mutants.verifier.domain.logics.business.individual;

import br.gov.mutants.verifier.application.exceptions.InvalidMatrixException;
import br.gov.mutants.verifier.application.i18n.MessageSourceHolder;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.business.IBusinessLogic;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * Search mutant gene
 */
@Order(3)
@Component
public class SearchMutantGenesLogic implements IBusinessLogic<Individual> {

    public static final int ELIGIBLE_MUTANT_GENE_LENGTH = 4;

    /**
     * @param individual Individual
     */
    @Override
    public void perform(final Individual individual) {
        individual.setMutant(isMutant(individual.getGenes()));
    }

    /**
     * @param genes String[]
     * @return Boolean
     */
    public static boolean isMutant(final String[] genes) {
        return extractAllGenes(genes);
    }

    /**
     * @param value String
     * @return boolean
     */
    public static boolean isMutant(final String value) {
        return Arrays.stream(GeneticMatrixHelper.MUTANTS_GENES).anyMatch(mutantsGenes -> value.trim().toLowerCase().contains(mutantsGenes.trim().toLowerCase()));
    }

    /**
     * Extract all genes from matrix
     *
     * @param geneticMatrix String[]
     * @return boolean
     */
    public static boolean extractAllGenes(final String[] geneticMatrix) {

        //  Add horizontal and vertical genes
        if (extractVerticalAndHorizontalGenes(geneticMatrix))
            return true;

        //  Add diagonal genes
        return extractDiagonalGenes(geneticMatrix);

    }

    /**
     * Extract vertical and diagonal genes from genetic matrix
     *
     * @param geneticMatrix String[]
     * @return boolean
     */
    public static boolean extractVerticalAndHorizontalGenes(final String[] geneticMatrix) {

        if (geneticMatrix == null || geneticMatrix.length == 0)
            throw new InvalidMatrixException(MessageSourceHolder.getMessage("invalid-genetic-matrix"));

        for (int i = 0; i < geneticMatrix.length; i++) {
            if (isMutant(geneticMatrix[i]))
                return true;

            final StringBuilder verticalGene = new StringBuilder();
            for (int j = 0; j < geneticMatrix[i].length(); j++)
                verticalGene.append(geneticMatrix[j].charAt(i));

            if (isMutant(verticalGene.toString()))
                return true;
        }

        return false;
    }

    /**
     * Extract diagonal genes from genetic matrix
     *
     * @param geneticMatrix String[]
     * @return boolean
     */
    public static boolean extractDiagonalGenes(String[] geneticMatrix) {

        if (geneticMatrix == null || geneticMatrix.length == 0)
            throw new InvalidMatrixException(MessageSourceHolder.getMessage("invalid-genetic-matrix"));

        // Course all sides of the matrix.
        for (int turn = 0; turn < 4; turn++) {

            // Create a array only genetic matrix size minus 3, because Anything beyond that is not eligible to mutant gene.
            final StringBuilder[] genes = new StringBuilder[geneticMatrix.length - (ELIGIBLE_MUTANT_GENE_LENGTH - (turn >= 2 ? 0 : 1))];
            // Initialize array
            for (int i = 0; i < genes.length; i++)
                genes[i] = new StringBuilder();

            // Extracting genes
            for (int i = 0; i < geneticMatrix.length; i++)
                for (int j = 0; j < geneticMatrix[i].length(); j++)
                    // Course only in eligible mutant genes.
                    for (int k = (turn >= 2 ? 1 : 0); k < geneticMatrix.length - (ELIGIBLE_MUTANT_GENE_LENGTH - 1); k++)
                        // (j - k): It has the responsibility to find genes on the diagonal, without being the main diagonal.
                        if (i == j - k) {
                            final int index = (turn >= 2 ? k - 1 : k);
                            genes[index].append(geneticMatrix[i].charAt(j));
                            if (genes[index].toString().length() >= 4 && isMutant(genes[index].toString())) {
                                return true;
                            }
                        }

            // Rotate the matrix
            geneticMatrix = GeneticMatrixHelper.rotate(geneticMatrix);
        }

        return false;
    }

}
