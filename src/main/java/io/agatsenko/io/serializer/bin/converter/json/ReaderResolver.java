/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.converter.json;

import io.agatsenko.io.serializer.bin.RecordType;

public interface ReaderResolver {
    Reader resolve(RecordType recordType);
}
