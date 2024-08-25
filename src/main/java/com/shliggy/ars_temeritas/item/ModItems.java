package com.shliggy.ars_temeritas.item;

import com.hollingsworth.arsnouveau.setup.registry.ModPotions;
import com.shliggy.ars_temeritas.ArsTemeritas;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

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



    public static void register(IEventBus eventBus) { ITEMS.register(eventBus); }
}