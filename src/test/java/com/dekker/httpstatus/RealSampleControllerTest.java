package com.dekker.httpstatus;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(NoContentStatusFilter.class)
class RealSampleControllerTest extends SampleControllerTest {

    @Autowired private WebTestClient webClient;

    @Override
    protected WebTestClient getWebClient() {
        return webClient;
    }
}
