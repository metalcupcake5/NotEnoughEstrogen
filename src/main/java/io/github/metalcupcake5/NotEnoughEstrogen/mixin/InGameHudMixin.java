package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import io.github.metalcupcake5.NotEnoughEstrogen.config.ConfigManager;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.Commission;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.GardenUtils;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.SkyblockChecker;
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
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (SkyblockChecker.inSkyblock) {
            List<Text> lines = new ArrayList<>();
            int baseX = 1;
            int baseY = 1;
            int longestWidth = 1;

            if (ConfigManager.showCommissions && SkyblockChecker.inDwarvenMines) {
                List<Commission> comms = SkyblockChecker.commissions;
                if (!comms.isEmpty()) {
                    for (Commission comm : comms) {
                        String name = comm.getName();
                        String progress = comm.getProgress();
                        Text commissionText = Text.of(Formatting.WHITE + name + ": " + progress);
                        lines.add(commissionText);
                    }
                }
            }
            if (SkyblockChecker.inGarden) {
                lines.add(Text.of("Visitors: " + GardenUtils.getVisitorCount()));
                lines.add(Text.of("Time Until Next: " + Formatting.AQUA + GardenUtils.getVisitorTime()));
                lines.add(Text.of("Compost Stored: " + Formatting.GREEN + GardenUtils.getCompostAvailable()));
                lines.add(Text.of("Next Compost: " + Formatting.GREEN + GardenUtils.getCompostTime()));
                lines.add(Text.of("Compost Queued: " + Formatting.GREEN + GardenUtils.getQueuedCompost()));
            }
            for (Text line : lines) {
                longestWidth = Math.max(longestWidth, client.textRenderer.getWidth(line));
            }
            renderBackground(matrixStack, baseX, baseY, longestWidth + 8, lines.size() * 10 + 8);
            for (int i = 0; i < lines.size(); i++) {
                renderText(matrixStack, lines.get(i), 4 + baseX, 5 + baseY + (10 * i));
            }
        }

    }

    public void renderText(MatrixStack matrixStack, Text text, int originX, int originY) {
        MinecraftClient client = MinecraftClient.getInstance();
        client.textRenderer.drawWithShadow(matrixStack, text, originX, originY, 0xffffffff);
    }

    public void renderBackground(MatrixStack matrixStack, int originX, int originY, int width, int height) {
        MinecraftClient client = MinecraftClient.getInstance();
        fill(matrixStack, originX, originY, originX + width, originY + height, MathHelper.ceil((255.0D * client.options.getTextBackgroundOpacity().getValue())) << 24);
    }
}
