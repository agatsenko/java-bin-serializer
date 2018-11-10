/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.util.Check;

public class StringWriter extends AbstractWriter<CharSequence> {
    private final Charset charset;

    public StringWriter(Charset charset) {
        Check.argNotNull(charset, "charset");
        this.charset = charset;
    }

    @Override
    public RecordType type() {
        return RecordType.STRING;
    }

    @Override
    protected void writeValue(CharSequence value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        final var bytes = value.toString().getBytes(charset);
        writeLength(bytes.length, out);
        out.write(bytes);
    }
}
