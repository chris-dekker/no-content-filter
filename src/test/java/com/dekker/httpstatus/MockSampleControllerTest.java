package com.dekker.httpstatus;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@WebFluxTest
@Import(NoContentStatusFilter.class)
class MockSampleControllerTest extends SampleControllerTest {

    @Autowired private WebTestClient webClient;

    @Override
    protected WebTestClient getWebClient() {
        return webClient;
    }
}
