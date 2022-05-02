package br.gov.mutants.verifier.infrastructure.aid;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Collectors;

public final class GeneticMatrixHelper {

    public static final String[] MUTANTS_GENES = new String[]{"AAAA", "CCCC", "GGGG", "TTTT"};

    public static final String POSSIBLE_GENES = "AGCT";

    private static final Random random = new Random();

    public static final String[] GENETIC_MATRIX_LENGTH_6_WITH_HORIZONTAL_MUTANT = new String[]{"AaAAAG", "TGTAAC", "GGTTTC", "GGGAGC", "GTgACC", "GTAgAG"};

    public static final String[] GENETIC_MATRIX_LENGTH_6_WITH_MUTANT = new String[]{"AGGTAG", "TGTAAC", "GGTTTC", "GGGAGC", "GTgACC", "GTAgAG"};

    public static final String[] GENETIC_MATRIX_LENGTH_6_WITHOUT_MUTANT = new String[]{"AGGTAG", "TATAAC", "GGTTTA", "GGGAGC", "ATCACC", "GTATAG"};

    public static final String[] GENETIC_MATRIX_LENGTH_9_WITH_MUTANT = new String[]{"AGGTAGGTA", "GTAGTGTAA", "GGTTGTAGc", "GcGGAGGAG", "GTCGTAGAC", "GTcGGTATA", "GGTTGTAGT", "GcGTAGGcG", "GTCGTAGAg"};

    public static final String[] GENETIC_MATRIX_LENGTH_10_WITH_MUTANT = new String[]{"AGGTAGGTAc", "GTAGTGTAAa", "GGTTGTAGTa", "GcGGAGGAGC", "GTCGTAGACC", "GTcGGTATAG", "GGTTGTAGTC", "GcGTAGGcGg", "GTCGTAGAgC", "GTAGGTAgAC"};


    /**
     * @return String
     */
    public static String[] generateMutantGeneticMatrix(final int sizeOfMatrix) {
        final StringBuilder[] localGeneticMatrix = new StringBuilder[sizeOfMatrix];

        localGeneticMatrix[0] = new StringBuilder("A");
        // frist line
        for (int i = 1; i < sizeOfMatrix; i++) {
            if (localGeneticMatrix[0].charAt(localGeneticMatrix[0].length() - 1) == 'A') {
                localGeneticMatrix[0].append('G');
            } else if (localGeneticMatrix[0].charAt(localGeneticMatrix[0].length() - 1) == 'G') {
                localGeneticMatrix[0].append('C');
            } else if (localGeneticMatrix[0].charAt(localGeneticMatrix[0].length() - 1) == 'C') {
                localGeneticMatrix[0].append('T');
            } else {
                localGeneticMatrix[0].append('A');
            }
        }

        // frist line
        for (int i = 1; i < sizeOfMatrix; i++) {
            localGeneticMatrix[i] = new StringBuilder();
            for (int j = 0; j < sizeOfMatrix; j++) {
                if (localGeneticMatrix[i - 1].charAt(j) == 'A') {
                    localGeneticMatrix[i].append('G');
                } else if (localGeneticMatrix[i - 1].charAt(j) == 'G') {
                    localGeneticMatrix[i].append('C');
                } else if (localGeneticMatrix[i - 1].charAt(j) == 'C') {
                    localGeneticMatrix[i].append('T');
                } else {
                    localGeneticMatrix[i].append('A');
                }
            }
        }

        return Arrays.stream(localGeneticMatrix).map(StringBuilder::toString).collect(Collectors.toList()).toArray(new String[]{});
    }


    /**
     * @return String
     */
    public static String[] generateHumanGeneticMatrix(final int sizeOfMatrix) {
        final StringBuilder[] localGeneticMatrix = new StringBuilder[sizeOfMatrix];

        localGeneticMatrix[0] = new StringBuilder("A");
        // frist line
        for (int i = 1; i < sizeOfMatrix; i++) {
            if (localGeneticMatrix[0].charAt(localGeneticMatrix[0].length() - 1) == 'A') {
                localGeneticMatrix[0].append('G');
            } else if (localGeneticMatrix[0].charAt(localGeneticMatrix[0].length() - 1) == 'G') {
                localGeneticMatrix[0].append('C');
            } else if (localGeneticMatrix[0].charAt(localGeneticMatrix[0].length() - 1) == 'C') {
                localGeneticMatrix[0].append('T');
            } else {
                localGeneticMatrix[0].append('A');
            }
        }

        // frist line
        for (int i = 1; i < sizeOfMatrix; i++) {
            localGeneticMatrix[i] = new StringBuilder();
            for (int j = 0; j < sizeOfMatrix; j++) {
                if (localGeneticMatrix[i - 1].charAt(j) == 'A') {
                    localGeneticMatrix[i].append('C');
                } else if (localGeneticMatrix[i - 1].charAt(j) == 'G') {
                    localGeneticMatrix[i].append('A');
                } else if (localGeneticMatrix[i - 1].charAt(j) == 'C') {
                    localGeneticMatrix[i].append('T');
                } else
                    localGeneticMatrix[i].append('C');
            }
        }

        return Arrays.stream(localGeneticMatrix).map(StringBuilder::toString).collect(Collectors.toList()).toArray(new String[]{});
    }

    /**
     * @return String
     */
    public static String[] generateRandomGeneticMatrix(final int sizeOfMatrix) {
        final String[] localGeneticMatrix = new String[sizeOfMatrix];

        // Initialize array
        Arrays.fill(localGeneticMatrix, "");

        for (int i = 0; i < sizeOfMatrix; i++) {
            localGeneticMatrix[i] = generateRandomGene(sizeOfMatrix);
        }

        return localGeneticMatrix;
    }

    /**
     * @return String
     */
    public static String generateRandomGene(final int sizeOfMatrix) {
        final StringBuilder gene = new StringBuilder();
        for (int i = 0; i < sizeOfMatrix; i++) {
            gene.append(POSSIBLE_GENES.charAt(random.nextInt(4)));
        }
        return gene.toString();
    }

    /**
     * Rotate matrix by 90 degrees clockwise
     *
     * @param matrix String[]
     * @return String[]
     */
    public static String[] rotate(final String[] matrix) {

        // Populate matrix of chars with values of the matrix
        final char[][] matrixInChars = new char[matrix.length][matrix.length];
        for (int i = 0; i < matrixInChars.length; i++) {
            for (int j = 0; j < matrixInChars.length; j++) {
                matrixInChars[i][j] = matrix[i].charAt(j);
            }
        }

        for (int i = 0; i < matrixInChars.length; i++) {
            for (int j = i; j < matrixInChars.length; j++) {
                final char temp = matrixInChars[i][j];
                matrixInChars[i][j] = matrixInChars[j][i];
                matrixInChars[j][i] = temp;
            }
        }

        //then we reverse the elements of each row
        for (int i = 0; i < matrixInChars.length; i++) {
            //logic to reverse each row i.e 1D Array.
            int low = 0, high = matrixInChars.length - 1;
            while (low < high) {
                final char temp = matrixInChars[i][low];
                matrixInChars[i][low] = matrixInChars[i][high];
                matrixInChars[i][high] = temp;
                low++;
                high--;
            }
        }

        // Populate matrix to return
        final String[] matrixToReturn = new String[matrix.length];
        for (int i = 0; i < matrixInChars.length; i++) {
            matrixToReturn[i] = new String(matrixInChars[i]);
        }

        return matrixToReturn;
    }
}
