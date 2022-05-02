package br.gov.mutants.verifier.domain.logics.validation;

public interface IValidation<T> {

    void perform(final T object);
}
