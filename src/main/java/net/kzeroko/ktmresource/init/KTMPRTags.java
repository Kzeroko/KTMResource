/*
 * Copyright 2022 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
