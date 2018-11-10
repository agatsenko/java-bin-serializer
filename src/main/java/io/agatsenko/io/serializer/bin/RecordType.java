/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

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

    public byte byteValue() {
        return byteValue;
    }
}
