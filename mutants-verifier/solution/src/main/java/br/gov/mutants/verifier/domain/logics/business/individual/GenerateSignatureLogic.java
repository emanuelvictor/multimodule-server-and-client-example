package br.gov.mutants.verifier.domain.logics.business.individual;

import br.gov.mutants.verifier.domain.entity.Individual;
import br.gov.mutants.verifier.domain.logics.business.IBusinessLogic;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Generate signature from individual by genes
 */
@Order(1)
@Component
public class GenerateSignatureLogic implements IBusinessLogic<Individual> {

    /**
     * @param individual Individual
     */
    @Override
    public void perform(final Individual individual) {
        individual.setSignature(String.join(Individual.SIGNATURE_SEPARATOR, individual.getGenes()));

    }
}
