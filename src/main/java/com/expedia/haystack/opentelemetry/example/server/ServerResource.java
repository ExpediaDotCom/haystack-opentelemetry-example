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
