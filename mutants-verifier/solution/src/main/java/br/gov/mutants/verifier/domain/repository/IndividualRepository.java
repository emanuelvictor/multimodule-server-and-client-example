package br.gov.mutants.verifier.domain.repository;

import br.gov.mutants.verifier.client.v1.dto.StatsDTO;
import br.gov.mutants.verifier.domain.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {

// TODO hardcoded
    @Query("SELECT new br.gov.mutants.verifier.client.v1.dto.StatsDTO(" +
            "   (select count(*) from Individual individual where individual.mutant IS TRUE) as mutant," +
            "   (select count(*) from Individual individual where individual.mutant IS FALSE) as human" +
            ")" +
            "FROM Individual individual " +
            "GROUP BY human")
    StatsDTO stats();
}
