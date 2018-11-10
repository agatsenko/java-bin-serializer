/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

import io.agatsenko.util.Check;

public enum RecordType {
    NULL(0),
    TRUE(1),
    FALSE(2),
    STRING(3),
    BYTE(4),
    SHORT(5),
    INT(6),
    LONG(7),
    FLOAT(8),
    DOUBLE(9),
    ARRAY(10),
    MAP(11),
    OBJECT(12),
    ;

    private final byte byteValue;

    RecordType(byte byteValue) {
        this.byteValue = byteValue;
    }

    RecordType(int byteValue) {
        this.byteValue = (byte) byteValue;
    }

    public static RecordType fromByteValue(byte byteValue) {
        RecordType foundType = null;
        for (var type : values()) {
            if (type.byteValue == byteValue) {
                foundType = type;
                break;
            }
        }
        Check.state(foundType != null, () -> byteValue + " byte value is unsupported");
        return foundType;
    }

    public byte byteValue() {
        return byteValue;
    }
}
