/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json;

import java.io.IOException;
import java.io.InputStream;

import io.agatsenko.io.serializer.bin.RecordType;

public interface Reader {
    Record readValue(RecordType recordType, InputStream in) throws IOException;
}
