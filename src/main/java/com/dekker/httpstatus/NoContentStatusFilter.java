package com.dekker.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class NoContentStatusFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        return webFilterChain
                .filter(serverWebExchange)
                .doFirst(() -> setNoContentIfRequired(serverWebExchange));
    }

    private void setNoContentIfRequired(ServerWebExchange serverWebExchange) {
        ServerHttpResponse response = serverWebExchange.getResponse();

        response.beforeCommit(
                () ->
                        shouldOverrideStatus(response)
                                ? Mono.fromSupplier(
                                                () -> response.setStatusCode(HttpStatus.NO_CONTENT))
                                        .then()
                                : Mono.empty());
    }

    private boolean shouldOverrideStatus(ServerHttpResponse response) {
        return response.getStatusCode() == null && response.getHeaders().getContentLength() <= 0;
    }
}
