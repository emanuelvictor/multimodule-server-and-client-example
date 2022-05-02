package br.gov.mutants.verifier.domain.logics.validation.individual;

import br.gov.mutants.verifier.application.exceptions.InvalidMatrixException;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.validation.IValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Verify if the matrix is a square matrix
 */
@Order(2)
@Component
@RequiredArgsConstructor
public class MatrixValidation implements IValidation<Individual> {

    /**
     *
     */
    private final MessageSource messageSource;

    /**
     * @param individual Individual
     */
    @Override
    public void perform(final Individual individual) {
        for (final String gene : individual.getGenes()) {
            if (gene.length() != individual.getGenes().length)
                throw new InvalidMatrixException(messageSource.getMessage("invalid-genetic-matrix", null, LocaleContextHolder.getLocale()));
        }
    }
}
