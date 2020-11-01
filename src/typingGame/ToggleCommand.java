package typingGame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ToggleCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.isOp()) {
			sender.sendMessage("You can't do that.");
			return true;
		}

		if (TypingGame.gameEnabled) {
			Bukkit.broadcastMessage(
					ChatColor.GOLD + "[Game] " + ChatColor.RESET + sender.getName() + " has disabled the typing game.");
			TypingGame.gameEnabled = false;
		} else {
			Bukkit.broadcastMessage(
					ChatColor.GOLD + "[Game] " + ChatColor.RESET + sender.getName() + " has enabled the typing game!");
			TypingGame.gameEnabled = true;
		}
		return false;
	}

}
