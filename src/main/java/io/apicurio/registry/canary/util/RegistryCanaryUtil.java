package io.apicurio.registry.canary.util;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.apicurio.registry.rest.client.RegistryClient;
import io.apicurio.registry.rest.v2.beans.ArtifactMetaData;
import io.apicurio.registry.rest.v2.beans.IfExists;
import io.apicurio.registry.types.ArtifactType;

public class RegistryCanaryUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistryCanaryUtil.class);

    /**
     * Create the artifact in the registry (or update it if it already exists).
     *
     * @param artifactId
     * @param schema
     */
    public static void createSchemaInServiceRegistry(RegistryClient service, String artifactId, String schema) {

        LOGGER.info("---------------------------------------------------------");
        LOGGER.info("=====> Creating artifact in the registry for JSON Schema with ID: {}", artifactId);
        final ByteArrayInputStream content = new ByteArrayInputStream(schema.getBytes(StandardCharsets.UTF_8));
        final ArtifactMetaData metaData = service.createArtifact("default", artifactId, ArtifactType.JSON, IfExists.RETURN, content);
        assert metaData != null;
        LOGGER.info("=====> Successfully created JSON Schema artifact in Service Registry: {}", metaData);
        LOGGER.info("---------------------------------------------------------");
    }

    /**
     * Get the artifact from the registry.
     *
     * @param artifactId
     */
    public static ArtifactMetaData getSchemaFromRegistry(RegistryClient service, String artifactId) {

        LOGGER.info("---------------------------------------------------------");
        LOGGER.info("=====> Fetching artifact from the registry for JSON Schema with ID: {}", artifactId);
        final ArtifactMetaData metaData = service.getArtifactMetaData("default", artifactId);
        assert metaData != null;
        LOGGER.info("=====> Successfully fetched JSON Schema artifact in Service Registry: {}", metaData);
        LOGGER.info("---------------------------------------------------------");
        return metaData;
    }

    /**
     * Delete the artifact from the registry.
     *
     * @param artifactId
     */
    public static void deleteSchema(RegistryClient service, String artifactId) {

        LOGGER.info("---------------------------------------------------------");
        LOGGER.info("=====> Deleting artifact from the registry for JSON Schema with ID: {}", artifactId);
        service.deleteArtifact("default", artifactId);
        LOGGER.info("=====> Successfully deleted JSON Schema artifact in Service Registry.");
        LOGGER.info("---------------------------------------------------------");
    }
}
