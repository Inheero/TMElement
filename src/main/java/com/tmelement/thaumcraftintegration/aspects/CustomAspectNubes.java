package com.tmelement.thaumcraftintegration.aspects;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class CustomAspectNubes {

    public static Aspect NUBES;

    public static void init() {
        NUBES = new Aspect(
                "nubes",
                0xFF288D8F,
                new Aspect[] {Aspect.AIR, Aspect.MOTION},
                new ResourceLocation("tmelements", "textures/aspects/nubes.png"),
                1
        );
    }
}