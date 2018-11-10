/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import java.util.Collection;

import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.util.Pair;

public class PairsRecord implements Record {
    private final Collection<Pair<Record, Record>> pairs;

    public PairsRecord(Collection<Pair<Record, Record>> pairs) {
        this.pairs = pairs;
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append('[');
        if (!pairs.isEmpty()) {
            pairs.forEach(p -> {
                sb.append('[');
                p.first.writeJson(sb);
                sb.append(',');
                p.second.writeJson(sb);
                sb.append("],");
            });
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append(']');
    }
}
