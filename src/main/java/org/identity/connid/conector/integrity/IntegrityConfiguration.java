/*
 * Copyright (c) 2010-2014 Evolveum
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.identity.connid.conector.integrity;

import org.identityconnectors.common.StringUtil;
import org.identityconnectors.common.logging.Log;
import org.identityconnectors.common.security.GuardedString;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.spi.AbstractConfiguration;
import org.identityconnectors.framework.spi.ConfigurationProperty;

import java.util.Objects;


public class IntegrityConfiguration extends AbstractConfiguration {

    private static final Log LOG = Log.getLog(IntegrityConfiguration.class);

    private String username;

    private GuardedString password;

    private GuardedString apiKey;

    private String baseUrl;

    private int defaultQueryPageSize = 50;

    private int connectionTimeoutInSeconds = 10;

    private int readTimeoutInSeconds = 10;

    private int writeTimeoutInSeconds = 10;

    @Override
    public void validate() {
        LOG.info("Processing trough configuration validation procedure");
        String exceptionMsg = "";
        if (Objects.isNull(username) || StringUtil.isBlank(username)) {
            exceptionMsg = "Username url is required";
            LOG.error(exceptionMsg.toString());
            throw new ConfigurationException(exceptionMsg);
        }
        if (Objects.isNull(baseUrl) || StringUtil.isBlank(baseUrl)) {
            exceptionMsg = "baseUrl url is required";
            LOG.error(exceptionMsg.toString());
            throw new ConfigurationException(exceptionMsg);
        }
        if (Objects.isNull(password)) {
            exceptionMsg = "Password is required";
            LOG.error(exceptionMsg.toString());
            throw new ConfigurationException(exceptionMsg);
        }
        if (Objects.isNull(apiKey)) {
            exceptionMsg = "Api key is required";
            LOG.error(exceptionMsg.toString());
            throw new ConfigurationException(exceptionMsg);
        }
        LOG.info("Configuration id valid");
    }

    @ConfigurationProperty(
            order = 1,
            displayMessageKey = "User Name",
            helpMessageKey = "Set an user name for Basic Authorization")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ConfigurationProperty(
            order = 2,
            displayMessageKey = "Password",
            helpMessageKey = "Set a password for Basic Authorization")
    public GuardedString getPassword() {
        return password;
    }

    public void setPassword(GuardedString password) {
        this.password = password;
    }

    @ConfigurationProperty(
            order = 3,
            displayMessageKey = "Api Key",
            helpMessageKey = "Every request header should include an API KEY")
    public GuardedString getApiKey() {
        return apiKey;
    }

    public void setApiKey(GuardedString apiKey) {
        this.apiKey = apiKey;
    }

    @ConfigurationProperty(
            order = 4,
            displayMessageKey = "Base Url",
            helpMessageKey = "Example http://190.167.219.10/restApi/v2")
    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @ConfigurationProperty(
            order = 5,
            displayMessageKey = "Default Query Page Size",
            helpMessageKey = "Number of results to return per page. (Default: 50)",
            required = false,
            confidential = false)
    public int getDefaultQueryPageSize() {
        return defaultQueryPageSize;
    }

    public void setDefaultQueryPageSize(int defaultQueryPageSize) {
        this.defaultQueryPageSize = defaultQueryPageSize;
    }

    @ConfigurationProperty(
            order = 6,
            displayMessageKey = "Connection Timeout (in seconds)",
            helpMessageKey = "Connection timeout when connecting to SmartHR. (Default: 10)",
            required = false,
            confidential = false)
    public int getConnectionTimeoutInSeconds() {
        return connectionTimeoutInSeconds;
    }

    public void setConnectionTimeoutInSeconds(int connectionTimeoutInSeconds) {
        this.connectionTimeoutInSeconds = connectionTimeoutInSeconds;
    }

    @ConfigurationProperty(
            order = 7,
            displayMessageKey = "Read Timeout (in seconds)",
            helpMessageKey = "Read timeout when fetching data from SmartHR. (Default: 10)",
            required = false,
            confidential = false)
    public int getReadTimeoutInSeconds() {
        return readTimeoutInSeconds;
    }

    public void setReadTimeoutInSeconds(int readTimeoutInSeconds) {
        this.readTimeoutInSeconds = readTimeoutInSeconds;
    }

    @ConfigurationProperty(
            order = 8,
            displayMessageKey = "Write Timeout (in seconds)",
            helpMessageKey = "Write timeout when fetching data from SmartHR. (Default: 10)",
            required = false,
            confidential = false)
    public int getWriteTimeoutInSeconds() {
        return writeTimeoutInSeconds;
    }

    public void setWriteTimeoutInSeconds(int writeTimeoutInSeconds) {
        this.writeTimeoutInSeconds = writeTimeoutInSeconds;
    }
}