/*
 * Copyright 2010-2018 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://aws.amazon.com/apache2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package software.amazon.awssdk.awscore.endpointdiscovery;

import java.net.URI;
import java.time.Instant;

public class EndpointDiscoveryEndpoint {

    private final URI endpoint;
    private final Instant expirationTime;

    private EndpointDiscoveryEndpoint(BuilderImpl builder) {
        this.endpoint = builder.endpoint;
        this.expirationTime = builder.expirationTime;
    }

    public URI endpoint() {
        return endpoint;
    }

    public Instant expirationTime() {
        return expirationTime;
    }

    public static Builder builder() {
        return new BuilderImpl();
    }

    public interface Builder {

        Builder endpoint(URI endpoint);

        Builder expirationTime(Instant expirationTime);

        EndpointDiscoveryEndpoint build();
    }

    private static final class BuilderImpl implements Builder {

        private URI endpoint;
        private Instant expirationTime;

        private BuilderImpl() {}

        @Override
        public Builder endpoint(URI endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public void setEndpoint(URI endpoint) {
            endpoint(endpoint);
        }

        @Override
        public Builder expirationTime(Instant expirationTime) {
            this.expirationTime = expirationTime;
            return this;
        }

        @Override
        public EndpointDiscoveryEndpoint build() {
            return new EndpointDiscoveryEndpoint(this);
        }

        public void setExpirationTime(Instant expirationTime) {
            expirationTime(expirationTime);
        }
    }
}
