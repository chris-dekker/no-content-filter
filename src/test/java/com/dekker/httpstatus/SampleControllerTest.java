package com.dekker.httpstatus;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

abstract class SampleControllerTest {

    protected abstract WebTestClient getWebClient();

    @ParameterizedTest
    @MethodSource
    void testEmpty(HttpMethod method, String uri, HttpStatus expectedStatus) {
        getWebClient().method(method)
                .uri(uri)
                .exchange()
                .expectStatus().isEqualTo(expectedStatus);
    }

    private static Stream<Arguments> testEmpty() {
        return Stream.of(
                // An empty Mono<Void> should always return 204
                Arguments.of(HttpMethod.GET, "/samples/empty/void", HttpStatus.NO_CONTENT),
                // A Mono<String> should return 204 when its empty
                Arguments.of(HttpMethod.GET, "/samples/empty/string", HttpStatus.NO_CONTENT),
                // An endpoint explicitly annotated should never have its status altered
                Arguments.of(HttpMethod.GET, "/samples/empty/annotated", HttpStatus.ACCEPTED),
                // A non-empty Mono<String> should return 200
                Arguments.of(HttpMethod.GET, "/samples/payload/string", HttpStatus.OK),
                // A non-empty Flux<String> should return 200
                Arguments.of(HttpMethod.GET, "/samples/payload/strings", HttpStatus.OK),

                // All of the above assertions apply, regardless of HTTP METHOD
                Arguments.of(HttpMethod.PUT, "/samples/empty/void", HttpStatus.NO_CONTENT),
                Arguments.of(HttpMethod.PUT, "/samples/empty/string", HttpStatus.NO_CONTENT),
                Arguments.of(HttpMethod.PUT, "/samples/empty/annotated", HttpStatus.ACCEPTED),
                Arguments.of(HttpMethod.PUT, "/samples/payload/string", HttpStatus.OK),
                Arguments.of(HttpMethod.PUT, "/samples/payload/strings", HttpStatus.OK));
    }
}