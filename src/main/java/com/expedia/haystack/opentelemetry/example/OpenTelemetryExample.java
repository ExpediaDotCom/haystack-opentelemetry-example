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

package com.expedia.haystack.opentelemetry.example;

import io.opentelemetry.common.AttributeValue;
import io.opentelemetry.exporters.zipkin.ZipkinExporterConfiguration;
import io.opentelemetry.exporters.zipkin.ZipkinSpanExporter;
import io.opentelemetry.sdk.trace.data.SpanData;
import io.opentelemetry.trace.Span;
import io.opentelemetry.trace.SpanId;
import io.opentelemetry.trace.Status;
import io.opentelemetry.trace.TraceId;
import zipkin2.codec.SpanBytesEncoder;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class OpenTelemetryExample {
    private static final int NUMBER_OF_SPANS_TO_SEND = 25;
    private static final String ZIPKIN_ENDPOINT = "http://localhost:9411/api/v2/spans";
    private static final String SERVICE_NAME = "my-service";

    public static void main(String[] args) throws InterruptedException {
        generateSpans(NUMBER_OF_SPANS_TO_SEND);
    }

    private static void generateSpans(int numberOfSpans) throws InterruptedException {
        for(int i = 0; i < numberOfSpans; i ++) {
            ZipkinExporterConfiguration configuration =
                    ZipkinExporterConfiguration.builder()
                            .setEndpoint(ZIPKIN_ENDPOINT)
                            .setServiceName(SERVICE_NAME)
                            .setEncoder(SpanBytesEncoder.JSON_V2)
                            .build();

            ZipkinSpanExporter exporter = ZipkinSpanExporter.create(configuration);

            Random random = new Random();
            TraceId traceId = new TraceId(random.nextLong(), random.nextLong());
            SpanId spanId = new SpanId(random.nextLong());
            Map<String, AttributeValue> attributeValueMap = new HashMap<>();
            attributeValueMap.put("attribute_key1", AttributeValue.stringAttributeValue("attribute_value"));
            attributeValueMap.put("attribute_key_numbered", AttributeValue.stringAttributeValue("attribute_value_" + i));

            SpanData spandata = SpanData.newBuilder()
                    .setTraceId(traceId)
                    .setName("span_name" + i)
                    .setSpanId(spanId)
                    .setStartEpochNanos(TimeUnit.NANOSECONDS.convert(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), TimeUnit.SECONDS))
                    .setEndEpochNanos(TimeUnit.NANOSECONDS.convert(LocalDateTime.now().toEpochSecond(ZoneOffset.UTC), TimeUnit.SECONDS))
                    .setHasEnded(false)
                    .setHasRemoteParent(false)
                    .setKind(Span.Kind.CLIENT)
                    .setStatus(Status.OK)
                    .setAttributes(attributeValueMap)
                    .setTotalAttributeCount(2)
                    .build();

            exporter.export(Arrays.asList(spandata));
            Thread.sleep(1000);
        }
    }
}
