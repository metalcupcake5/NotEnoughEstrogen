package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import net.minecraft.client.gui.hud.PlayerListHud;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerListHud.class)
public class TabListMixin {
    @Inject(method = "getPlayerName", at = @At("HEAD"))
    private void getPlayerName(PlayerListEntry entry, CallbackInfoReturnable<Text> cir) {
        //Test.LOGGER.info(entry.getDisplayName());
        //JsonElement data = Text.Serializer.toJsonTree(entry.getDisplayName());
        //Test.LOGGER.info(data);
    }

    @Inject(method = "render", at = @At("HEAD"))
    private void render(MatrixStack matrices, int scaledWindowWidth, Scoreboard scoreboard, ScoreboardObjective objective, CallbackInfo ci){

    }
}