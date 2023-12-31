package net.stroke.client.commands;

import java.util.ArrayList;

import net.stroke.client.commands.impl.BindCommand;
import net.stroke.client.commands.impl.FriendsAddCommand;
import net.stroke.client.commands.impl.FriendsListCommand;
import net.stroke.client.commands.impl.FriendsRemoveCommand;
import net.stroke.client.commands.impl.HelpCommand;
import net.stroke.client.commands.impl.ModulesCommand;
import net.stroke.client.commands.impl.ToggleCommand;
import net.stroke.client.commands.impl.XRayAddCommand;
import net.stroke.client.commands.impl.XRayListCommand;
import net.stroke.client.commands.impl.XRayRemoveCommand;

public class CommandManager {
	public static ArrayList<BaseCommand> commands = new ArrayList<BaseCommand>();
	
	public static void addCommand(BaseCommand command) {
		commands.add(command);
	}
	
	public static String[] getArguments(String message) {
		return message.split(" ");
	}
	
	public static void loadCommands() {
		CommandManager.addCommand(new BindCommand());
		CommandManager.addCommand(new ToggleCommand());
		CommandManager.addCommand(new HelpCommand());
		CommandManager.addCommand(new FriendsAddCommand());
		CommandManager.addCommand(new FriendsRemoveCommand());
		CommandManager.addCommand(new FriendsListCommand());
		CommandManager.addCommand(new ModulesCommand());
		CommandManager.addCommand(new XRayAddCommand());
		CommandManager.addCommand(new XRayRemoveCommand());
		CommandManager.addCommand(new XRayListCommand());
	}
}
