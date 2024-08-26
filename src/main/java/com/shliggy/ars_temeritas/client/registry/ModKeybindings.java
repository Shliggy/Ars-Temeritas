package com.shliggy.ars_temeritas.client.registry;

import com.mojang.blaze3d.platform.InputConstants;
import com.shliggy.ars_temeritas.ArsTemeritas;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ArsTemeritas.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModKeybindings {
    public static final String CATEGORY = "key.category.ars_temeritas.general";

    public static final KeyMapping TOGGLE_CHANT = new KeyMapping(
                    "key.ars_temeritas.toggle_chant",
                    KeyConflictContext.IN_GAME,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL,
                    CATEGORY);

    public static final KeyMapping CHANT_FORM = new KeyMapping(
                    "key.ars_temeritas.chant_form",
                    ArsTemeritasKeyConflictContext.CHANT_FORM,
                    KeyModifier.CONTROL,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP,
                    CATEGORY);

    public static final KeyMapping CHANT_EFFECT =  new KeyMapping(
                    "key.ars_temeritas.chant_effect",
                    ArsTemeritasKeyConflictContext.CHANT_EFFECT,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT,
                    CATEGORY);

    public static final KeyMapping CHANT_AUGMENT = new KeyMapping(
                    "key.ars_temeritas.chant_augment",
                    ArsTemeritasKeyConflictContext.CHANT_AUGMENT,
                    KeyModifier.SHIFT,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_RIGHT,
                    CATEGORY);

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_CHANT);
        event.register(CHANT_FORM);
        event.register(CHANT_EFFECT);
        event.register(CHANT_AUGMENT);
    }

    // this literally doesn't work
    public enum ArsTemeritasKeyConflictContext implements IKeyConflictContext{
        CHANT_FORM {
            @Override
            public boolean isActive() { return KeyConflictContext.IN_GAME.isActive(); }

            @Override
            public boolean conflicts(IKeyConflictContext other) { return this == other; }
        },
        CHANT_EFFECT {
            @Override
            public boolean isActive() { return KeyConflictContext.IN_GAME.isActive(); }

            @Override
            public boolean conflicts(IKeyConflictContext other) { return this == other; }
        },
        CHANT_AUGMENT {
            @Override
            public boolean isActive() { return KeyConflictContext.IN_GAME.isActive(); }

            @Override
            public boolean conflicts(IKeyConflictContext other) { return this == other; }
        },
    }
}
