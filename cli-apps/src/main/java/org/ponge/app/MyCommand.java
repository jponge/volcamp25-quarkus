package org.ponge.app;

import io.quarkus.runtime.Quarkus;
import picocli.CommandLine;

@CommandLine.Command(
        mixinStandardHelpOptions = true,
        description = "My Awesome CLI"
)
public class MyCommand implements Runnable {

    @CommandLine.Option(
            names = "--repeat",
            description = "How many times to repeat",
            required = true,
            defaultValue = "3"
    )
    int repeat;

    @Override
    public void run() {
        for (int i = 0; i < repeat; i++) {
            System.out.println("Hello Volcamp");
        }

        Quarkus.waitForExit();
    }
}
