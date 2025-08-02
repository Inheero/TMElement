package com.tmelement.thaumcraftintegration.aspects;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class CustomAspectScarabaeus {

    public static Aspect SCARABEUS;

    public static void init() {
        SCARABEUS = new Aspect(
                "scarabeus",
                0xFF288D8F,
                new Aspect[] {Aspect.LIGHT, Aspect.PLANT},
                new ResourceLocation("tmelements", "textures/aspects/nubes.png"),
                1
        );
    }
}