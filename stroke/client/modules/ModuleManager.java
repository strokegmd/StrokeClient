package net.stroke.client.modules;

import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.client.Minecraft;
import net.stroke.client.modules.combat.AntiKnockback;
import net.stroke.client.modules.combat.AutoArmor;
import net.stroke.client.modules.combat.AutoGApple;
import net.stroke.client.modules.combat.AutoTotem;
import net.stroke.client.modules.combat.Criticals;
import net.stroke.client.modules.combat.CrystalAura;
import net.stroke.client.modules.combat.KillAura;
import net.stroke.client.modules.fun.HalalMode;
import net.stroke.client.modules.misc.DiscordRPCModule;
import net.stroke.client.modules.misc.MCF;
import net.stroke.client.modules.misc.MigrationCape;
import net.stroke.client.modules.misc.SelfDestruct;
import net.stroke.client.modules.misc.Spammer;
import net.stroke.client.modules.misc.StashLogger;
import net.stroke.client.modules.movement.AirJump;
import net.stroke.client.modules.movement.AutoJump;
import net.stroke.client.modules.movement.EntitySpeed;
import net.stroke.client.modules.movement.Flight;
import net.stroke.client.modules.movement.InventoryMove;
import net.stroke.client.modules.movement.NoSlowDown;
import net.stroke.client.modules.movement.SafeWalk;
import net.stroke.client.modules.movement.Sprint;
import net.stroke.client.modules.movement.Step;
import net.stroke.client.modules.player.Blink;
import net.stroke.client.modules.player.ChestStealer;
import net.stroke.client.modules.player.FastPlace;
import net.stroke.client.modules.player.Freecam;
import net.stroke.client.modules.player.NoFall;
import net.stroke.client.modules.player.Portals;
import net.stroke.client.modules.player.Timer;
import net.stroke.client.modules.render.AntiOverlay;
import net.stroke.client.modules.render.BlockHitAnim;
import net.stroke.client.modules.render.ClickGui;
import net.stroke.client.modules.render.FullBright;
import net.stroke.client.modules.render.Hud;
import net.stroke.client.modules.render.NameTags;
import net.stroke.client.modules.render.NoScoreBoard;
import net.stroke.client.modules.render.NoWeather;
import net.stroke.client.modules.render.PlayerESP;
import net.stroke.client.modules.render.StorageESP;
import net.stroke.client.modules.render.TargetHUD;
import net.stroke.client.modules.render.Tracers;
import net.stroke.client.modules.render.ViewModel;
import net.stroke.client.modules.render.Wallhack;
import net.stroke.client.modules.render.XRay;

public class ModuleManager {
	public static Minecraft mc = Minecraft.getMinecraft();
	
	public static ArrayList<BaseModule> modules = new ArrayList<BaseModule>();
	
	public static void addModule(BaseModule module) {
		modules.add(module);
	}

	public static void sortModules() {
		modules.sort(Comparator.comparingInt(module -> mc.fontRendererObj.getStringWidth(((BaseModule)module).name)).reversed());
	}
	
	public static void loadModules() {
		ModuleManager.addModule(new AntiKnockback());
		ModuleManager.addModule(new AutoArmor());
		ModuleManager.addModule(new AutoGApple());
		ModuleManager.addModule(new AutoTotem());
		ModuleManager.addModule(new Criticals());
		ModuleManager.addModule(new CrystalAura());
		ModuleManager.addModule(new KillAura());
		
		ModuleManager.addModule(new AirJump());
		ModuleManager.addModule(new AutoJump());
		ModuleManager.addModule(new EntitySpeed());
		ModuleManager.addModule(new Flight());
		ModuleManager.addModule(new InventoryMove());
		ModuleManager.addModule(new NoSlowDown());
		ModuleManager.addModule(new SafeWalk());
		ModuleManager.addModule(new Sprint());
		ModuleManager.addModule(new Step());
	
		ModuleManager.addModule(new Blink());
		ModuleManager.addModule(new ChestStealer());
		ModuleManager.addModule(new FastPlace());
		ModuleManager.addModule(new Freecam());
		ModuleManager.addModule(new NoFall());
		ModuleManager.addModule(new Portals());
		ModuleManager.addModule(new Timer());
		
		ModuleManager.addModule(new AntiOverlay());
		ModuleManager.addModule(new BlockHitAnim());
		ModuleManager.addModule(new ClickGui());
		ModuleManager.addModule(new FullBright());
		ModuleManager.addModule(new Hud());
		ModuleManager.addModule(new NameTags());
		ModuleManager.addModule(new NoScoreBoard());
		ModuleManager.addModule(new NoWeather());
		ModuleManager.addModule(new PlayerESP());
		ModuleManager.addModule(new StorageESP());
		ModuleManager.addModule(new TargetHUD());
		ModuleManager.addModule(new Tracers());
		ModuleManager.addModule(new ViewModel());
		ModuleManager.addModule(new Wallhack());
		ModuleManager.addModule(new XRay());
		
		ModuleManager.addModule(new DiscordRPCModule());
		ModuleManager.addModule(new MCF());
		ModuleManager.addModule(new MigrationCape());
		ModuleManager.addModule(new SelfDestruct());
		ModuleManager.addModule(new Spammer());
		ModuleManager.addModule(new StashLogger());
		
		ModuleManager.addModule(new HalalMode());
	}
	
	public static BaseModule getModuleByName(String name) {
		for(BaseModule module : modules) {
			if(module.name.equalsIgnoreCase(name)) {
				return module;
			}
		}
		
		return null;
	}
	
	public static ArrayList<BaseModule> getModulesInCategory(ModuleCategory category)
	{
		ArrayList<BaseModule> modules = new ArrayList<BaseModule>();
		for (BaseModule module : ModuleManager.modules)
		{
			if (module.category == category)
			{
				modules.add(module);
			}
		}
		return modules;
	}
}
