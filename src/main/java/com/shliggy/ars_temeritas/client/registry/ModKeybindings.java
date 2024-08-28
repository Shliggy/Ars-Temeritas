package com.shliggy.ars_temeritas.client.registry;

import com.mojang.blaze3d.platform.InputConstants;
import com.shliggy.ars_temeritas.ArsTemeritas;
import net.minecraft.client.KeyMapping;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

@EventBusSubscriber(modid = ArsTemeritas.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModKeybindings {
    public static final String CATEGORY_GENERAL = "key.category.ars_temeritas.general";
    public static final String CATEGORY_LYRICS = "key.category.ars_temeritas.lyrics";

    public static final KeyMapping CHANT_TOGGLE = new KeyMapping(
            "key.ars_temeritas.chant_toggle",
            KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_APOSTROPHE,
            CATEGORY_GENERAL);

    // CL - Chant lyric, possible chant craft bypass: binding every bind to one key
    public static final KeyMapping CL_1 = new KeyMapping("key.ars_temeritas.cl1", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_2 = new KeyMapping("key.ars_temeritas.cl2", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_3 = new KeyMapping("key.ars_temeritas.cl3", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_4 = new KeyMapping("key.ars_temeritas.cl4", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_5 = new KeyMapping("key.ars_temeritas.cl5", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_6 = new KeyMapping("key.ars_temeritas.cl6", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_7 = new KeyMapping("key.ars_temeritas.cl7", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_8 = new KeyMapping("key.ars_temeritas.cl8", -1, CATEGORY_LYRICS);
    public static final KeyMapping CL_9 = new KeyMapping("key.ars_temeritas.cl9", -1, CATEGORY_LYRICS);

    @SubscribeEvent
    public static void register(RegisterKeyMappingsEvent event) {
        event.register(CHANT_TOGGLE);
        event.register(CL_1);
        event.register(CL_2);
        event.register(CL_3);
        event.register(CL_4);
        event.register(CL_5);
        event.register(CL_6);
        event.register(CL_7);
        event.register(CL_8);
        event.register(CL_9);
    }

    // Ty @baileyholl https://github.com/baileyholl/Ars-Nouveau/blob/1.21.x/src/main/java/com/hollingsworth/arsnouveau/client/registry/ModKeyBindings.java
    public static int usedChantLyric(int key) {
        for (ChantLyric q : ChantLyric.VALUES) {
            if (q.key().getKey().getValue() == key) {
                return q.slot;
            }
        }
        return -1;
    }

    public record ChantLyric(int slot, KeyMapping key) {
        public static final ChantLyric[] VALUES = new ChantLyric[]{
                new ChantLyric(1, CL_1),
                new ChantLyric(2, CL_2),
                new ChantLyric(3, CL_3),
                new ChantLyric(4, CL_4),
                new ChantLyric(5, CL_5),
                new ChantLyric(6, CL_6),
                new ChantLyric(7, CL_7),
                new ChantLyric(8, CL_8),
                new ChantLyric(9, CL_9)
        };
    }
}
