package org.javacord.api.entity.channel;

import org.javacord.api.entity.channel.internal.ServerTextChannelBuilderDelegate;
import org.javacord.api.entity.server.Server;
import org.javacord.api.util.internal.DelegateFactory;

import java.util.concurrent.CompletableFuture;

/**
 * This class is used to create new server text channels.
 */
public class ServerTextChannelBuilder extends ServerChannelBuilder {

    /**
     * The server text channel delegate used by this instance.
     */
    private final ServerTextChannelBuilderDelegate delegate;

    /**
     * Creates a new server text channel builder.
     *
     * @param server The server of the server text channel.
     */
    public ServerTextChannelBuilder(Server server) {
        delegate = DelegateFactory.createServerTextChannelBuilderDelegate(server);
    }

    @Override
    public ServerTextChannelBuilder setAuditLogReason(String reason) {
        delegate.setAuditLogReason(reason);
        return this;
    }

    @Override
    public ServerTextChannelBuilder setName(String name) {
        delegate.setName(name);
        return this;
    }

    /**
     * Sets the topic of the channel.
     *
     * @param topic The topic of the channel.
     * @return The current instance in order to chain call methods.
     */
    public ServerTextChannelBuilder setTopic(String topic) {
        delegate.setTopic(topic);
        return this;
    }

    @Override
    public ServerTextChannelBuilder setCategory(ChannelCategory category) {
        delegate.setCategory(category);
        return this;
    }

    /**
     * Creates the server text channel.
     *
     * @return The created text channel.
     */
    public CompletableFuture<ServerTextChannel> create() {
        return delegate.create();
    }

}
