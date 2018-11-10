/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import io.agatsenko.io.serializer.bin.converter.json.Record;

public class BoolRecord implements Record {
    private final boolean value;

    public BoolRecord(boolean value) {
        this.value = value;
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append(value);
    }
}
