# Haystack OpenTelemetry through Zipkin

[![Build Status](https://travis-ci.org/ExpediaDotCom/haystack-opentelemetry-example.svg?branch=master)](https://travis-ci.org/ExpediaDotCom/haystack-opentelemetry-example)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg)](https://github.com/ExpediaDotCom/haystack/blob/master/LICENSE)

[OpenTelemetry] provides a single set of APIs, libraries, agents, and collector services to capture distributed traces and metrics from your application. You can analyze them using Prometheus, Jaeger, and other observability tools.

Haystack can consume spans generated from [OpenTelemetry] by using its ZipkinSpanExporter. It currently supports languages such as:
  - [Java]
  - [Node.js]
  - [Python]

among others.


## How to integrate with Haystack
[Haystack Agent] is capable of consuming Zipkin spans through its [Pitchfork] endpoint. In order to enable this, we just need to turn it on in the configurations like this:
```
agents {
  spans {
    ...
  }
  ossblobs {
    ...
  }

  pitchfork {
    enabled = true
    port = 9411
    http.threads {
       max = 16
       min = 2
    }
    gzip.enabled = true
    idle.timeout.ms = 60000
    stop.timeout.ms = 30000
    accept.null.timestamps = false
    max.timestamp.drift.sec = -1

    dispatchers {
      kafka {
        bootstrap.servers = "<host>:19092"
        producer.topic = "proto-spans"
        buffer.memory = 1048576
        retries = 2
      }
    }
  }
}
```

Now after this, when agent initializes it'll listen on http://localhost:9411/api/v1/spans for zipkin spans. Please refer to Haystack Agent documentation for [Pitchfork] endpoint versions.

## Java ZipkinSpanExporter
This example will show how to export [OpenTelemetry] spans to haystack through [ZipkinSpanExporter]

### Dependencies
We nee to add appropriate [OpenTelemetry] and Zipkin libraries.
```
    <dependency>
        <groupId>io.opentelemetry</groupId>
        <artifactId>opentelemetry-api</artifactId>
        <version>LIBRARY_VERSION</version>
    </dependency>
    <dependency>
        <groupId>io.opentelemetry</groupId>
        <artifactId>opentelemetry-exporters-zipkin</artifactId>
        <version>LIBRARY_VERSION</version>
    </dependency>
    <dependency>
        <groupId>io.zipkin.zipkin2</groupId>
        <artifactId>zipkin</artifactId>
        <version>LIBRARY_VERSION</version>
    </dependency>
```

### Code Configuration

In the provided example, we first need to create a configuration object
```
ZipkinExporterConfiguration configuration =
            ZipkinExporterConfiguration
                .builder()
                .setEndpoint(<ZIPKIN_ENDPOINT>)
                .setServiceName(<SERVICE_NAME>)
                .setEncoder(SpanBytesEncoder.JSON_V2) // JSON_V1, PROTO3 or THRIFT
                .build();
```

After creating configuration we need to create a ZipkinSpanExporter which will be the object
that will actually send our spans.
```
 ZipkinSpanExporter exporter = ZipkinSpanExporter.create(configuration);
```

Once we have our exporter set up then we can start creating spans by using the SpanData class 
which zipkin consumes.
```
SpanData spandata = SpanData.newBuilder()
                    .setTraceId(...)
                    .setName(...)
                    .setSpanId(...)
                    .setStartEpochNanos(...)
                    .setEndEpochNanos(...)
                    .setHasEnded(...)
                    .setHasRemoteParent(...)
                    .setKind(...)
                    .setStatus(...)
                    .setAttributes(...)
                    .setTotalAttributeCount(...)
                    .build();
```

After we have created a span(s) all we need to do is use the method export from the previously created exporter. 
```
exporter.export(Arrays.asList(spandata));
```

Since this has configured the Haystack [Pitchfork] endpoint it'll send the spans there for 
consumption. [Haystack Agent] understands Zipkin spans and parses them into our own spans and 
process them into our pipeline.

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [Java]: https://github.com/open-telemetry/opentelemetry-java/blob/d7f6d5a64176b44752bbd4bd403716a0f150b3e6/exporters/zipkin/src/main/java/io/opentelemetry/exporters/zipkin/ZipkinSpanExporter.java
   [Node.js]:https://github.com/open-telemetry/opentelemetry-js/tree/master/packages/opentelemetry-exporter-zipkin
   [Python]:https://opentelemetry-python.readthedocs.io/en/latest/ext/zipkin/zipkin.html
   [OpenTelemetry]: https://opentelemetry.io/
   [Pitchfork]: https://github.com/ExpediaDotCom/haystack-agent#zipkin-agent-pitchfork
   [Haystack Agent]: https://github.com/ExpediaDotCom/haystack-agent
   
