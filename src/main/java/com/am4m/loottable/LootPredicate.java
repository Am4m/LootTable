package com.am4m.loottable;

import com.am4m.loottable.predicate.BlockPredicate;
import com.am4m.loottable.predicate.ItemPredicate;
import com.am4m.loottable.utils.NumberProvider;
import com.squareup.moshi.Json;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public sealed interface LootPredicate {

    record AllOf(@NotNull List<LootPredicate> terms) implements LootPredicate {}

    record AnyOf(
        @Json(name = "terms")
        @NotNull List<LootPredicate> terms
    ) implements LootPredicate {}

    record Inverted(@NotNull LootPredicate term) implements LootPredicate {}

    record SurvivesExplosion() implements LootPredicate {}

    record RandomChance(@NotNull NumberProvider chance) implements LootPredicate {}

    record Reference(@NotNull String name) implements LootPredicate {}

    record BlockStateProperty(@NotNull String block, @Nullable BlockPredicate predicate) implements LootPredicate {}

    record TableBonus(

        @Json(name = "enchantment")
        String enchantment,
        @Json(name = "chances")
        List<Double> chances

    ) implements LootPredicate {}

    record MatchTool(

        @Json(name = "predicate") ItemPredicate predicate

    ) implements LootPredicate {
    }
}
