/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

public interface RecordWriterResolver {
    <T> RecordWriter<T> resolve(Object value, RecordFieldsExtractor fieldsExtractor);
}
