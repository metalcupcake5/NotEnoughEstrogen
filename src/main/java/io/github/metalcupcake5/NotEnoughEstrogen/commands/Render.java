package io.github.metalcupcake5.NotEnoughEstrogen.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import io.github.metalcupcake5.NotEnoughEstrogen.utils.RendererHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class Render {
    public static int run(CommandContext context) throws CommandSyntaxException {
        MinecraftClient client = MinecraftClient.getInstance();
        BlockPos pos = client.player.getBlockPos();
        Vec3d vec3d = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        RendererHelper.coords.put(vec3d, new Color(255, 0, 0, 50));
        return 1;
    }
}
