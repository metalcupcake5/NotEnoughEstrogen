package io.github.metalcupcake5.NotEnoughEstrogen.events;

import io.github.metalcupcake5.NotEnoughEstrogen.utils.RendererHelper;
import me.x150.renderer.event.EventListener;
import me.x150.renderer.event.EventType;
import me.x150.renderer.event.Shift;
import me.x150.renderer.event.events.RenderEvent;
import me.x150.renderer.renderer.Renderer3d;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.awt.*;

public class RenderEvents {

    @EventListener(shift = Shift.POST, value = EventType.WORLD_RENDER)
    void worldRender(RenderEvent re) {
        Renderer3d.startRenderingThroughWalls();
        MinecraftClient client = MinecraftClient.getInstance();
        BlockPos pos = client.player.getBlockPos();
        Vec3d vec3d = new Vec3d(-1, 65, 20);
        RendererHelper.coords.forEach((coord, color) -> {
            Renderer3d.renderBlockWithEdges(coord, new Vec3d(1, 1, 1), new me.x150.renderer.renderer.color.Color(color), new me.x150.renderer.renderer.color.Color(Color.red)).drawAllWithoutVbo(re.getStack());
        });
    }

//    @EventListener(shift = Shift.POST, type = EventType.ENTITY_RENDER)
//    void entityRender(RenderEvent re) {
//        //System.out.println(entity.getType().toString());
//
//    }
}