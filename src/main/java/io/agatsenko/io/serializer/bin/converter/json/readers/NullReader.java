/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.io.InputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.json.Reader;
import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.io.serializer.bin.converter.json.records.NullRecord;
import io.agatsenko.util.Check;

public class NullReader implements Reader {
    @Override
    public Record readValue(RecordType recordType, InputStream in) {
        Check.arg(recordType == RecordType.NULL, () -> "invalid record type");
        return NullRecord.instance;
    }
}
