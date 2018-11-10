/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.records;

import io.agatsenko.io.serializer.bin.converter.json.Record;
import io.agatsenko.util.Check;

public class NumberRecord implements Record {
    private final Number number;

    public NumberRecord(Number number) {
        Check.argNotNull(number, "number");
        this.number = number;
    }

    @Override
    public void writeJson(StringBuilder sb) {
        sb.append(number);
    }
}
