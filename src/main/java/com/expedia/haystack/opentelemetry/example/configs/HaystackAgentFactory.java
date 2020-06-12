/*
 *  Copyright 2020 Expedia Group.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */
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
