package com.am4m.loottable;

import com.squareup.moshi.Json;

import java.util.List;

public sealed interface LootEntry {

    record Item(
        @Json(name = "conditions") List<LootPredicate> predicates,
        @Json(name = "functions") List<LootFunction> functions,
        @Json(name = "weight") Long weight,
        @Json(name = "quality") Long quality,
        @Json(name = "name") String name

    ) implements LootEntry { }

    record Alternatives(
        @Json(name = "children") List<LootEntry> children
    ) implements LootEntry {}

}
