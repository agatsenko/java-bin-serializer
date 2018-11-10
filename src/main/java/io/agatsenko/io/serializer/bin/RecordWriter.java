/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

import java.io.IOException;
import java.io.OutputStream;

public interface RecordWriter<T> {
    RecordType type();

    void write(T value, OutputStream out) throws IOException;
}
