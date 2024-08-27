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
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ArsTemeritas.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModKeybindings {
    public static final String CATEGORY = "key.category.ars_temeritas.general";

    // IDEA: key bind (e.g. here) is CONTROL and i just .get() other pressed keys to use a specific glyph
    public static final KeyMapping CHANT_FORM_EFFECT =  new KeyMapping(
                    "key.ars_temeritas.chant_form_effect",
                    ArsTemeritasKeyConflictContext.CHANT_FORM_EFFECT,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_CONTROL,
                    CATEGORY);

    public static final KeyMapping CHANT_AUGMENT = new KeyMapping(
                    "key.ars_temeritas.chant_augment",
                    ArsTemeritasKeyConflictContext.CHANT_AUGMENT,
                    InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_SHIFT,
                    CATEGORY);

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(CHANT_FORM_EFFECT);
        event.register(CHANT_AUGMENT);
    }

    // this literally doesn't work
    public enum ArsTemeritasKeyConflictContext implements IKeyConflictContext{
        CHANT_FORM_EFFECT {
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
