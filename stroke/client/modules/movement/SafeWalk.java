package net.stroke.client.modules.movement;

import java.util.List;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class SafeWalk extends BaseModule {
	public SafeWalk() {
		super("SafeWalk", "Automatically sneaking when you are near void", 0x00, ModuleCategory.Movement, false);
	}
	
	public void onUpdate() {
		BlockPos below = new BlockPos(mc.player.posX, mc.player.posY - 1.0f, mc.player.posZ);
		KeyBinding sneak = mc.gameSettings.keyBindSneak;
		
		if(isAir(below) && !mc.player.capabilities.isFlying && !mc.player.movementInput.jump) {
			sneak.setKeyBindState(sneak.getKeyCode(), true);
		} else {
			sneak.setKeyBindState(sneak.getKeyCode(), Keyboard.isKeyDown(sneak.getKeyCode()));
		}
	}
	
	public void onDisable() {
		KeyBinding sneak = mc.gameSettings.keyBindSneak;
		sneak.setKeyBindState(sneak.getKeyCode(), false);
	}

	public boolean isAir(BlockPos pos) {
		return mc.world.getBlockState(pos).getBlock() == Blocks.AIR;
	}
}
