package net.kzeroko.ktmresource.items;

import net.kzeroko.ktmresource.init.KTMPRBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class KtmItemTab {

    public static final CreativeModeTab KTMWEAPONS = new CreativeModeTab("ktmweaponsTab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(KtmItems.ANNEAL_BLADE.get());
        }
    };

    public static final CreativeModeTab KTMRESOURCES = new CreativeModeTab("ktmresourceTab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(KtmItems.LOST_FRAGMENT.get());
        }
    };

    public static final CreativeModeTab KTMINGOTS = new CreativeModeTab("ktmingotsTab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(KtmItems.ADAMANTITE_INGOT.get());
        }
    };

    public static final CreativeModeTab KTMBUCKETS = new CreativeModeTab("ktmbucketsTab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(KtmItems.BLAZINGSOUL_BUCKET.get());
        }
    };

    public static final CreativeModeTab KTMBLOCKS = new CreativeModeTab("ktmblocksTab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(KTMPRBlocks.ALLOY_FURNACE.get());
        }
    };

    public static final CreativeModeTab KTMOICONS = new CreativeModeTab("ktmoiconsTab") {
        @Override
        public ItemStack makeIcon() { return new ItemStack(KtmItems.OICON_ELF.get());
        }
    };
}
