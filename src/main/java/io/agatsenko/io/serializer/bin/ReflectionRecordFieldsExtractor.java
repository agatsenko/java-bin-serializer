/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import io.agatsenko.util.Check;

public class ReflectionRecordFieldsExtractor implements RecordFieldsExtractor {
    private final RecordWriterResolver writerResolver;

    public ReflectionRecordFieldsExtractor(RecordWriterResolver writerResolver) {
        Check.argNotNull(writerResolver, "writerResolver");

        this.writerResolver = writerResolver;
    }

    @Override
    public List<RecordField> extract(Object obj) {
        Check.argNotNull(obj, "obj");

        final var recordFields = new ArrayList<RecordField>();
        try {
            var clazz = obj.getClass();
            while (clazz != Object.class) {
                for (var f : clazz.getDeclaredFields()) {
                    if (Modifier.isStatic(f.getModifiers())) {
                        continue;
                    }
                    f.setAccessible(true);
                    final var value = f.get(obj);
                    recordFields.add(new RecordField(f.getName(), value, writerResolver.resolve(value, this)));
                }
                clazz = clazz.getSuperclass();
            }
        }
        catch (IllegalAccessException ex) {
            throw new IllegalStateException("unable to extract record fields from " + obj, ex);
        }
        return recordFields;
    }
}
