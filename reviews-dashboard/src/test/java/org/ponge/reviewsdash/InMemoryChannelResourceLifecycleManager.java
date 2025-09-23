package org.ponge.reviewsdash;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import io.smallrye.reactive.messaging.memory.InMemoryConnector;

import java.util.HashMap;
import java.util.Map;

public class InMemoryChannelResourceLifecycleManager implements QuarkusTestResourceLifecycleManager {

    @Override
    public Map<String, String> start() {
        Map<String, String> env = new HashMap<>();
        env.putAll(InMemoryConnector.switchIncomingChannelsToInMemory("incoming-reviews"));
        env.putAll(InMemoryConnector.switchOutgoingChannelsToInMemory("dispatched-reviews"));
        env.putAll(InMemoryConnector.switchOutgoingChannelsToInMemory("bad-reviews"));
        return env;
    }

    @Override
    public void stop() {
        InMemoryConnector.clear();
    }
}
