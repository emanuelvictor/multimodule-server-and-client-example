package br.gov.mutants.verifier.infrastructure.clients.configuration;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author  Emanuel Victor
 * @version 1.0
 * @since   2022-04-29
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MutantsVerifierClientConfiguration.class)
public @interface EnableMutantsVerifierApiClient {
}