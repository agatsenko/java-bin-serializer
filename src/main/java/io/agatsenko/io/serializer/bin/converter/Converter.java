/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import io.agatsenko.util.Check;

public interface Converter<R> {
    R convert(InputStream in) throws IOException;

    default R convert(byte[] bytes) throws IOException {
        Check.argNotNull(bytes, "bytes");
        try (var in = new ByteArrayInputStream(bytes)) {
            return convert(in);
        }
    }
}
