/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.util.Check;

public class StringRecord implements Record {
    private final String value;

    public StringRecord(String value) {
        Check.argNotNull(value, "value");
        this.value = value;
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append('"').append(value).append('"');
    }
}
