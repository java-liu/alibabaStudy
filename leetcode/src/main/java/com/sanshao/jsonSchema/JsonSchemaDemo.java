package com.sanshao.jsonSchema;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;
import java.io.IOException;

public class JsonSchemaDemo {
    public static void main(String[] args) throws IOException, ProcessingException {
        //创建工厂
        JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        //
        JsonNode schemaNode = JsonLoader.fromFile(new File("schema.json"));
        JsonNode dataNode = JsonLoader.fromFile(new File("source.json"));
        JsonSchema schema = factory.getJsonSchema(schemaNode);
        ProcessingReport processingReport = schema.validate(dataNode);
        System.out.println(processingReport);
        System.out.println(processingReport.isSuccess());
    }
}
