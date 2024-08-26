package com.shliggy.ars_temeritas.client.registry;

import com.mojang.blaze3d.platform.InputConstants;
import com.shliggy.ars_temeritas.ArsTemeritas;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.jarjar.nio.util.Lazy;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.IKeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ArsTemeritas.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModKeybindings {
    public static final Lazy<KeyMapping>CHANT_FORM = Lazy.of(() -> new KeyMapping(
                    "key.ars_temeritas.chant_form",
                    ArsTemeritasKeyConflictContext.CHANT_FORM,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP,
                    "key.categories.misc"));

    public static final Lazy<KeyMapping>CHANT_EFFECT = Lazy.of(() -> new KeyMapping(
                    "key.ars_temeritas.chant_effect",
                    ArsTemeritasKeyConflictContext.CHANT_EFFECT,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP,
                    "key.categories.misc"));

    public static final Lazy<KeyMapping>CHANT_AUGMENT = Lazy.of(() -> new KeyMapping(
                    "key.ars_temeritas.chant_augment",
                    ArsTemeritasKeyConflictContext.CHANT_AUGMENT,
                    KeyModifier.SHIFT, InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UP,
                    "key.categories.misc"));

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(CHANT_FORM.get());
        event.register(CHANT_EFFECT.get());
        event.register(CHANT_AUGMENT.get());
    }

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
