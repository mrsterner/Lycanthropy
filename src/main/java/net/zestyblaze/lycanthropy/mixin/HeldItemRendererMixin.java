package net.zestyblaze.lycanthropy.mixin;

import net.zestyblaze.lycanthropy.common.item.FlintlockItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.item.ItemStack;

@Mixin(value = HeldItemRenderer.class)
public abstract class HeldItemRendererMixin {

    @Mutable
    @Final
    @Shadow
    private final MinecraftClient client;
    @Shadow
    private float equipProgressMainHand;
    @Shadow
    private float equipProgressOffHand;
    @Shadow
    private ItemStack mainHand;
    @Shadow
    private ItemStack offHand;

    private HeldItemRendererMixin(MinecraftClient client) {
        this.client = client;
    }


    @Inject(method = "updateHeldItems", at = @At("TAIL"))
    private void lycanthropy$cancelAnimation(CallbackInfo ci) {
        ClientPlayerEntity clientPlayerEntity = this.client.player;
        assert clientPlayerEntity != null;
        ItemStack itemStack = clientPlayerEntity.getMainHandStack();
        ItemStack itemStack2 = clientPlayerEntity.getOffHandStack();
        if ((this.mainHand.getItem() instanceof FlintlockItem) && (itemStack.getItem() instanceof FlintlockItem) && ItemStack.areItemsEqualIgnoreDamage(mainHand, itemStack)) {
            this.equipProgressMainHand = 1;
            this.mainHand = itemStack;
        }
        if ((this.offHand.getItem() instanceof FlintlockItem) && (itemStack2.getItem() instanceof FlintlockItem) && ItemStack.areItemsEqualIgnoreDamage(offHand, itemStack2)) {
            this.equipProgressOffHand = 1;
            this.offHand = itemStack2;
        }
    }
}