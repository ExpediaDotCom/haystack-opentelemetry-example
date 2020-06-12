package com.expedia.haystack.opentelemetry.example.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ServerResponse implements Serializable {

    private String traceId;
    private String spanId;
    private String serviceName;
    private String result;

    public ServerResponse(String traceId, String spanId, String serviceName, String result) {
        this.traceId = traceId;
        this.spanId = spanId;
        this.serviceName = serviceName;
        this.result = result;
    }

    @JsonProperty
    public String getTraceId() {
        return traceId;
    }

    @JsonProperty
    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }

    @JsonProperty
    public String getSpanId() {
        return spanId;
    }

    @JsonProperty
    public void setSpanId(String spanId) {
        this.spanId = spanId;
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
    public void setResult(String resul) { this.result = result; }

    @JsonProperty
    public String getResult() { return this.result; }
}
