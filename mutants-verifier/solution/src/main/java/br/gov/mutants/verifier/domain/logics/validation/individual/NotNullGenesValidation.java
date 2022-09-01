package br.gov.mutants.verifier.domain.logics.validation.individual;

import br.gov.mutants.verifier.application.exceptions.GenesCannotBeNullExceptionInvalidException; //TODO coupling
import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.validation.IValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Validate if the genes is not null and not contains null values
 */
@Order(1)
@Component
@RequiredArgsConstructor
public class NotNullGenesValidation implements IValidation<Individual> {

    /**
     *
     */
    private final MessageSource messageSource;

    /**
     * @param individual Individual
     */
    @Override
    public void perform(final Individual individual) {
        if (individual.getGenes() == null)
            throw new GenesCannotBeNullExceptionInvalidException(messageSource.getMessage("genes-cannot-be-null", null, LocaleContextHolder.getLocale()));
        for (final String gene : individual.getGenes()) {
            if (gene == null)
                throw new GenesCannotBeNullExceptionInvalidException(messageSource.getMessage("gene-cannot-be-null", null, LocaleContextHolder.getLocale()));
        }
    }
}
