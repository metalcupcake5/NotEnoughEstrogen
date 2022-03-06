package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.utils.Commission;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo ci){
        MinecraftClient client = MinecraftClient.getInstance();
        if(ConfigManager.showCommissions) {
            List<Commission> comms = SkyblockChecker.commissions;
            if (!comms.isEmpty()) {
                int baseX = 1;
                int baseY = 1;
                int longestWidth = 1;
                ArrayList<Text> texts = new ArrayList<>();
                for (Commission comm : comms) {
                    String name = comm.getName();
                    String progress = comm.getProgress();
                    Text commissionText = Text.of(Formatting.WHITE + name + ": " + progress);
                    int width = client.textRenderer.getWidth(commissionText);
                    longestWidth = Math.max(longestWidth, width);
                    texts.add(commissionText);
                }
                renderBackground(matrixStack, baseX, baseY, longestWidth + 8, comms.size() * 10 + 8);
                for(int i = 0; i < texts.size(); i++){
                    renderText(matrixStack, texts.get(i), 4 + baseX, 5 + baseY + (10 * i));
                }
            }
        }
    }

    public void renderText(MatrixStack matrixStack, Text text, int originX, int originY){
        MinecraftClient client = MinecraftClient.getInstance();
        client.textRenderer.drawWithShadow(matrixStack, text, originX, originY, 0xffffffff);
    }

    public void renderBackground(MatrixStack matrixStack, int originX, int originY, int width, int height){
        MinecraftClient client = MinecraftClient.getInstance();
        fill(matrixStack, originX, originY, originX + width, originY + height, MathHelper.ceil((255.0D * client.options.textBackgroundOpacity)) << 24);
    }
}
