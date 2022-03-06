package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import com.mojang.blaze3d.systems.RenderSystem;
import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.utils.Drill;
import io.github.metalcupcake5.JustEnoughUpdates.utils.ItemUtils;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow
    protected abstract void renderGuiQuad(BufferBuilder buffer, int x, int y, int width, int height, int red, int green, int blue, int alpha);

    @Inject(method = "renderGuiItemOverlay(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/item/ItemStack;IILjava/lang/String;)V", at = @At("HEAD"))
    private void renderGuiItemOverlay(TextRenderer renderer, ItemStack stack, int x, int y, @Nullable String countLabel, CallbackInfo ci){
        if(SkyblockChecker.inSkyblock && ConfigManager.showDrillFuel) {
            NbtCompound data = ItemUtils.getSkyblockData(stack);
            if(data == null) return;
            if(!data.contains("drill_fuel")) return;
            Drill drill = new Drill(ItemUtils.getLore(stack));
            float max = drill.maxFuel;
            float current = drill.currentFuel;

            /*
                taken from https://github.com/Kraineff/Skyblocker/
             */
            RenderSystem.disableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.disableBlend();
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder buffer = tessellator.getBuffer();
            float hue = Math.max(0.0F, 1.0F - (max - current) / max);
            int width = Math.round(current / max * 13.0F);
            int rgb = MathHelper.hsvToRgb(hue / 3.0F, 1.0F, 1.0F);
            this.renderGuiQuad(buffer, x + 2, y + 13, 13, 2, 0,0,0,255);
            this.renderGuiQuad(buffer, x + 2, y + 13, width, 1, rgb >> 16 & 255, rgb >> 8 & 255, rgb & 255, 255);
            RenderSystem.enableBlend();
            //RenderSystem.enableAlphaTest();
            RenderSystem.enableTexture();
            RenderSystem.enableDepthTest();
        }
    }
}
