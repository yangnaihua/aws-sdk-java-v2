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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProviderChain;
import software.amazon.awssdk.utils.SdkAutoCloseable;
import software.amazon.awssdk.utils.Validate;

public class EndpointDiscoveryProviderChain implements EndpointDiscoveryProvider, SdkAutoCloseable {

    private final List<EndpointDiscoveryProvider> endpointDiscoveryProviders;

    private EndpointDiscoveryProviderChain(BuilderImpl builder) {
        this.endpointDiscoveryProviders = Collections.unmodifiableList(
            Validate.notEmpty(builder.endpointDiscoveryProviders, "No endpoint discovery providers were specified."));
    }

    @Override
    public boolean resolveEndpointDiscovery() {
        return false;
    }

    @Override
    public void close() {

    }

    public interface Builder {

        /**
         * Configure the credentials providers that should be checked for credentials, in the order they should be checked.
         */
        Builder endpointDiscoveryProviders(Collection<? extends EndpointDiscoveryProvider> endpointDiscoveryProviders);

        /**
         * Configure the credentials providers that should be checked for credentials, in the order they should be checked.
         */
        Builder endpointDiscoveryProviders(EndpointDiscoveryProvider... endpointDiscoveryProviders);

        /**
         * Add a credential provider to the chain, after the credential providers that have already been configured.
         */
        Builder addEndpointDiscoveryProvider(EndpointDiscoveryProvider endpointDiscoveryProviders);

        EndpointDiscoveryProviderChain build();
    }

    private static final class BuilderImpl implements Builder {
        private List<EndpointDiscoveryProvider> endpointDiscoveryProviders = new ArrayList<>();

        private BuilderImpl() {}

        @Override
        public Builder endpointDiscoveryProviders(Collection<? extends EndpointDiscoveryProvider> endpointDiscoveryProviders) {
            this.endpointDiscoveryProviders = new ArrayList<>(endpointDiscoveryProviders);
            return this;
        }

        public void setEndpointDiscoveryProviders(Collection<? extends EndpointDiscoveryProvider> endpointDiscoveryProviders) {
            endpointDiscoveryProviders(endpointDiscoveryProviders);
        }

        @Override
        public Builder endpointDiscoveryProviders(EndpointDiscoveryProvider... endpointDiscoveryProviders) {
            return endpointDiscoveryProviders(Arrays.asList(endpointDiscoveryProviders));
        }

        @Override
        public Builder addEndpointDiscoveryProvider(EndpointDiscoveryProvider endpointDiscoveryProviders) {
            this.endpointDiscoveryProviders.add(endpointDiscoveryProviders);
            return this;
        }

        @Override
        public EndpointDiscoveryProviderChain build() {
            return new EndpointDiscoveryProviderChain(this);
        }
    }
}
