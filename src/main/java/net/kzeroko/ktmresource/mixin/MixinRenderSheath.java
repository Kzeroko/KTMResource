package net.kzeroko.ktmresource.mixin;

import net.kzeroko.ktmresource.init.KtmItems;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.client.events.engine.RenderEngine;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.client.renderer.patched.item.RenderKatana;

import java.util.Map;


@Mixin(RenderEngine.class)
public class MixinRenderSheath {
    @Inject(at = @At("RETURN"),method = "registerRenderer")
    private void onRegisterRenderer(CallbackInfo info) {
        this.itemRendererMapByInstance.put(KtmItems.HF_MURAMASA.get(), new RenderKatana());
    }
    @Shadow private Map<Item, RenderItemBase> itemRendererMapByInstance;

}
