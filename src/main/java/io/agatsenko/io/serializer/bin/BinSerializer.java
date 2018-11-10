/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

import java.io.IOException;
import java.io.OutputStream;

import io.agatsenko.io.serializer.Serializer;
import io.agatsenko.util.Check;

public class BinSerializer implements Serializer {
    private final RecordWriterResolver recordWriterResolver;
    private final RecordFieldsExtractor recordFieldsExtractor;

    public BinSerializer(RecordWriterResolver recordWriterResolver, RecordFieldsExtractor recordFieldsExtractor) {
        Check.argNotNull(recordWriterResolver, "recordWriterResolver");
        Check.argNotNull(recordFieldsExtractor, "recordFieldsExtractor");

        this.recordWriterResolver = recordWriterResolver;
        this.recordFieldsExtractor = recordFieldsExtractor;
    }

    @Override
    public void serialize(Object value, OutputStream out) throws IOException {
        Check.argNotNull(out, "out");

        recordWriterResolver.resolve(value, recordFieldsExtractor).write(value, out);
    }
}
