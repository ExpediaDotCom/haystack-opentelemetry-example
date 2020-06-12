package com.expedia.haystack.opentelemetry.example.configs;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HaystackAgentFactory {
    private String haystackAgentHost;
    private String pitchforkPort;
    private String serviceName;
    private String spanType;

    @JsonProperty
    public String getHaystackAgentHost() {
        return haystackAgentHost;
    }

    @JsonProperty
    public void setHaystackAgentHost(String haystackAgentHost) {
        this.haystackAgentHost = haystackAgentHost;
    }

    @JsonProperty
    public String getPitchforkPort() {
        return pitchforkPort;
    }

    @JsonProperty
    public void setPitchforkPort(String pitchforkPort) {
        this.pitchforkPort = pitchforkPort;
    }

    @JsonProperty
    public String getServiceName() {
        return serviceName;
    }

    @JsonProperty
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    @JsonProperty
    public String getSpanType() {
        return spanType;
    }

    @JsonProperty
    public void setSpanType(String spanType) {
        this.spanType = spanType;
    }

}
