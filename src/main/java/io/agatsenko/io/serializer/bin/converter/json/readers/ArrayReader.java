/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-11
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.io.serializer.bin.converter.json.Reader;
import io.agatsenko.io.serializer.bin.converter.json.ReaderResolver;
import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.io.serializer.bin.converter.json.records.ArrayRecord;
import io.agatsenko.util.Check;

public class ArrayReader implements Reader {
    private final ReaderResolver readerResolver;

    public ArrayReader(ReaderResolver readerResolver) {
        this.readerResolver = readerResolver;
    }

    @Override
    public Record readValue(RecordType recordType, InputStream in) throws IOException {
        Check.arg(recordType == RecordType.ARRAY, () -> "recordType is invalid");
        var items = new LinkedList<Record>();
        var len = Readers.readLength(in);
        for (var i = 0; i < len; ++i) {
            var itemType = Readers.readRecordType(in);
            items.add(readerResolver.resolve(itemType).readValue(itemType, in));
        }
        return new ArrayRecord(items);
    }
}
