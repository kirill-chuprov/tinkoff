package com.tinkoff.task.repository.data.remote.entity;

import com.bluelinelabs.logansquare.JsonMapper;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import java.io.IOException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;

@SuppressWarnings("unsafe,unchecked")
public final class EntityR$$JsonObjectMapper extends JsonMapper<EntityR> {
  @Override
  public EntityR parse(JsonParser jsonParser) throws IOException {
    EntityR instance = new EntityR();
    if (jsonParser.getCurrentToken() == null) {
      jsonParser.nextToken();
    }
    if (jsonParser.getCurrentToken() != JsonToken.START_OBJECT) {
      jsonParser.skipChildren();
      return null;
    }
    while (jsonParser.nextToken() != JsonToken.END_OBJECT) {
      String fieldName = jsonParser.getCurrentName();
      jsonParser.nextToken();
      parseField(instance, fieldName, jsonParser);
      jsonParser.skipChildren();
    }
    return instance;
  }

  @Override
  public void parseField(EntityR instance, String fieldName, JsonParser jsonParser) throws
      IOException {
    if ("data".equals(fieldName)) {
      instance.setData(jsonParser.getValueAsString(null));
    } else if ("partnerId".equals(fieldName)) {
      instance.setId(jsonParser.getValueAsString(null));
    }
  }

  @Override
  public void serialize(EntityR object, JsonGenerator jsonGenerator, boolean writeStartAndEnd)
      throws IOException {
    if (writeStartAndEnd) {
      jsonGenerator.writeStartObject();
    }
    if (object.getData() != null) {
      jsonGenerator.writeStringField("data", object.getData());
    }
    if (object.getId() != null) {
      jsonGenerator.writeStringField("partnerId", object.getId());
    }
    if (writeStartAndEnd) {
      jsonGenerator.writeEndObject();
    }
  }
}
