package opmpackets.util;

import org.jspecify.annotations.NullMarked;

@FunctionalInterface
@NullMarked
public interface FloatUnaryOperator {

    float apply(float v);
}
