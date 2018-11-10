/**
 * Author: Alexander Gatsenko (alexandr.gatsenko@gmail.com)
 * Created: 2018-11-10
 */
package io.agatsenko;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import io.agatsenko.io.serializer.Serializer;
import io.agatsenko.io.serializer.bin.BinSerializer;
import io.agatsenko.io.serializer.bin.ReflectionRecordFieldsExtractor;
import io.agatsenko.io.serializer.bin.writers.DefaultRecordWriterResolver;

class Obj {
    Boolean nullVal = null;
    String str = "test text";
    char ch = 'q';
    boolean trueVal = true;
    boolean falseVal = false;
    byte byteVal = (byte) 255;
    short shortVal = (short) 256;
    int intVal = 1848;
    long longVal = 184848181848L;
    float floatVal = 18.48f;
    double doubleVal= 18.48;
    List<String> strList = List.of("one", "two", "three");
    Map<Integer, String> map = Map.of(1, "one", 2, "two", 3, "three");
}

class RootObj extends Obj {
    Obj obj = new Obj();
    Iterable<Obj> objs = List.of(new Obj(), new Obj(), new Obj());
    Map<Obj, Integer> objMap = Map.of(
            new Obj(), 1,
            new Obj(), 2,
            new Obj(), 3
    );
}

public class Tester {
    public static void main(String[] args) {
        try {
            final var out = new ByteArrayOutputStream();

            final var serializer = createSerializer();
            serializer.serialize(new RootObj(), out);

            for (var b : out.toByteArray()) {
                System.out.printf("%02x", b);
            }
            System.out.println();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static Serializer createSerializer() {
        final var writerResolver = new DefaultRecordWriterResolver();
        return new BinSerializer(writerResolver, new ReflectionRecordFieldsExtractor(writerResolver));
    }
}
