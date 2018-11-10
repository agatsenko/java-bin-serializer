/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import io.agatsenko.io.serializer.bin.converter.json.Record;

public final class NullRecord implements Record {
    public static final NullRecord instance = new NullRecord();

    private NullRecord() {
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append("null");
    }
}
