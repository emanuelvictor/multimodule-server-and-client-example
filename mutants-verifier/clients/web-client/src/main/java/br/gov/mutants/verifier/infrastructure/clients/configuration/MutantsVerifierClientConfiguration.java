package br.gov.mutants.verifier.infrastructure.clients.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author  Emanuel Victor
 * @version 1.0
 * @since   2022-04-29
 */
@Configuration
@ComponentScan("br") // The root package is br (BR.gov.mutants....)
@PropertySource(value = "classpath:config/mutants-verifier-${spring.profiles.active}-client-application.yml", factory = YamlPropertySourceFactory.class) // TODO hardcoded
public class MutantsVerifierClientConfiguration {
}
