package com.am4m.loottable;

import com.am4m.loottable.utils.NumberProvider;
import com.squareup.moshi.Json;

import java.util.List;
import java.util.Random;

public sealed interface LootFunction {

    record ApplyBonus(

        @Json(name = "conditions")
        List<LootPredicate> predicates,
        @Json(name = "enchantment")
        String enchantment,
        Formula formula

    ) implements LootFunction {}

    record ExplosionDecay(@Json(name = "conditions") List<LootPredicate> predicates) implements LootFunction {}

    record SetCount(

        @Json(name = "conditions")
        List<LootPredicate> predicates,
        @Json(name = "count")
        NumberProvider number,
        boolean add

    ) implements LootFunction {}

    sealed interface Formula {

        int calculate(Random random, int count, int level);

        record UniformBonusCount(int bonusMultiplier) implements Formula {

            @Override
            public int calculate(Random random, int count, int level) {
                return count + random.nextInt(bonusMultiplier * level + 1);
            }
        }

        record OreDrops() implements Formula {

            @Override
            public int calculate(Random random, int count, int level) {
                if (level <= 0) return count;

                return count * Math.min(1, random.nextInt(level + 2));
            }
        }

        record BinomialWithBonusCount(float probability, int extra) implements Formula {

            @Override
            public int calculate(Random random, int count, int level) {
                for (int i = 0; i < extra + level; i++) {
                    if (random.nextFloat() < probability) {
                        count++;
                    }
                }
                return count;
            }
        }

    }
}
