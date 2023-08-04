package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import io.github.metalcupcake5.NotEnoughEstrogen.utils.MouseUtils;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HandledScreen.class)
public class HandledScreenMixin {
    @Inject(method = "mouseClicked", at = @At("HEAD"))
    private void mouseClicked(double mouseX, double mouseY, int button, CallbackInfoReturnable<Boolean> cir) {
        System.out.println("mouse clicked, setting to " + mouseX + mouseY);
        MouseUtils.setMousePos(mouseX, mouseY);
    }
}
