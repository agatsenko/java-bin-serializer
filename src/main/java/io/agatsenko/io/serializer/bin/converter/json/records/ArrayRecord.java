/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import java.util.Collection;

import io.agatsenko.io.serializer.bin.converter.json.Record;

public class ArrayRecord implements Record {
    private final Collection<Record> items;

    public ArrayRecord(Collection<Record> items) {
        this.items = items;
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append('[');
        if (!items.isEmpty()) {
            items.forEach(i -> {
                i.writeJson(sb);
                sb.append(',');
            });
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append(']');
    }
}
