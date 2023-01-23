package ls.project.chatgames;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static Main instance;
    ChatGames games;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        games = new ChatGames();

        getCommand("games").setExecutor(games);
        Bukkit.getPluginManager().registerEvents(new ChatListener(), this);


    }

    public ChatGames getGames(){ return games; }
}
