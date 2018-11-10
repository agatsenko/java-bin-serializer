/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.json.Reader;
import io.agatsenko.io.serializer.bin.converter.json.ReaderResolver;
import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.io.serializer.bin.converter.json.records.ObjectRecord;
import io.agatsenko.util.Check;
import io.agatsenko.util.Pair;

public class ObjectReader implements Reader {
    private final ReaderResolver readerResolver;

    public ObjectReader(ReaderResolver readerResolver) {
        this.readerResolver = readerResolver;
    }

    @Override
    public Record readValue(RecordType recordType, InputStream in) throws IOException {
        Check.arg(recordType == RecordType.OBJECT, () -> "recordType is invalid");

        var fields = new LinkedHashMap<String, Record>();
        var len = Readers.readLength(in);
        for (var i = 0L; i < len; ++i) {
            var pair = readField(in);
            fields.put(pair.first, pair.second);
        }
        return new ObjectRecord(fields);
    }

    private String readFieldName(InputStream in) throws IOException {
        var type = Readers.readRecordType(in);
        Check.state(type == RecordType.STRING, () -> "invalid record type");

        StringBuilder sb = new StringBuilder();
        readerResolver.resolve(type).readValue(type, in).writeJson(sb);
        return sb.toString();
    }

    private Record readFieldValue(InputStream in) throws IOException {
        var recordType = Readers.readRecordType(in);
        return readerResolver.resolve(recordType).readValue(recordType, in);
    }

    private Pair<String, Record> readField(InputStream in) throws IOException {
        return Pair.of(readFieldName(in), readFieldValue(in));
    }
}
