/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.util.Check;

public class ShortWriter extends AbstractWriter<Short> {
    @Override
    public RecordType type() {
        return RecordType.SHORT;
    }

    @Override
    protected void writeValue(Short value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        writeShort(value, out);
    }
}
