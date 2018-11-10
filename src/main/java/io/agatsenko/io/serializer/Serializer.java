/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.io.serializer;

import java.io.IOException;
import java.io.OutputStream;

public interface Serializer {
    void serialize(Object value, OutputStream out) throws IOException;
}
