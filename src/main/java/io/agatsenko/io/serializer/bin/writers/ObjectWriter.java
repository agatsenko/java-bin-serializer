/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import io.agatsenko.io.serializer.bin.RecordField;
import io.agatsenko.io.serializer.bin.RecordFieldsExtractor;
import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.RecordWriter;
import io.agatsenko.util.Check;

public class ObjectWriter extends AbstractWriter<Object> {
    private final RecordFieldsExtractor fieldsExtractor;
    private final RecordWriter<CharSequence> nameWriter;

    ObjectWriter(RecordWriter<CharSequence> nameWriter, RecordFieldsExtractor fieldsExtractor) {
        Check.argNotNull(nameWriter, "nameWriter");
        Check.argNotNull(fieldsExtractor, "fieldsExtractor");

        this.nameWriter = nameWriter;
        this.fieldsExtractor = fieldsExtractor;
    }

    @Override
    public RecordType type() {
        return RecordType.OBJECT;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void writeValue(Object value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        List<RecordField> fields = fieldsExtractor.extract(value);
        writeLength(fields.size(), out);
        for (final var field : fields) {
            nameWriter.write(field.name, out);
            field.valueWriter.write(field.value, out);
        }
    }
}
