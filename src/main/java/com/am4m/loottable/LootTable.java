package com.am4m.loottable;

import com.squareup.moshi.Json;

import java.util.List;

public record LootTable(
    @Json(name = "type")
    String type,

    @Json(name = "pools")
    List<LootPool> pools,

    @Json(name = "random_sequence")
    String randomSequence
) {}
