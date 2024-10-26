package com.am4m.loottable;

import com.am4m.loottable.predicate.ItemPredicate;
import com.am4m.loottable.utils.NumberProvider;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        Moshi moshi = new Moshi.Builder()
            .add(PolymorphicJsonAdapterFactory.of(LootEntry.class, "type")
                .withSubtype(LootEntry.Item.class, "minecraft:item")
                .withSubtype(LootEntry.Alternatives.class, "minecraft:alternatives"))
            .add(PolymorphicJsonAdapterFactory.of(LootPredicate.class, "condition")
                .withSubtype(LootPredicate.MatchTool.class, "minecraft:match_tool")
                .withSubtype(LootPredicate.AnyOf.class, "minecraft:any_of")
                .withSubtype(LootPredicate.SurvivesExplosion.class, "minecraft:survives_explosion")
                .withSubtype(LootPredicate.TableBonus.class, "minecraft:table_bonus")
                .withSubtype(LootPredicate.Inverted.class, "minecraft:inverted"))
            .add(PolymorphicJsonAdapterFactory.of(LootFunction.class, "function")
                .withSubtype(LootFunction.ApplyBonus.class, "minecraft:apply_bonus")
                .withSubtype(LootFunction.ExplosionDecay.class, "minecraft:explosion_decay")
                .withSubtype(LootFunction.SetCount.class, "minecraft:set_count"))
            .add(PolymorphicJsonAdapterFactory.of(LootFunction.Formula.class, "formula")
                .withSubtype(LootFunction.Formula.UniformBonusCount.class, "minecraft:uniform_bonus_count")
                .withSubtype(LootFunction.Formula.BinomialWithBonusCount.class, "minecraft:binomial_with_bonus_count")
                .withSubtype(LootFunction.Formula.OreDrops.class, "minecraft:ore_drops"))
            .add(PolymorphicJsonAdapterFactory.of(NumberProvider.class, "type")
                .withSubtype(NumberProvider.Constant.class, "minecraft:constant")
                .withSubtype(NumberProvider.Uniform.class, "minecraft:uniform")
                .withSubtype(NumberProvider.Binomial.class, "minecraft:binomial"))
            .add(ItemPredicate.createJsonAdapterFactory())
            .build();

        JsonAdapter<LootTable> adapter = moshi.adapter(LootTable.class);
    }
}