package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import io.github.metalcupcake5.NotEnoughEstrogen.utils.GardenUtils;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.ItemUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ChatHud.class)
public class ChatHudListenerMixin {
    @Inject(method = "addMessage(Lnet/minecraft/text/Text;)V", at = @At("HEAD"))
    private void onChatMessage(Text message, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        String msg = message.getString();
        Pattern usePattern = Pattern.compile("You used your (([A-z]).+) Pickaxe Ability");
        Pattern readyPattern = Pattern.compile("(([A-z]).+) is now available");
        Pattern compostPattern = Pattern.compile("Picked up \\d Compost");
        Pattern compostPattern2 = Pattern.compile("Added \\d+ organic matter to the composter");

        if (usePattern.matcher(msg).find()) {
            ItemUtils.pickaxeAbilityCooldown = 120;
        } else if (readyPattern.matcher(msg).find()) {
            ItemUtils.pickaxeAbilityCooldown = 0;
        } else if (msg.contains("This ability is on cooldown for") && ItemUtils.isMiningTool(client.player.getMainHandStack())) {
            Pattern cooldown = Pattern.compile("\\d+");
            Matcher m = cooldown.matcher(msg);
            if (m.find()) {
                ItemUtils.pickaxeAbilityCooldown = Integer.parseInt(msg.substring(m.start(), m.end()));
            }
        } else if (compostPattern.matcher(msg).find()) {
            GardenUtils.setCompostAvailable(0);
        } else if (compostPattern2.matcher(msg).find()) {
            GardenUtils.checkArmorStands();
        }
    }
}
