/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json;

import java.io.IOException;
import java.io.InputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.Converter;

public class JsonConverter implements Converter<String> {
    private final ReaderResolver readerResolver;

    public JsonConverter(ReaderResolver readerResolver) {
        this.readerResolver = readerResolver;
    }

    @Override
    public String convert(InputStream in) throws IOException {
        var sb = new StringBuilder();
        int b;
        while ((b = in.read()) > -1) {
            var recordType = RecordType.fromByteValue((byte) b);
            readerResolver.resolve(recordType).readValue(recordType, in).writeJson(sb);
        }
        return sb.toString();
    }
}
