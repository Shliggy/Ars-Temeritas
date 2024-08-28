package com.shliggy.ars_temeritas.item;

import com.hollingsworth.arsnouveau.common.items.ModItem;
import com.hollingsworth.arsnouveau.common.items.SpellParchment;
import com.hollingsworth.arsnouveau.common.lib.LibItemNames;
import com.hollingsworth.arsnouveau.setup.registry.ItemRegistryWrapper;
import com.hollingsworth.arsnouveau.setup.registry.ItemsRegistry;
import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import com.shliggy.ars_temeritas.ArsTemeritas;
import com.shliggy.ars_temeritas.item.custom.SpellLyricsItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(ArsTemeritas.MODID);

    public static final DeferredItem<Item> SOURCEBERRY_BUNDLE = ITEMS.register("sourceberry_bundle",
            () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SOURCE_TOUCHED_APPLE = ITEMS.register("source_touched_apple",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6).saturationModifier(0.3F).effect(() -> new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 20 * 60), 1.0F).alwaysEdible().build())));
    public static final DeferredItem<Item> SOURCE_BOUND_APPLE = ITEMS.register("source_bound_apple",
            () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
                    .nutrition(6).saturationModifier(0.5F).effect(() -> new MobEffectInstance(ModPotions.MANA_REGEN_EFFECT, 20 * 40, 1), 1.0F).alwaysEdible().build())));

    public static final DeferredItem<Item> SPELL_INSCRIPTION_SMITHING_TEMPLATE = ITEMS.register("spell_inscription_smithing_template",
            () -> new Item(new Item.Properties()));

    public static final ItemRegistryWrapper<SpellLyricsItem> SPELL_LYRICS = register("spell_lyrics", SpellLyricsItem::new);

    public static <T extends Item> ItemRegistryWrapper<T> register(String name, Supplier<T> item) {
        return new ItemRegistryWrapper<>(ITEMS.register(name, item));
    }
    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}