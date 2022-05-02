package br.gov.mutants.verifier.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

/**
 *
 */
@Entity
public class Individual {

    public static final String SIGNATURE_SEPARATOR = ";";

    /**
     *
     */
    @Id
    @Getter
    @Setter
    @Column(columnDefinition = "TEXT")
    private String signature;

    /**
     *
     */
    @Setter
    @Column(nullable = false)
    private Boolean mutant;

    /**
     *
     */
    @Setter
    @Getter
    @Transient
    private String[] genes;

    /**
     * @return Boolean
     */
    public Boolean isMutant() {
        return this.mutant;
    }

}
