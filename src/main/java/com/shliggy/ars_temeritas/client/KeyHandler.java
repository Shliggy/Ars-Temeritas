package com.shliggy.ars_temeritas.client;

import com.hollingsworth.arsnouveau.client.registry.ModKeyBindings;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.InputEvent;

import static com.shliggy.ars_temeritas.Chanting.*;
import static com.shliggy.ars_temeritas.client.registry.ModKeybindings.*;


public class KeyHandler {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while(CHANT_TOGGLE.consumeClick() && !isActive() && !CHANT_TOGGLE.isUnbound()) {
            Chant("CHANT_START", null);
        }
        while(CHANT_TOGGLE.consumeClick() && !isActive() && !CHANT_TOGGLE.isUnbound()) {
            Chant("CHANT_STOP", null);
        }
    }

    @SubscribeEvent
    public static void keyEvent(final InputEvent.Key event) {
        if ((usedChantLyric(event.getKey()) != -1) && !isActive()) {
            String key = String.valueOf(event.getKey());
            System.out.println(key);
            addGlyph(key);
        }
    }
}
