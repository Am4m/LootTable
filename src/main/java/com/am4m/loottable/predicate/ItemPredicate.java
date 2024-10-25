package com.am4m.loottable.predicate;

import com.am4m.loottable.utils.Enchantment;
import com.squareup.moshi.*;


import java.io.IOException;
import java.util.List;

public sealed interface ItemPredicate {

    record Enchantments(
        List<Enchantment> enchantments
    ) implements ItemPredicate {
    }

    record Items(
        Object namespace
    ) implements ItemPredicate {}

    static JsonAdapter.Factory createJsonAdapterFactory() {
        return (type, annotations, moshi) -> {
            if (type == ItemPredicate.class) {
                return new Adapter(moshi);
            }
            return null;
        };
    }


    class Adapter extends JsonAdapter<ItemPredicate> {


        private Adapter(Moshi moshi) {}

        @SuppressWarnings("unchecked")
        @Override
        public ItemPredicate fromJson(JsonReader reader) throws IOException {
            String name;
            ItemPredicate result = null;
            reader.beginObject();
            while (reader.hasNext()) {
                if (reader.peek() == JsonReader.Token.NAME) {
                    name = reader.nextName();
                    switch (name) {
                        case "items" -> result = new Items(name);
                        case "predicates" -> {
                            var peeked = reader.peekJson();
                            peeked.beginObject();
                            peeked.skipName();
                            //TODO: Safe parsing ?
                            if (peeked.readJsonValue() instanceof List<?> enchantments)
                                result = new Enchantments((List<Enchantment>) enchantments);
                        }
                    }
                    continue;
                }
                reader.skipValue();
            }
            reader.endObject();
            return result;
        }

        @Override
        public void toJson(JsonWriter writer, ItemPredicate value) throws IOException {}
    }
}
