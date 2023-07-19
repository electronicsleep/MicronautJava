package com.imgidea.micronaut_java;

import io.micronaut.runtime.EmbeddedApplication;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import jakarta.inject.Inject;

@MicronautTest
class RunTest {

    @Inject
    EmbeddedApplication<?> application;

    @Test
    void testRunning() {
        Assertions.assertTrue(application.isRunning());
    }
}