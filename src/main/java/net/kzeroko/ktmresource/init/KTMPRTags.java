package net.kzeroko.ktmresource.init;

import net.kzeroko.ktmresource.KTMResource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class KTMPRTags {

    public static class Items {

        public static final TagKey<Item> FORGING_MATERIALS = tag("forging_materials");
        public static final TagKey<Item> FORGING_FUELS = tag("forging_fuels");
        public static final TagKey<Item> FORGING_ACTIVATORS = tag("forging_activators");
        public static final TagKey<Item> FORGING_OUTPUTS = tag("forging_outputs");


        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(KTMResource.MOD_ID, name));
        }
    }

}
