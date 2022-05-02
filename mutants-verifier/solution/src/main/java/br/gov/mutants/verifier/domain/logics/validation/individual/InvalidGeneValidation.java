package br.gov.mutants.verifier.domain.logics.validation.individual;

import br.gov.mutants.verifier.application.exceptions.InvalidGeneException;
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.validation.IValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Validate if the genes contains only "AGTC" characters
 */
@Order(3)
@Component
@RequiredArgsConstructor
public class InvalidGeneValidation implements IValidation<Individual> {

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
            if (!isValid(gene)) {
                throw new InvalidGeneException(messageSource.getMessage("the-gene-is-invalid", new Object[]{gene}, LocaleContextHolder.getLocale()));
            }
        }
    }

    /**
     * @param gene String
     * @return boolean
     */
    public static boolean isValid(final String gene) {
        return gene.matches("[agtcAGTC]+");
    }
}
