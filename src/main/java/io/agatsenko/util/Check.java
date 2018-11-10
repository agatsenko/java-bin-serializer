/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko.util;

import java.util.function.Function;
import java.util.function.Supplier;

public interface Check {
    static void arg(boolean condition, Supplier<CharSequence> errMsg) {
        check(condition, errMsg, IllegalArgumentException::new);
    }

    static <T> void argNotNull(T arg, CharSequence argName) {
        arg(arg != null, () -> argName + " is null");
    }

    static void argNotNullOrEmpty(CharSequence arg, CharSequence argName) {
        arg(arg != null && arg.length() > 0, () -> argName + " is null or empty");
    }

    static void state(boolean condition, Supplier<CharSequence> errMsg) {
        check(condition, errMsg, IllegalStateException::new);
    }

    private static <E extends RuntimeException> void check(
            boolean condition, Supplier<CharSequence> errMsg, Function<String, E> ex) {
        if (!condition) {
            throw ex.apply(errMsg.get().toString());
        }
    }
}
