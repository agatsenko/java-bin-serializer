/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.json.Reader;
import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.io.serializer.bin.converter.json.records.StringRecord;
import io.agatsenko.util.Check;

public class StringReader implements Reader {
    private final Charset charset;

    public StringReader(Charset charset) {
        Check.argNotNull(charset, "charset");

        this.charset = charset;
    }

    @Override
    public Record readValue(RecordType recordType, InputStream in) throws IOException {
        Check.arg(recordType == RecordType.STRING, () -> "recordType is invalid");

        long length = Readers.readLength(in);
        Check.state(length <= Integer.MAX_VALUE, () -> "string length greater than int max");

        byte[] bytes = new byte[(int) length];
        var readLen = in.read(bytes);
        Check.state(readLen == bytes.length, () -> "string length less than declared length");

        return new StringRecord(new String(bytes, charset));
    }
}
