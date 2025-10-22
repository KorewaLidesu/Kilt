package xyz.bluspring.kilt.compat.fabric.mixin.jei;

import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.library.plugins.vanilla.VanillaPlugin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import java.util.*;

@Pseudo
@Mixin(value = VanillaPlugin.class, remap = false)
public abstract class FabricVanillaPluginMixin {
    @Shadow
    private abstract <T> void registerFluidIngredients(IModIngredientRegistration registration, IPlatformFluidHelperInternal<T> platformFluidHelper);

    @Inject(method = "registerIngredients", at = @At("RETURN"))
    private void kilt$jei$registerForgeFluidIngredients(IModIngredientRegistration registration) {
        IPlatformFluidHelperInternal<?> platformFluidHelper = Services.PLATFORM.getFluidHelper();
		registerFluidIngredients(registration, platformFluidHelper);
    }
}
