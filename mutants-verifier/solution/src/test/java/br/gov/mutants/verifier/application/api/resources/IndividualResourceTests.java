package br.gov.mutants.verifier.application.api.resources;


import br.gov.mutants.verifier.application.api.resource.IndividualResource;
import br.gov.mutants.verifier.application.exceptions.GenesCannotBeNullExceptionInvalidException;
import br.gov.mutants.verifier.application.i18n.MessageSourceHolder;
import br.gov.mutants.verifier.commons.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.commons.v1.dto.StatsDTO;
import br.gov.mutants.verifier.infrastructure.aid.GeneticMatrixHelper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IndividualResourceTests {

    /**
     *
     */
    @Autowired
    private MockMvc mockMvc;

    /**
     *
     */
    @Autowired
    private IndividualResource individualResource;

    /**
     *
     */
    @Test
    @Sql("/dataset/truncate.sql")
    public void saveIndividualTest() {
        final IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setDna(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_9_WITH_MUTANT);

        Assertions.assertThat(individualResource.mutant(individualDTO).getBody()).isTrue();
    }

    /**
     *
     */
    @Test
    public void saveIndividualWithNullGeneTest() {

        final String[] genes = new String[GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length];
        System.arraycopy(GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT, 0, genes, 0, GeneticMatrixHelper.GENETIC_MATRIX_LENGTH_6_WITH_MUTANT.length);

        genes[genes.length - 1] = null;

        final IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setDna(genes);

        Assertions.assertThatThrownBy(() -> individualResource.mutant(individualDTO)).isInstanceOf(GenesCannotBeNullExceptionInvalidException.class);
    }

    /**
     *
     */
    @Sql({
            "/dataset/truncate.sql",
            "/dataset/insert_10_mutants.sql",
            "/dataset/insert_4_humans.sql",
    })
    @Test
    public void statsTest() {
        final StatsDTO statsDTO = individualResource.stats();
        Assertions.assertThat(statsDTO).isNotNull();
        Assertions.assertThat(statsDTO.getCountHumanDna()).isEqualTo(4);
        Assertions.assertThat(statsDTO.getCountMutantDna()).isEqualTo(10);
        Assertions.assertThat(statsDTO.getRatio()).isEqualTo(new BigDecimal("2.50"));
    }

    /**
     * @throws Exception
     */
    @Test
    @Sql("/dataset/truncate.sql")
    public void postMutantToEndpointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/mutant")
                        .content(
                                "{" +
                                        "\"dna\": [\"AGGTAGGTA\",\"GTAGTGTAA\",\"GGTTGTAGc\",\"GcGGAGGAG\",\"GTCGTAGAC\",\"GTcGGTATA\",\"GGTTGTAGT\",\"GcGTAGGcG\",\"GTCGTAGAg\"]" +
                                        "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    /**
     * @throws Exception
     */
    @Test
    @Sql("/dataset/truncate.sql")
    public void postHumanToEndpointTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/v1/mutant")
                        .content(
                                "{" +
                                        "\"dna\": [\"AGGTAG\",\"TATAAC\",\"GGTTTA\",\"GGGAGC\",\"ATCACC\",\"GTATAG\"]" +
                                        "}"
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden())
                .andExpect(content().string("false"));
    }

    /**
     * @throws Exception
     */
    @Test
    public void postHumanWithInvalidGeneEndpointTest() throws Exception {
        final Exception exception = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/mutant")
                                .content(
                                        "{" +
                                                "\"dna\": [\"AGGTAG\",\"TATAAC\",\"GGTTTA\",\"GGGAGC\",\"ATCACC\",\"GTATA1\"]" +
                                                "}"
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException();

        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getMessage()).isEqualTo(MessageSourceHolder.getMessage(LocaleContextHolder.getLocale(), "the-gene-is-invalid", "GTATA1"));
    }

    /**
     * @throws Exception
     */
    @Test
    public void postHumanWithNullGenesEndpointTest() throws Exception {

        final Exception exception = mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/mutant")
                                .content(
                                        "{}"
                                )
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
                .andReturn().getResolvedException();

        Assertions.assertThat(exception).isNotNull();
        Assertions.assertThat(exception.getMessage()).isEqualTo(MessageSourceHolder.getMessage("genes-cannot-be-null"));
    }

    /**
     * @throws Exception
     */
    @Test
    @Sql("/dataset/truncate.sql")
    public void postMassiveMutantsGenesEndpointTest() throws Exception {

        final String jsonString = "{" +
                "\"dna\" : [\"" + String.join("\",\"", GeneticMatrixHelper.generateMutantGeneticMatrix(426)) + "\"]" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/mutant")
                                .content(jsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    /**
     * @throws Exception
     */
    @Test
    @Sql("/dataset/truncate.sql")
    public void postMassiveHumansGenesEndpointTest() throws Exception {

        final String jsonString = "{" +
                "\"dna\" : [\"" + String.join("\",\"", GeneticMatrixHelper.generateHumanGeneticMatrix(420)) + "\"]" +
                "}";

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/v1/mutant")
                                .content(jsonString)
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isForbidden())
                .andExpect(content().string("false"));
    }
}

