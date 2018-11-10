/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin.writers;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import io.agatsenko.io.serializer.bin.RecordFieldsExtractor;
import io.agatsenko.io.serializer.bin.RecordWriter;
import io.agatsenko.io.serializer.bin.RecordWriterResolver;
import io.agatsenko.util.Check;

public class DefaultRecordWriterResolver implements RecordWriterResolver {
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    private final RecordWriter<Object> nullWriter = new NullWriter();
    private final RecordWriter<Boolean> trueWriter = new TrueWriter();
    private final RecordWriter<Boolean> falseWriter = new FalseWriter();
    private final RecordWriter<CharSequence> stringWriter;

    private final Map<Class<?>, RecordWriter<?>> writersMap;

    public DefaultRecordWriterResolver(Charset charset) {
        Check.argNotNull(charset, "charset");

        this.stringWriter = new StringWriter(charset);
        this.writersMap = ImmutableMap.
                <Class<?>, RecordWriter<?>>builder().
                put(CharSequence.class, stringWriter).
                put(Character.class, new CharWriter(stringWriter)).
                put(Byte.class, new ByteWriter()).
                put(Short.class, new ShortWriter()).
                put(Integer.class, new IntWriter()).
                put(Long.class, new LongWriter()).
                put(Float.class, new FloatWriter()).
                put(Double.class, new DoubleWriter()).
                build();
    }

    public DefaultRecordWriterResolver() {
        this(DEFAULT_CHARSET);
    }

    @Override
    public RecordWriter<?> resolve(Object value, RecordFieldsExtractor fieldsExtractor) {
        if (value == null) {
            return nullWriter;
        }
        else if (value.getClass() == Boolean.class) {
            final var boolVal = (Boolean) value;
            return boolVal ? trueWriter : falseWriter;
        }
        else if (Collection.class.isAssignableFrom(value.getClass())) {
            return new CollectionWriter(this, fieldsExtractor);
        }
        else if (Map.class.isAssignableFrom(value.getClass())) {
            return new MapWriter(this, fieldsExtractor);
        }
        else {
            return resolve(value.getClass(), fieldsExtractor).orElse(new ObjectWriter(stringWriter, fieldsExtractor));
        }
    }

    private Optional<RecordWriter<?>> resolve(Class<?> valueClass, RecordFieldsExtractor fieldsExtractor) {
        Optional<RecordWriter<?>> opn = Optional.empty();
        for (var entry : writersMap.entrySet()) {
            if (entry.getKey().isAssignableFrom(valueClass)) {
                opn = Optional.of(entry.getValue());
                break;
            }
        }
        return opn;
    }
}
