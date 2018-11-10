/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.json.Reader;
import io.agatsenko.io.serializer.bin.converter.json.ReaderResolver;
import io.agatsenko.util.Check;

public class DefaultReaderResolver implements ReaderResolver {
    public static Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final Map<RecordType, Reader> readers;

    public DefaultReaderResolver(Charset charset) {
        var numReader = new NumberReader();
        var boolReader = new BoolReader();
        readers = ImmutableMap.
                <RecordType, Reader>builder().
                put(RecordType.NULL, new NullReader()).
                put(RecordType.STRING, new StringReader(charset)).
                put(RecordType.OBJECT, new ObjectReader(this)).
                put(RecordType.BYTE, numReader).
                put(RecordType.SHORT, numReader).
                put(RecordType.INT, numReader).
                put(RecordType.LONG, numReader).
                put(RecordType.FLOAT, numReader).
                put(RecordType.DOUBLE, numReader).
                put(RecordType.TRUE, boolReader).
                put(RecordType.FALSE, boolReader).
                put(RecordType.ARRAY, new ArrayReader(this)).
                put(RecordType.MAP, new PairsReader(this)).
                build();
    }

    public DefaultReaderResolver() {
        this(DEFAULT_CHARSET);
    }

    @Override
    public Reader resolve(RecordType recordType) {
        var reader = readers.get(recordType);
        Check.state(reader != null, () -> "unable to resolve reader for " + recordType + " record type");
        return reader;
    }
}
