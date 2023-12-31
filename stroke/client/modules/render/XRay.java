package net.stroke.client.modules.render;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.stroke.client.modules.BaseModule;
import net.stroke.client.modules.ModuleCategory;

public class XRay extends BaseModule {
	public static XRay instance;
	
	public static ArrayList<Block> blocks = new ArrayList<Block>();
	
	public XRay() {
		super("XRay", "See ores through walls", 0x00, ModuleCategory.Render, false);
		instance = this;
	}
	
	public void onEnable() {
		if(blocks.size() == 0) {
			this.addBlock(Blocks.DIAMOND_ORE);
			this.addBlock(Blocks.GOLD_ORE);
			this.addBlock(Blocks.IRON_ORE);
			this.addBlock(Blocks.COAL_ORE);
			this.addBlock(Blocks.LAPIS_ORE);
			this.addBlock(Blocks.REDSTONE_ORE);
			this.addBlock(Blocks.EMERALD_ORE);
			this.addBlock(Blocks.QUARTZ_ORE);
			
			this.addBlock(Blocks.LAVA);
			this.addBlock(Blocks.WATER);
		}
		
		mc.renderGlobal.loadRenderers();
	}
	
	public void onDisable() {
		mc.renderGlobal.loadRenderers();
	}
	
	public void addBlock(Block block) {
		this.instance.blocks.add(block);
	}
}
