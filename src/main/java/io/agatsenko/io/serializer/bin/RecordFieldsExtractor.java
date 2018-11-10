/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer.bin;

import java.util.List;

public interface RecordFieldsExtractor {
    List<RecordField> extract(Object o);
}
