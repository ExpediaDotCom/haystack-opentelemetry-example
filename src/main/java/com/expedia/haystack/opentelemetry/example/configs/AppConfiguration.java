package com.expedia.haystack.opentelemetry.example.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.client.JerseyClientConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AppConfiguration extends Configuration {
    @Valid
    @NotNull
    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

    private HaystackAgentFactory haystackAgentFactory = new HaystackAgentFactory();

    @JsonProperty("jerseyClient")
    public JerseyClientConfiguration getJerseyClientConfiguration() {
        return jerseyClient;
    }

    @JsonProperty("haystackAgent")
    public HaystackAgentFactory getHaystackAgentFactory() { return haystackAgentFactory; }

    @JsonProperty("haystackAgent")
    public void setHaystackAgentFactory(HaystackAgentFactory haystackAgentFactory) { this.haystackAgentFactory = haystackAgentFactory; }
}
