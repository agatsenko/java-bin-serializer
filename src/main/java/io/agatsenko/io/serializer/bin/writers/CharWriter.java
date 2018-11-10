/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.RecordWriter;
import io.agatsenko.util.Check;

public class CharWriter implements RecordWriter<Character> {
    private final RecordWriter<CharSequence> stringWriter;

    public CharWriter(RecordWriter<CharSequence> stringWriter) {
        Check.argNotNull(stringWriter, "stringWriter");
        this.stringWriter = stringWriter;
    }

    @Override
    public RecordType type() {
        return RecordType.STRING;
    }

    @Override
    public void write(Character value, OutputStream out) throws IOException {
        Check.argNotNull(value, "value");
        stringWriter.write(value.toString(), out);
    }
}
