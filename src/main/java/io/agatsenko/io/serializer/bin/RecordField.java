/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

import io.agatsenko.util.Check;

public class RecordField {
    public final String name;
    public final Object value;
    public final RecordWriter valueWriter;

    public RecordField(String name, Object value, RecordWriter valueWriter) {
        Check.argNotNullOrEmpty(name, "name");
        Check.argNotNull(valueWriter, "valueWriter");

        this.name = name;
        this.value = value;
        this.valueWriter = valueWriter;
    }
}
