package org.ponge.app;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusMainTest
public class CliTest {

    @Test
    @Launch(value = "--help", exitCode = 0)
    public void checkHelp(LaunchResult result) {
        String out = result.getOutput();
        assertTrue(out.contains("[--host=<host>]"));
        assertTrue(out.contains("[--period=<period>]"));
        assertTrue(out.contains("[--port=<port>]"));
    }

    @Test
    @Launch(value = "--foo-bar-baz", exitCode = 2)
    public void badInvocation() {
    }
}
