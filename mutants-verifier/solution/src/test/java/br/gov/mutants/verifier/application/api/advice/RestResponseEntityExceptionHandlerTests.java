package br.gov.mutants.verifier.application.api.advice;

import br.gov.mutants.verifier.application.exceptions.InvalidMatrixException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;

/**
 *
 */
public class RestResponseEntityExceptionHandlerTests {

    /**
     *
     */
    @Test
    public void testInvalidMatrixException() {

        final MockHttpServletRequest servletRequest = new MockHttpServletRequest();
        servletRequest.setServerName("www.example.com");
        servletRequest.setRequestURI("/v1/someuri");
        servletRequest.addParameter("brand1", "value1");
        servletRequest.addParameter("brand2", "value2");
        servletRequest.addParameter("another-param", "another-value");

        final ServletWebRequest webRequest = new ServletWebRequest(servletRequest);
        final RestResponseEntityExceptionHandler restResponseEntityExceptionHandler = new RestResponseEntityExceptionHandler();

        final ResponseEntity<Object> response = restResponseEntityExceptionHandler.handleException(new InvalidMatrixException(), webRequest);
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
    }
}
