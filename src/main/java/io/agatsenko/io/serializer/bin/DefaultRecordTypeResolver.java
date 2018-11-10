/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

public class DefaultRecordTypeResolver implements RecordTypeResolver {
    @Override
    public RecordType resolve(Object value) {
        if (value == null) {
            return RecordType.NULL;
        }
        else if (value instanceof CharSequence) {
            return RecordType.STRING;
        }
        else if (value.getClass() == Long.class) {
            return RecordType.LONG;
        }
        else if (value.getClass() == Double.class) {
            return RecordType.DOUBLE;
        }
        else {
            return RecordType.OBJECT;
        }
    }
}
