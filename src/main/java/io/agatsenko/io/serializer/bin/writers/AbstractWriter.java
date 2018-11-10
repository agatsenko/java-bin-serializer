/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.io.IOException;
import java.io.OutputStream;

import io.agatsenko.io.serializer.bin.RecordWriter;
import io.agatsenko.util.Check;

public abstract class AbstractWriter<T> implements RecordWriter<T> {
    @Override
    public void write(T value, OutputStream out) throws IOException {
        writeType(type().byteValue(), out);
        writeValue(value, out);
    }

    protected static void writeByte(byte value, OutputStream out) throws IOException {
        out.write(value);
    }

    protected static void writeShort(short value, OutputStream out) throws IOException {
        for (var i = 0; i < 2; ++i) {
            out.write((value >>> (i * 8)) & 0xFF);
        }
    }

    protected static void writeInt(int value, OutputStream out) throws IOException {
        for (var i = 0; i < 4; ++i) {
            out.write((value >>> (i * 8)) & 0xFF);
        }
    }

    protected static void writeLong(long value, OutputStream out) throws IOException {
        for (var i = 0; i < 8; ++i) {
            out.write((int) (value >>> (i * 8)) & 0xFF);
        }
    }

    protected static void writeType(byte type, OutputStream out) throws IOException {
        out.write((int) type);
    }

    protected static void writeLength(long length, OutputStream out) throws IOException {
        Check.arg(length > -1, () -> "length less than 0");
        writeLong(length, out);
    }

    protected abstract void writeValue(T value, OutputStream out) throws IOException;
}
