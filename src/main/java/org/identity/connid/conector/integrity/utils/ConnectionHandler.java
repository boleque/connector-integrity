package org.identity.connid.conector.integrity.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import org.apache.cxf.jaxrs.client.JAXRSClientFactoryBean;
import org.identity.connid.conector.integrity.IntegrityConfiguration;
import org.identity.connid.conector.integrity.api.ClientService;

public abstract class ConnectionHandler {

    private IntegrityConfiguration configuration;

    public ConnectionHandler(IntegrityConfiguration configuration) {
        this.configuration = configuration;
    }

    protected <T extends ClientService> T setupClient(Class<T> type) {
        JAXRSClientFactoryBean bean = new JAXRSClientFactoryBean();
        bean.setResourceClass(type);
        bean.setAddress(this.configuration.getBaseUrl());

        String username = this.configuration.getUserName();
        if (username != null) {
            bean.setUsername(username);

            StringAccessor accessor = new StringAccessor();
            if (this.configuration.getPassword() != null) {
                this.configuration.getPassword().access(accessor);
                bean.setPassword(accessor.getValue());
            }
        }

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        ObjectMapper mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.setVisibility(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        provider.setMapper(mapper);

        bean.setProvider(provider);

        return bean.create(type);
    }

    protected void setConfiguration(IntegrityConfiguration configuration) {
        this.configuration = configuration;
    }
}
