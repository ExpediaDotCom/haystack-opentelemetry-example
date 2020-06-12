package com.expedia.haystack.opentelemetry.example.server;

import com.expedia.haystack.opentelemetry.example.configs.AppConfiguration;
import io.dropwizard.Application;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class ServerApplication extends Application<AppConfiguration>  {
    @Override
    public String getName() {
        return "server";
    }

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new ResourceConfigurationSourceProvider());
    }

    @Override
    public void run(AppConfiguration appConfiguration, Environment environment){
        environment.jersey().register(new ServerResource(appConfiguration.getHaystackAgentFactory()));
    }
}
