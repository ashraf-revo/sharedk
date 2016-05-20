package org.revo.Util

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import org.springframework.stereotype.Component

import java.text.SimpleDateFormat

/**
 * Created by revo on 4/28/16.
 */
@Component
class JsonDateSerializer extends JsonSerializer<Date> {
    private final SimpleDateFormat format=new SimpleDateFormat("hh:mm:ss dd-MM-yyyy")

    @Override
    void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeString(format.format(date))
    }
}
