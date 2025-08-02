package com.tmelement.thaumcraftintegration.aspects;

import net.minecraft.util.ResourceLocation;
import thaumcraft.api.aspects.Aspect;

public class CustomAspectsSolstitium {

    public static Aspect SOLSTITIUM;

    public static void init() {
        SOLSTITIUM = new Aspect(
                "solstitium",
                0xFF288D8F,
                new Aspect[] {Aspect.AIR, Aspect.MAGIC},
                new ResourceLocation("tmelements", "textures/aspects/nubes.png"),
                1
        );
    }
}