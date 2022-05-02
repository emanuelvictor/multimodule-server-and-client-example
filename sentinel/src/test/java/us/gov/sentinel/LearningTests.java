package us.gov.sentinel;

import br.gov.mutants.verifier.client.v1.IndividualClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SentinelApplication.class)
public class LearningTests {

    /**
     *
     */
    @Autowired
    IndividualClient individualClient;

    /**
     *
     */
    @Test
    public void notNullStatsDTO() {
        Assertions.assertNotNull(individualClient);
        Assertions.assertNotNull(individualClient.stats());
    }

}
