package com.am4m.loottable;

import com.am4m.loottable.predicate.ItemPredicate;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory;

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
            .add(ItemPredicate.createJsonAdapterFactory())
            .build();

        JsonAdapter<LootTable> adapter = moshi.adapter(LootTable.class);
    }

}