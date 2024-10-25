package com.am4m.loottable;

import com.squareup.moshi.Json;

import java.util.List;

public record LootPool(
    @Json(name = "rolls")
    int rolls,

    @Json(name = "entries")
    List<LootEntry> entries,

    @Json(name = "bonus_rolls")
    int bonusRolls
) {}
