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
import io.agatsenko.io.serializer.bin.converter.json.records.PairsRecord;
import io.agatsenko.util.Check;
import io.agatsenko.util.Pair;

public class PairsReader implements Reader {
    private final ReaderResolver readerResolver;

    public PairsReader(ReaderResolver readerResolver) {
        this.readerResolver = readerResolver;
    }

    @Override
    public Record readValue(RecordType recordType, InputStream in) throws IOException {
        Check.arg(recordType == RecordType.MAP, () -> "recordType is invalid");
        var pairs = new LinkedList<Pair<Record, Record>>();
        var len = Readers.readLength(in);
        for (var i = 0; i < len; ++i) {
            pairs.add(Pair.of(readPairValue(in), readPairValue(in)));
        }
        return new PairsRecord(pairs);
    }

    private Record readPairValue(InputStream in) throws IOException {
        var type = Readers.readRecordType(in);
        return readerResolver.resolve(type).readValue(type, in);
    }
}
