package br.gov.mutants.verifier.client.v1;


import br.gov.mutants.verifier.client.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * {@link IndividualClient} is an implementation to verify if genetic matrix is a mutant or normal human (see in {@link IndividualClient#mutant(IndividualDTO)}).
 * Also return the instatistics of the verifications on the endpoint {@link IndividualClient#stats()}
 *
 * @author  Emanuel Victor
 * @version 1.0
 * @since   2022-04-29
 */
@Component
public class IndividualClient {

    /**
     *
     */
    private final static Logger LOGGER = LoggerFactory.getLogger(IndividualClient.class);

    /**
     * {@link org.springframework.web.reactive.function.client.WebClient}
     */
    private final WebClient client;

    /**
     * Constructor with the URL
     *
     * @param url {@link String}
     */
    protected IndividualClient(@Value("${endpoints.individual.url}") final String url) {
        LOGGER.info(url);
        client = WebClient.create(url);
    }

    /**
     * Return the statistcs of the individuals.
     *
     * @return {@link StatsDTO}
     */
    public StatsDTO stats() {
        LOGGER.info("Getting statistc data");
        return client.get().uri("stats").retrieve().bodyToMono(StatsDTO.class).block();
    }

    /**
     * Endpoint to verify if the {@link IndividualDTO} with genetic matrix, is a mutant or normal human.
     *
     * @param individualDTO {@link IndividualDTO}
     * @return {@link Boolean}
     */
    public Boolean mutant(final IndividualDTO individualDTO) {
        LOGGER.info("Post individual data");
        return client.post().uri("mutant").body(Mono.just(individualDTO), IndividualDTO.class).retrieve().bodyToMono(Boolean.class).block();
    }
}