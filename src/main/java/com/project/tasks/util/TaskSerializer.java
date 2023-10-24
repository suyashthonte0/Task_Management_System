package com.project.tasks.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.project.tasks.entity.Task;

import java.io.IOException;

public class TaskSerializer extends JsonSerializer<Task> {
    @Override
    public void serialize(Task task, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", task.getId());
        jsonGenerator.writeStringField("title", task.getTitle());
        jsonGenerator.writeStringField("description", task.getDescription());
        jsonGenerator.writeBooleanField("completed", task.getCompleted());
        jsonGenerator.writeObjectField("priority", task.getPriority());
        jsonGenerator.writeEndObject();
    }
}
