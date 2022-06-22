package io.github.metalcupcake5.JustEnoughUpdates.mixin;

import io.github.metalcupcake5.JustEnoughUpdates.utils.ItemUtils;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.ChatHudListener;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(ChatHudListener.class)
public class ChatHudListenerMixin {
    @Inject(method = "onChatMessage", at = @At("HEAD"))
    private void onChatMessage(MessageType type, Text message, UUID sender, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        String msg = message.getString();
        Pattern usePattern = Pattern.compile("You used your (([A-z]).+) Pickaxe Ability");
        Pattern readyPattern = Pattern.compile("(([A-z]).+) is now available");
        if(usePattern.matcher(msg).find()) {
            ItemUtils.pickaxeAbilityCooldown = 120;
        } else if(readyPattern.matcher(msg).find()) {
            ItemUtils.pickaxeAbilityCooldown = 0;
        } else if(msg.contains("This ability is on cooldown for") && ItemUtils.isMiningTool(client.player.getMainHandStack())) {
            Pattern cooldown = Pattern.compile("\\d+");
            Matcher m = cooldown.matcher(msg);
            if(m.find()) {
                ItemUtils.pickaxeAbilityCooldown = Integer.parseInt(msg.substring(m.start(), m.end()));
            }
        }
    }
}
