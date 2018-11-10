/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.util.Check;

public class FalseWriter extends AbstractWriter<Boolean> {
    @Override
    public RecordType type() {
        return RecordType.FALSE;
    }

    @Override
    public void write(Boolean value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        Check.arg(!value, () -> "value is not false");
        super.write(false, out);
    }

    @Override
    protected void writeValue(Boolean value, OutputStream out) throws IOException {
        // do nothing
    }
}
