/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import io.agatsenko.io.serializer.bin.RecordFieldsExtractor;
import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.RecordWriterResolver;
import io.agatsenko.util.Check;

public class MapWriter extends AbstractWriter<Map> {
    private final RecordWriterResolver writerResolver;
    private final RecordFieldsExtractor fieldsExtractor;

    public MapWriter(RecordWriterResolver writerResolver, RecordFieldsExtractor fieldsExtractor) {
        Check.argNotNull(writerResolver, "writerResolver");
        Check.argNotNull(fieldsExtractor, "fieldsExtractor");

        this.writerResolver = writerResolver;
        this.fieldsExtractor = fieldsExtractor;
    }

    @Override
    public RecordType type() {
        return RecordType.MAP;
    }

    @Override
    protected void writeValue(Map value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        writeLength(value.size(), out);
        for (var item : value.entrySet()) {
            Map.Entry entry = (Map.Entry) item;
            writerResolver.resolve(entry.getKey(), fieldsExtractor).write(entry.getKey(), out);
            writerResolver.resolve(entry.getValue(), fieldsExtractor).write(entry.getValue(), out);
        }
    }
}
