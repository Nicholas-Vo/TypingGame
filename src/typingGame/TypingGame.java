package typingGame;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TypingGame extends JavaPlugin implements Listener {
	public static TypingGame plugin;
	public static boolean gameEnabled;
	private boolean sentenceDisplayed;
	private long start;
	private String currentSentence;
	public HashMap<UUID, Integer> map = new HashMap<UUID, Integer>();
	String PLUGIN_VERSION = "1.0.0";
	Logger log = Bukkit.getLogger();

	public void onEnable() {
		plugin = this;
		new Sentences();
		getServer().getPluginManager().registerEvents(this, this);
		this.log.info("Enabling TypingGame " + PLUGIN_VERSION + "...");
		this.getCommand("game").setExecutor(new ToggleCommand());

		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
			@Override
			public void run() {
				if (!gameEnabled || sentenceDisplayed) {
					return;
				}
				
				start = System.currentTimeMillis();
				sentenceDisplayed = true;
				String sentence = Sentences.getSentence();
				currentSentence = sentence;
				msg("The sentence to type is " + ChatColor.GRAY + sentence);
				Sentences.s.remove(sentence);
			}
		}, 20, 120); // delay, period
	}

	public void onDisable() {
		this.log.info("Disabled TypingGame " + PLUGIN_VERSION + ".");
	}

	private void msg(String string) {
		Bukkit.broadcastMessage(ChatColor.GOLD + "[Game] " + ChatColor.WHITE + string);
	}

	public static long getHoursPlayed(OfflinePlayer p) {
		long ticks = p.getStatistic(Statistic.PLAY_ONE_MINUTE);
		long seconds = ticks / 20;
		long minutes = seconds / 60;
		long hours = minutes / 60;
		return hours;
	}

	@EventHandler
	public void onChat(AsyncPlayerChatEvent e) {
		Player player = e.getPlayer();
		UUID id = player.getUniqueId();

		if (sentenceDisplayed) {
			if (e.getMessage().equalsIgnoreCase(currentSentence)) {
				e.setCancelled(true);
				sentenceDisplayed = false;

				if (map.get(id) == null) {
					map.put(id, 1);
				} else {
					map.put(id, map.get(id) + 1);
				}

				long time = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - start);
				msg(player.getName() + " typed the sentence in " + ChatColor.RED + time + ChatColor.WHITE
						+ " seconds! Points: " + ChatColor.RED + map.get(id));
			}
		}
	}
}
