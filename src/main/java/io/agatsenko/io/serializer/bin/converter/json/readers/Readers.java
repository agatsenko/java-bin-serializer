/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json.readers;

import java.io.IOException;
import java.io.InputStream;

import io.agatsenko.io.serializer.bin.RecordType;
import io.agatsenko.util.Check;

public interface Readers {
    static RecordType readRecordType(InputStream in) throws IOException {
        return RecordType.fromByteValue((byte) in.read());
    }

    static byte readByte(InputStream in) throws IOException {
        var byteVal = in.read();
        Check.state(byteVal > -1, () -> "unable to read byte because stream has reached end");
        return (byte) byteVal;
    }

    static short readShort(InputStream in) throws IOException {
        byte[] bytes = new byte[2];
        var readBytes = in.read(bytes);
        Check.state(bytes.length == readBytes, () -> "unable to read short because stream has reached end");

        int value = 0;
        for (var i = 0; i < 2; ++i) {
            var byteValue = Long.rotateLeft(0xFF & bytes[i], i * 8);
            value |= byteValue;
        }
        return (short) value;
    }

    static int readInt(InputStream in) throws IOException {
        byte[] bytes = new byte[4];
        var readBytes = in.read(bytes);
        Check.state(bytes.length == readBytes, () -> "unable to read int because stream has reached end");

        var value = 0;
        for (var i = 0; i < 4; ++i) {
            var byteValue = Long.rotateLeft(0xFF & bytes[i], i * 8);
            value |= byteValue;
        }
        return value;
    }

    static long readLong(InputStream in) throws IOException {
        byte[] bytes = new byte[8];
        var readBytes = in.read(bytes);
        Check.state(bytes.length == readBytes, () -> "unable to read long because stream has reached end");

        var value = 0L;
        for (var i = 0; i < 8; ++i) {
            var byteValue = Long.rotateLeft(0xFFL & bytes[i], i * 8);
            value |= byteValue;
        }
        return value;
    }

    static float readFloat(InputStream in) throws IOException {
        return Float.intBitsToFloat(readInt(in));
    }

    static double readDouble(InputStream in) throws IOException {
        return Double.longBitsToDouble(readLong(in));
    }

    static long readLength(InputStream in) throws IOException {
        return readLong(in);
    }
}
