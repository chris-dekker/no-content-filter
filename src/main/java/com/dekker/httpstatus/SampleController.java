package com.dekker.httpstatus;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/samples")
public class SampleController {

    @RequestMapping(value = "/empty/void")
    Mono<Void> emptyVoid() {
        return Mono.empty();
    }

    @RequestMapping(value = "/empty/string")
    Mono<String> emptyString() {
        return Mono.empty();
    }

    @RequestMapping(value = "/empty/annotated")
    @ResponseStatus(HttpStatus.ACCEPTED)
    Mono<String> emptyAnnotated() {
        return Mono.empty();
    }

    @RequestMapping(value = "/payload/string")
    Mono<String> contentString() {
        return Mono.just("aaa");
    }

    @RequestMapping(value = "/payload/strings")
    Flux<String> contentStrings() {
        return Flux.just("a", "b", "c");
    }
}
