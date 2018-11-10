/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.io.IOException;
import java.io.InputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.json.Reader;
import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.io.serializer.bin.converter.json.records.BoolRecord;
import io.agatsenko.util.Check;

public class BoolReader implements Reader {
    @Override
    public Record readValue(RecordType recordType, InputStream in) throws IOException {
        Check.state(
                recordType == RecordType.TRUE || recordType == RecordType.FALSE,
                () -> "invalid record type"
        );
        switch (recordType) {
            case TRUE:
                return new BoolRecord(true);
            case FALSE:
                return new BoolRecord(false);
            default:
                throw new IllegalArgumentException("invalid record type");
        }
    }
}
