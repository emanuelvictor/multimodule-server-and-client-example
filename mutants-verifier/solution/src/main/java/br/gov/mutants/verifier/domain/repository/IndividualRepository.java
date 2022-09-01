package br.gov.mutants.verifier.domain.repository;

import br.gov.mutants.verifier.commons.v1.dto.StatsDTO; //TODO coupling
import br.gov.mutants.verifier.domain.entity.Individual;
import org.springframework.data.jpa.repository.JpaRepository; //TODO coupling
import org.springframework.data.jpa.repository.Query; //TODO coupling
import org.springframework.stereotype.Repository; //TODO coupling

@Repository
public interface IndividualRepository extends JpaRepository<Individual, String> {

// TODO hardcoded
    @Query("SELECT new br.gov.mutants.verifier.commons.v1.dto.StatsDTO(" +
            "   (select count(*) from Individual individual where individual.mutant IS TRUE) as mutant," +
            "   (select count(*) from Individual individual where individual.mutant IS FALSE) as human" +
            ")" +
            "FROM Individual individual " +
            "GROUP BY human")
    StatsDTO stats();
}
