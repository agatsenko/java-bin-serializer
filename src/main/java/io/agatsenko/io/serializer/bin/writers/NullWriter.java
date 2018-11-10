/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.util.Check;

public class NullWriter extends AbstractWriter<Object> {
    @Override
    public RecordType type() {
        return RecordType.NULL;
    }

    @Override
    public void write(Object value, OutputStream out) throws IOException {
        Check.arg(value == null, () -> "value is not null");
        super.write(null, out);
    }

    @Override
    protected void writeValue(Object value, OutputStream out) throws IOException {
        // do nothing
    }
}
