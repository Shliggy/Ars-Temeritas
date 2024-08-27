package com.shliggy.ars_temeritas.client;

import com.shliggy.ars_temeritas.ArsTemeritas;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import static com.shliggy.ars_temeritas.client.registry.ModKeybindings.CHANT_FORM_EFFECT;

public class ClientTick {
    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event){
        while(CHANT_FORM_EFFECT.isDown()){
            System.out.println("amongus");
        }
    }
}
