package conditions;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.qameta.allure.Step;
import io.restassured.path.json.JsonPath;
import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class SchemaCondition {

    @SneakyThrows
    @Step("Проверка json-схемой 'default'")
    public void schemaValidation(String json) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(getSchemaByJson(json));
        JsonNode validationFor = objectMapper.readTree(json);
        JsonSchema jsonSchema = jsonSchemaFactory.getSchema(schemaNode);
        Set<ValidationMessage> errorMessage = jsonSchema.validate(validationFor);
        StringBuilder buffer = new StringBuilder();
        for (ValidationMessage error : errorMessage) {
            buffer.append(error.getMessage()).append("\n");
        }
        assertThat(buffer.toString()).isEmpty();
    }

    @SneakyThrows
    @Step("Валидация json-схемой '{schema}'")
    public void schemaValidation(String json, String schema) {
        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V202012);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode schemaNode = objectMapper.readTree(getSchemaByOper(schema));
        JsonSchema jsonSchema = jsonSchemaFactory.getSchema(schemaNode);
        Set<ValidationMessage> errorMessage = jsonSchema.validate(objectMapper.readTree(json));
        StringBuilder buffer = new StringBuilder();
        for (ValidationMessage error : errorMessage) {
            buffer.append(error.getMessage()).append("\n");
        }
        assertThat(buffer.toString()).isEmpty();
    }

    private String getSchemaByJson(String json) {
        String tempOper = JsonPath.from(json).getString("oper");
        return getSchemaByOper(tempOper);
    }

    @SneakyThrows
    private String getSchemaByOper(String oper) {
        String jsonSchemaPath = "src/test/resources/schemas/" + oper + ".json";
        return new String(Files.readAllBytes(Paths.get(jsonSchemaPath)));
    }
}
