package io.github.metalcupcake5.NotEnoughEstrogen.mixin;

import io.github.metalcupcake5.NotEnoughEstrogen.utils.EnchantingHelper;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.GardenUtils;
import net.fabricmc.fabric.api.client.screen.v1.ScreenEvents;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.GenericContainerScreen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(ScreenEvents.class)
public class ScreenEventsMixin {
    @Inject(method = "beforeRender", at = @At("HEAD"))
    private static void beforeRender(Screen screen, CallbackInfoReturnable<Event<ScreenEvents.BeforeRender>> cir) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (screen instanceof GenericContainerScreen) {
            String name = screen.getTitle().getString();
            ScreenHandler provider = ((GenericContainerScreen) screen).getScreenHandler();
            if (provider.slots.size() < 1) return;
            if (name.matches("Ultrasequencer \\([A-z]*\\)")) {
                EnchantingHelper.ultrasequencer(provider.slots.get(0).inventory);
            } else if (name.matches("Composter")) {
                Inventory inventory = provider.slots.get(0).inventory;

                if (inventory.isEmpty()) return;

                String[] stored;

                // collect compost
                ItemStack compostItem = inventory.getStack(22);
                List<Text> compostLore = compostItem.getTooltip(client.player, TooltipContext.Default.NORMAL);
                if (compostLore.size() < 2) return;
                stored = compostLore.get(1).getString().split(":");
                if (stored.length < 2) return;
                String compostStored = stored[1].trim();
                System.out.println("compostStored");
                GardenUtils.setCompostAvailable(Integer.parseInt(compostStored));


                // crops
                ItemStack cropsItem = inventory.getStack(46);
                List<Text> cropsLore = cropsItem.getTooltip(client.player, TooltipContext.Default.NORMAL);
                if (cropsLore.size() < 2) return;
                stored = cropsLore.get(1).getString().trim().split("/");
                if (stored.length < 1) return;
                String cropsStored = String.join("", stored[0].split(","));
                double crops = Double.parseDouble(cropsStored);
                int minCompost = (int) crops / 4000;

                // fuel
                ItemStack fuelItem = inventory.getStack(52);
                List<Text> fuelLore = fuelItem.getTooltip(client.player, TooltipContext.Default.NORMAL);
                if (fuelLore.size() < 2) return;
                stored = fuelLore.get(1).getString().trim().split("/");
                if (stored.length < 1) return;
                String fuelStored = String.join("", stored[0].split(","));
                int fuel = Integer.parseInt(fuelStored);
                GardenUtils.setQueuedCompost(Math.min(minCompost, fuel / 2000));

                // collect compost: 22
                // crop meter: 46
                // fuel: 52
            }
        }
    }
}
