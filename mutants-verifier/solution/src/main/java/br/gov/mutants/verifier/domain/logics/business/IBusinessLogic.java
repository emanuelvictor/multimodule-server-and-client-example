package br.gov.mutants.verifier.domain.logics.business;

public interface IBusinessLogic<T> {

    void perform(final T object);
}
