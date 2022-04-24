package com.xiaomi.xiaoai.servicefunc.util;

import com.google.gson.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GSONConfiguration {
    private static final JsonSerializer<LocalDateTime> JSON_SERIALIZER = (localDateTime, type, jsonSerializationContext) -> {
        return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    };
    private static final JsonSerializer<LocalDate> JSON_SERIALIZER_DATE = (localDate, type, jsonSerializationContext) -> {
        return new JsonPrimitive(localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    };
    private static final JsonDeserializer<LocalDateTime> JSON_DESERIALIZER_DATETIME = (jsonElement, type, jsonDeserializationContext) -> {
        return LocalDateTime.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    };
    private static final JsonDeserializer<LocalDate> JSON_DESERIALIZER_DATE = (jsonElement, type, jsonDeserializationContext) -> {
        return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    };

    public GSONConfiguration() {
    }

    public static Gson getGson() {
        return (new GsonBuilder()).registerTypeAdapterFactory(new GsonTypeAdapterFactory()).registerTypeAdapter(LocalDateTime.class, JSON_SERIALIZER).registerTypeAdapter(LocalDate.class, JSON_SERIALIZER_DATE).registerTypeAdapter(LocalDateTime.class, JSON_DESERIALIZER_DATETIME).registerTypeAdapter(LocalDate.class, JSON_DESERIALIZER_DATE).create();
    }
}
