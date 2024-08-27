package com.shliggy.ars_temeritas.client;

import com.shliggy.ars_temeritas.ArsTemeritas;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.Entity;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import static com.shliggy.ars_temeritas.client.registry.ModKeybindings.CHANT_AUGMENT;
import static com.shliggy.ars_temeritas.client.registry.ModKeybindings.CHANT_FORM_EFFECT;

@EventBusSubscriber(modid = ArsTemeritas.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class EventQueue {
    @SubscribeEvent
    public void onClientTick(ClientTickEvent.Post event) {
        while (CHANT_FORM_EFFECT.isDown()) {
            Entity entity =  Minecraft.getInstance().player;
            if (!entity.level().isClientSide()) {
                entity.igniteForSeconds(1);
            }
        }
        while (CHANT_AUGMENT.consumeClick()) {
            Entity entity =  Minecraft.getInstance().player;
            if (!entity.level().isClientSide()) {
                entity.igniteForSeconds(5);
            }
        }
    }
}
