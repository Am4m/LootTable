package com.am4m.loottable.utils;

public sealed interface NumberProvider {

    record Constant(Double value) implements  NumberProvider {}

    record Uniform(Double min, Double max) implements  NumberProvider {}

    record Binomial(NumberProvider n, NumberProvider p) implements NumberProvider {}
}
