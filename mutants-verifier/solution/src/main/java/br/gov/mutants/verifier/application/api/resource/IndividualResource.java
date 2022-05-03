package br.gov.mutants.verifier.application.api.resource;

import br.gov.mutants.verifier.client.v1.dto.IndividualDTO;
import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import br.gov.mutants.verifier.domain.service.IndividualService;
import br.gov.mutants.verifier.application.api.mappers.IndividualMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class IndividualResource {

    /**
     *
     */
    private final IndividualMapper individualMapper;

    /**
     *
     */
    private final IndividualService individualService;

    /**
     * @param individualDTO IndividualDTO
     * @return boolean
     */
    @PostMapping(value = "/mutant", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> mutant(@RequestBody final IndividualDTO individualDTO) {

        final boolean isMutant = individualService.save(individualMapper.convert(individualDTO)).isMutant();

        // This requirement is an anti pattern, and this code mitigates it.
        // If the return was 403 http code, the individual should be not saved in database.
        return ResponseEntity.status(isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN).body(isMutant);
    }

    /**
     * @return Object
     */
    @GetMapping("stats")
    public StatsDTO stats() {
        return individualService.stats();
    }
}

