package br.gov.mutants.verifier.application.i18n;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

public class MessageSourceHolderTests {

    /**
     *
     */
    @Nested
    public class WhenMustFailDone {

        /**
         *
         */
        @Test
        public void getExceptionsTests() {
            final ResourceBundleMessageSource resourceBundleMessageSource = (ResourceBundleMessageSource) new MessageSourceConfiguration().messageSource();
            MessageSourceHolder.setMessageSource(resourceBundleMessageSource);
            Assertions.assertThat(MessageSourceHolder.getMessageSource()).isNotNull();

            Assertions.assertThat(MessageSourceHolder.getMessage("genes-cannot-be-null")).isNotNull();
            Assertions.assertThat(MessageSourceHolder.getMessage("gene-cannot-be-null")).isNotNull();
            Assertions.assertThat(MessageSourceHolder.getMessage("the-gene-is-invalid", new Object[]{"AGCTG1"}, LocaleContextHolder.getLocale())).isNotNull();
            Assertions.assertThat(MessageSourceHolder.getMessage("invalid-genetic-matrix")).isNotNull();

            // Portuguese tests
            final Locale brazil = new Locale("pt", "BR");
            Assertions.assertThat(MessageSourceHolder.getMessage(brazil, "genes-cannot-be-null")).isEqualTo("Os genes não podem ser nullos!");
            Assertions.assertThat(MessageSourceHolder.getMessage(brazil, "gene-cannot-be-null")).isEqualTo("Há pelo menos um gene nullo!");
            Assertions.assertThat(MessageSourceHolder.getMessage(brazil, "the-gene-is-invalid", "AGCTG1")).isEqualTo("O gene \"AGCTG1\" é inválido!");
            Assertions.assertThat(MessageSourceHolder.getMessage(brazil, "invalid-genetic-matrix")).isEqualTo("A matriz genética não é válida!");

            // English tests
            final Locale english = new Locale("en", "US");
            Assertions.assertThat(MessageSourceHolder.getMessage(english, "genes-cannot-be-null")).isEqualTo("The genes cannot be null!");
            Assertions.assertThat(MessageSourceHolder.getMessage(english, "gene-cannot-be-null")).isEqualTo("There are one or more null genes!");
            Assertions.assertThat(MessageSourceHolder.getMessage(english, "the-gene-is-invalid", "AGCTG1")).isEqualTo("Invalid gene: AGCTG1!");
            Assertions.assertThat(MessageSourceHolder.getMessage(english, "invalid-genetic-matrix")).isEqualTo("Invalid genetic matrix!");
        }
    }


}
