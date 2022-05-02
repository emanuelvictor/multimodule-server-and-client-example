package br.gov.mutants.verifier.infrastructure.clients.configuration;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;

import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import static org.springframework.beans.factory.config.YamlProcessor.MatchStatus.*;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * @author Emanuel Victor
 * @version 1.0
 * @since 2022-04-29
 */
public class YamlPropertySourceFactory implements PropertySourceFactory {

    /**
     * @param name            the name of the property source
     *                        (can be {@code null} in which case the factory implementation
     *                        will have to generate a name based on the given resource)
     * @param encodedResource the resource (potentially encoded) to wrap
     * @return {@link PropertySource}
     */
    @Override
    public PropertySource<?> createPropertySource(final String name, final EncodedResource encodedResource) {
        final String activeProfile = Optional.ofNullable(System.getenv("SPRING_PROFILES_ACTIVE"))
                .orElse(System.getProperty("spring.profiles.active"));

        final YamlPropertiesFactoryBean yamlFactory = new YamlPropertiesFactoryBean();
        yamlFactory.setDocumentMatchers(properties -> {
            final String profileProperty = properties.getProperty("spring.profiles");

            if (isEmpty(profileProperty)) {
                return ABSTAIN;
            }

            return profileProperty.contains(activeProfile) ? FOUND : NOT_FOUND;
        });
        yamlFactory.setResources(encodedResource.getResource());

        final Properties properties = yamlFactory.getObject();

        System.out.println(properties);
        return new PropertiesPropertySource(Objects.requireNonNull(encodedResource.getResource().getFilename()), properties);
    }
}