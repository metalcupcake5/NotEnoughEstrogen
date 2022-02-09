package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import io.github.metalcupcake5.JustEnoughUpdates.config.ConfigManager;
import io.github.metalcupcake5.JustEnoughUpdates.utils.Commission;
import io.github.metalcupcake5.JustEnoughUpdates.utils.SkyblockChecker;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawableHelper;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.MathHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(InGameHud.class)
public class InGameHudMixin extends DrawableHelper {
    @Inject(method = "render", at = @At("HEAD"))
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo ci){
        MinecraftClient client = MinecraftClient.getInstance();
        if(ConfigManager.showFps) {
            Text fps = Text.of("fps: " + client.fpsDebugString.split(" fps")[0].trim());
            int fpsCounterWidth = client.textRenderer.getWidth(fps);

            int screenBorder = 30;
            int x = 1;
            int y = 1;
            fill(matrixStack, x, y, fpsCounterWidth + 6 + x, 10 + y, MathHelper.ceil((255.0D * client.options.textBackgroundOpacity)) << 24);
            client.textRenderer.drawWithShadow(matrixStack, fps, 3 + x, 1 + y, 16777215);
        }
        List<Commission> comms = SkyblockChecker.commissions;
        if(!comms.isEmpty()){
            int baseX = 1;
            int baseY = 1;
            for(int i = 0; i < comms.size(); i++){
                Commission comm = comms.get(i);
                String name = comm.getName();
                String progress = comm.getProgress();
                Text fps = Text.of(name + ": " + progress);
                int fpsCounterWidth = client.textRenderer.getWidth(fps);

                fill(matrixStack, baseX, baseY+ (10 * i), fpsCounterWidth + 6 + baseX, 10 + baseY + (10 * i), MathHelper.ceil((255.0D * client.options.textBackgroundOpacity)) << 24);
                client.textRenderer.drawWithShadow(matrixStack, fps, 3 + baseX, 1 + baseY + (10 * i), 0xffffffff);
            }
        }
    }
}
