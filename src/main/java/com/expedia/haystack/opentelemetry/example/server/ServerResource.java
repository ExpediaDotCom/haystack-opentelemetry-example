package com.expedia.haystack.opentelemetry.example.server;

import com.expedia.haystack.opentelemetry.example.configs.HaystackAgentFactory;
import com.expedia.haystack.opentelemetry.example.models.ServerResponse;
import com.expedia.haystack.opentelemetry.example.utils.OpenTelemetryUtils;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/span")
@Produces(MediaType.APPLICATION_JSON)
public class ServerResource {
    private OpenTelemetryUtils openTelemetryUtils;

    public ServerResource(HaystackAgentFactory factory) {
        this.openTelemetryUtils = new OpenTelemetryUtils(factory.getHaystackAgentHost(), factory.getPitchforkPort(), factory.getServiceName(), factory.getSpanType());
    }

    @GET
    public ServerResponse process(){
        return openTelemetryUtils.generateSpan();
    }
}
