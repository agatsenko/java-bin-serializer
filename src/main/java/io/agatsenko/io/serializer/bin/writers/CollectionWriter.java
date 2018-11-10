/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;

import io.agatsenko.io.serializer.bin.RecordFieldsExtractor;
import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.RecordWriterResolver;
import io.agatsenko.util.Check;

public class CollectionWriter extends AbstractWriter<Collection> {
    private final RecordWriterResolver writerResolver;
    private final RecordFieldsExtractor fieldsExtractor;

    public CollectionWriter(RecordWriterResolver writerResolver, RecordFieldsExtractor fieldsExtractor) {
        Check.argNotNull(writerResolver, "writerResolver");
        Check.argNotNull(fieldsExtractor, "fieldsExtractor");

        this.writerResolver = writerResolver;
        this.fieldsExtractor = fieldsExtractor;
    }

    @Override
    public RecordType type() {
        return RecordType.ARRAY;
    }

    @Override
    protected void writeValue(Collection value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        writeLength(value.size(), out);
        for (var item : value) {
            writerResolver.resolve(item, fieldsExtractor).write(item, out);
        }
    }
}
