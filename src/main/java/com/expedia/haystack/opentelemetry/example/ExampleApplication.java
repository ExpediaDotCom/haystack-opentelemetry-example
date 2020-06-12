package com.expedia.haystack.opentelemetry.example;

import com.expedia.haystack.opentelemetry.example.server.ServerApplication;

public class ExampleApplication {
    public static void main(String[] args) throws Exception {
        new ServerApplication().run("server", "config-client.yaml");
    }
}
