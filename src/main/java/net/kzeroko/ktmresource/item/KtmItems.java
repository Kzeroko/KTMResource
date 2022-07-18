package net.kzeroko.ktmresource.item;

import net.kzeroko.ktmresource.KTMResource;
import net.kzeroko.ktmresource.fluid.KtmFluid;
import net.kzeroko.ktmresource.item.custom.*;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class KtmItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, KTMResource.MOD_ID);

    // Weapons - wooden

    public static final RegistryObject<Item> WOODEN_BAT = ITEMS.register("wooden_bat",
            () -> new SwordItem(KtmItemTier.BASIC, 2, -2.9f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS)));

    public static final RegistryObject<Item> WOODEN_KATANA = ITEMS.register("wooden_katana",
            () -> new SwordItem(KtmItemTier.BASIC, 0, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS)));

    // Weapons - advanced

    public static final RegistryObject<Item> POWERED_SCYTHE = ITEMS.register("powered_scythe",
            () -> new PoweredScythe(KtmItemTier.ADVANCED, 2, -2.5f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> AQUASLASHKAI_A = ITEMS.register("aquaslashkai_a",
            () -> new AquaslashkaiA(KtmItemTier.ADVANCED, 0, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> AQUASLASHKAI_B = ITEMS.register("aquaslashkai_b",
            () -> new AquaslashkaiB(KtmItemTier.ADVANCED, 0, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> RUSTY_HALBERD = ITEMS.register("rusty_halberd",
            () -> new RustyHalberd(KtmItemTier.ADVANCED, 4, -2.8f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> RUNESAKURA_TACHI = ITEMS.register("runesakura_tachi",
            () -> new RunesakuraTachi(KtmItemTier.ADVANCED, 2, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> LUNARCRYSTAL_GREATSWORD = ITEMS.register("lunarcrystal_greatsword",
            () -> new LunarcrystalGreatsword(KtmItemTier.ADVANCED, 5, -2.9f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> KALIOTH_SWORD = ITEMS.register("kalioth_sword",
            () -> new KaliothSword(KtmItemTier.ADVANCED, 1, -2.7f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> SOUL_HARVESTER = ITEMS.register("soul_harvester",
            () -> new SoulHarvester(KtmItemTier.ADVANCED, 3, -2.5f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> BLOODTHIRST_DAGGER = ITEMS.register("bloodthirst_dagger",
            () -> new BloodthirstDagger(KtmItemTier.ADVANCED, -2, -1.8f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    // Weapons - SAO

    public static final RegistryObject<Item> BLACK_SWORD = ITEMS.register("black_sword",
            () -> new BlackSword(KtmItemTier.ELITE, -1, -2.5f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> BLACKIRON_GREATSWORD = ITEMS.register("blackiron_greatsword",
            () -> new BlackironGreatsword(KtmItemTier.ELITE, 0, -3.0f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> BLUE_LONGSWORD = ITEMS.register("blue_longsword",
            () -> new BlueLongsword(KtmItemTier.ELITE, -5, -2.7f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> ANNEAL_BLADE = ITEMS.register("anneal_blade",
            () -> new AnnealBlade(KtmItemTier.ELITE, -7, -2.7f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> BLUEROSE_SWORD = ITEMS.register("bluerose_sword",
            () -> new BlueroseSword(KtmItemTier.LEGENDARY, -15, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.LEGENDARY)));

    // Weapons - another

    public static final RegistryObject<Item> UNMOVABLE_DAWNLIGHT = ITEMS.register("unmovable_dawnlight",
            () -> new UnmovableDawnlight(KtmItemTier.ANOTHER, 2, -2.3f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.TREASURY)));

    public static final RegistryObject<Item> MANIACAL_DUSK = ITEMS.register("maniacal_dusk",
            () -> new ManiacalDusk(KtmItemTier.ANOTHER, 6, -2.7f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.TREASURY)));

    // Weapons - legendary

    public static final RegistryObject<Item> LAEVATEINN = ITEMS.register("laevateinn",
            () -> new Laevateinn(KtmItemTier.LEGENDARY, -4, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> CURVEDSWORD_OF_AFTERLIFE = ITEMS.register("curvedsword_of_afterlife",
            () -> new CurvedBladeOfAfterlife(KtmItemTier.LEGENDARY, -8, -2.8f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.VENOMOUS)));

    public static final RegistryObject<Item> AMANOMURAKUMO = ITEMS.register("ama_no_murakumo",
            () -> new AmaNoMurakumo(KtmItemTier.LEGENDARY, -5, -2.3f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.MONSTROUS)));

    public static final RegistryObject<Item> DAINSLEIF = ITEMS.register("dainsleif",
            () -> new Dainsleif(KtmItemTier.LEGENDARY, -1, -2.4f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.OUTER)));

    public static final RegistryObject<Item> MISTILTEINN = ITEMS.register("mistilteinn",
            () -> new Mistilteinn(KtmItemTier.LEGENDARY, 4, -2.6f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.LEGENDARY)));

    public static final RegistryObject<Item> EXCALIBUR_KAI = ITEMS.register("excalibur_kai",
            () -> new ExcaliburKai(KtmItemTier.LEGENDARY, -11, -2.5f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.LEGENDARY)));

    // Weapons - mythic

    public static final RegistryObject<Item> THEBLADEOFGOLDEN = ITEMS.register("thebladeofgolden",
            () -> new ThebladeofGolden(KtmItemTier.MYTHICAL, -6, -2.8f,
                    new Item.Properties().tab(KtmItemTab.KTMWEAPONS).fireResistant().rarity(KtmItemRarity.LEGENDARY)));

    // Misc Resource Items

    public static final RegistryObject<Item> CORE = ITEMS.register("core",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> CAST_IRON_DUST = ITEMS.register("cast_iron_dust",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> LIMESTONE_DUST = ITEMS.register("limestone_dust",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> PRE_STEEL_CHUNK = ITEMS.register("pre_steel_chunk",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> STEEL_COMPOUND = ITEMS.register("steel_compound",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> STEEL_MIXTURE = ITEMS.register("steel_mixture",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> COAL_DUST = ITEMS.register("coal_dust",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> REROLL_ORB = ITEMS.register("reroll_orb",
            () -> new RerollOrb(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> LOST_FRAGMENT = ITEMS.register("lost_fragment",
            () -> new LostFragment(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> LOST_NOTE = ITEMS.register("lost_note",
            () -> new LostNote(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.TREASURY)));

    public static final RegistryObject<Item> LOST_RECORD = ITEMS.register("lost_record",
            () -> new LostRecord(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> ASTRAL_ENERGY = ITEMS.register("astral_energy",
            () -> new AstralEnergy(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ALIEN_HEART = ITEMS.register("alien_heart",
            () -> new AlienHeart(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ASTRAL_HEART = ITEMS.register("astral_heart",
            () -> new AstralHeart(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> DARKMATTER_HEART = ITEMS.register("darkmatter_heart",
            () -> new DarkmatterHeart(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> HOLY_HEART = ITEMS.register("holy_heart",
            () -> new HolyHeart(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> NETHER_HEART = ITEMS.register("nether_heart",
            () -> new NetherHeart(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> STARFORGED_HEART = ITEMS.register("starforged_heart",
            () -> new StarforgedHeart(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ETERNAL_STONE = ITEMS.register("eternal_stone",
            () -> new EternalStone(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> BLOODSTONE = ITEMS.register("bloodstone",
            () -> new Bloodstone(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.MONSTROUS)));

    public static final RegistryObject<Item> TOKEN_OFADVENTURER = ITEMS.register("token_ofadventurer",
            () -> new TokenofAdventurer(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    // Repair Kits

    public static final RegistryObject<Item> UNCOMMON_REPAIRKIT = ITEMS.register("uncommon_repairkit",
            () -> new RepairkitUncommon(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> ADVANCED_REPAIRKIT = ITEMS.register("advanced_repairkit",
            () -> new RepairkitAdvanced(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.ADVANCED)));

    public static final RegistryObject<Item> ALIEN_REPAIRKIT = ITEMS.register("alien_repairkit",
            () -> new RepairkitAlien(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.OUTER)));

    public static final RegistryObject<Item> ENDER_REPAIRKIT = ITEMS.register("ender_repairkit",
            () -> new RepairkitEnder(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ENERGIZED_REPAIRKIT = ITEMS.register("energized_repairkit",
            () -> new RepairkitEnergized(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.VENOMOUS)));

    public static final RegistryObject<Item> LEGENDARY_REPAIRKIT = ITEMS.register("legendary_repairkit",
            () -> new RepairkitLegendary(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.LEGENDARY)));

    public static final RegistryObject<Item> MYTHIC_REPAIRKIT = ITEMS.register("mythic_repairkit",
            () -> new RepairkitMythic(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(KtmItemRarity.FORBIDDEN)));

    // Stone Variants

    public static final RegistryObject<Item> ANDESITE_LITHIC_FLAKE = ITEMS.register("andesite_lithic_flake",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> DIORITE_LITHIC_FLAKE = ITEMS.register("diorite_lithic_flake",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> LIMESTONE_LITHIC_FLAKE = ITEMS.register("limestone_lithic_flake",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    public static final RegistryObject<Item> GRANITE_LITHIC_FLAKE = ITEMS.register("granite_lithic_flake",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES)));

    // WyrmCrystal Items

    public static final RegistryObject<Item> BLACKWYRM_DRAGON_CRYSTAL = ITEMS.register("blackwyrm_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> WHITEWYRM_DRAGON_CRYSTAL = ITEMS.register("whitewyrm_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> CRYSTALLITE_DRAGON_CRYSTAL = ITEMS.register("crystallite_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ENDER_DRAGON_CRYSTAL = ITEMS.register("ender_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ICE_DRAGON_CRYSTAL = ITEMS.register("ice_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> FIRE_DRAGON_CRYSTAL = ITEMS.register("fire_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ELECTRO_DRAGON_CRYSTAL = ITEMS.register("electro_dragon_crystal",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMRESOURCES).rarity(Rarity.RARE)));

    // Tier 3 Ingot Items

    public static final RegistryObject<Item> CYADIA_INGOT = ITEMS.register("cyadia_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> GREENDUX_INGOT = ITEMS.register("greendux_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> INDIGO_INGOT = ITEMS.register("indigo_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> MOUNTSAKURA_INGOT = ITEMS.register("mountsakura_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> NEPTUNIUM_INGOT = ITEMS.register("neptunium_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> RADIALIME_INGOT = ITEMS.register("radialime_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> REDIRON_INGOT = ITEMS.register("rediron_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    public static final RegistryObject<Item> SHINEBLAZING_INGOT = ITEMS.register("shineblazing_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.UNCOMMON)));

    // Tier 2 Ingot Items

    public static final RegistryObject<Item> DIMCOPPER_INGOT = ITEMS.register("dimcopper_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> ENGERGIZE_INGOT = ITEMS.register("energize_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> FIREGEM_INGOT = ITEMS.register("firegem_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> KNIGHTLYGOLD_INGOT = ITEMS.register("knightlygold_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> LEAFIUM_INGOT = ITEMS.register("leafium_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> LIQUIDIZE_INGOT = ITEMS.register("liquidize_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> OBSIDIUM_INGOT = ITEMS.register("obsidium_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> PRIMALSTEEL_INGOT = ITEMS.register("primalsteel_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> PRIMALWARLOCK_INGOT = ITEMS.register("primalwarlock_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> SANGUINE_INGOT = ITEMS.register("sanguine_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> STARFORGED_INGOT = ITEMS.register("starforged_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    public static final RegistryObject<Item> UNPOWERED_INGOT = ITEMS.register("unpowered_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).rarity(Rarity.RARE)));

    // Tier 1 Ingot Items

    public static final RegistryObject<Item> CYBERIUM_INGOT = ITEMS.register("cyberium_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> EMPOWEREDLEAFIUM_INGOT = ITEMS.register("empoweredleafium_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ENERGYINFUSED_INGOT = ITEMS.register("energyinfused_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> EXTREMEDIMENSIONAL_INGOT = ITEMS.register("extremedimensional_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> GLOWCOMPLEXITY_INGOT = ITEMS.register("glowcomplexity_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> KINGDOMGOLD_INGOT = ITEMS.register("kingdomgold_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> MANAINFUSED_INGOT = ITEMS.register("manainfused_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> ULTIMATEAMETHYST_INGOT = ITEMS.register("ultimateamethyst_ingot",
            () -> new Item(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(Rarity.EPIC)));

    // Mythic Ingot Items

    public static final RegistryObject<Item> ADAMANTITE_INGOT = ITEMS.register("adamantite_ingot",
            () -> new AdamantiteIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> CRIMSONGOLD_INGOT = ITEMS.register("crimsongold_ingot",
            () -> new CrimsongoldIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> DAMASCUSSTEEL_INGOT = ITEMS.register("damascussteel_ingot",
            () -> new DamascussteelIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> DEMONSTEEL_INGOT = ITEMS.register("demonsteel_ingot",
            () -> new DemonsteelIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> MYTHRIL_INGOT = ITEMS.register("mythril_ingot",
            () -> new MythrilIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> ORICHALCUM_INGOT = ITEMS.register("orichalcum_ingot",
            () -> new OrichalcumIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    public static final RegistryObject<Item> VIBRANIUM_INGOT = ITEMS.register("vibranium_ingot",
            () -> new VibraniumIngot(new Item.Properties().tab(KtmItemTab.KTMINGOTS).fireResistant().rarity(KtmItemRarity.FORBIDDEN)));

    // Buckets

    public static final RegistryObject<Item> BLAZINGSOUL_BUCKET = ITEMS.register("blazingsoul_bucket",
            ()-> new BucketItem(KtmFluid.BLAZINGSOUL,
                    new BucketItem.Properties().craftRemainder(Items.BUCKET).stacksTo(1).tab(KtmItemTab.KTMBUCKETS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
