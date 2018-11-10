/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import java.util.Map;

import io.agatsenko.io.serializer.bin.converter.json.Record;

public class ObjectRecord implements Record {
    private final Map<String, Record> fields;

    public ObjectRecord(Map<String, Record> fields) {
        this.fields = fields;
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append('{');
        if (!fields.isEmpty()) {
            for (var entry : fields.entrySet()) {
                sb.append(entry.getKey()).append(':');
                entry.getValue().writeJson(sb);
                sb.append(',');
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        sb.append('}');
    }
}
