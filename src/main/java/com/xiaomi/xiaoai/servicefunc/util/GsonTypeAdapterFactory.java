package com.xiaomi.xiaoai.servicefunc.util;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class GsonTypeAdapterFactory implements TypeAdapterFactory {
    public GsonTypeAdapterFactory() {
    }

    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        final TypeAdapter<T> adapter = gson.getDelegateAdapter(this, type);
        return new TypeAdapter<T>() {
            @Override
            public void write(JsonWriter out, T value) throws IOException {
                adapter.write(out, value);
            }

            @Override
            public T read(JsonReader in) throws IOException {
                try {
                    return adapter.read(in);
                } catch (Throwable var3) {
                    return this.fallback(in);
                }
            }

            private <E> E fallback(JsonReader in) throws IOException {
                JsonToken jsonToken = in.peek();
                switch(jsonToken) {
                    case BEGIN_ARRAY:
                    case BEGIN_OBJECT:
                    case NAME:
                    case STRING:
                    case NUMBER:
                    case BOOLEAN:
                    case NULL:
                        in.skipValue();
                        break;
                    case END_ARRAY:
                        in.endArray();
                        break;
                    case END_OBJECT:
                        in.endObject();
                    case END_DOCUMENT:
                        break;
                    default:
                        throw new AssertionError(jsonToken);
                }

                return null;
            }
        };
    }
}