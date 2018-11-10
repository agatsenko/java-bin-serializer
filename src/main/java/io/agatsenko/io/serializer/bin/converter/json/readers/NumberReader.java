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
import io.agatsenko.io.serializer.bin.converter.json.records.NumberRecord;

public class NumberReader implements Reader {
    @Override
    public Record readValue(RecordType recordType, InputStream in) throws IOException {
        switch (recordType) {
            case BYTE:
                return new NumberRecord(Readers.readByte(in));
            case SHORT:
                return new NumberRecord(Readers.readShort(in));
            case INT:
                return new NumberRecord(Readers.readInt(in));
            case LONG:
                return new NumberRecord(Readers.readLong(in));
            case FLOAT:
                return new NumberRecord(Readers.readFloat(in));
            case DOUBLE:
                return new NumberRecord(Readers.readDouble(in));
            default:
                throw new IllegalStateException(recordType + " record type is unsupported");
        }
    }
}
