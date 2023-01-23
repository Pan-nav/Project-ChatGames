package ls.project.chatgames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChatGames implements CommandExecutor {

    private final Main main = Main.instance;

    private final List<ConfigurationSection> keys = new ArrayList<>();

    private final BukkitScheduler scheduler = Bukkit.getScheduler();

    public String currentTask;

    public int task;

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (!(sender instanceof Player player)) return false;

        player.sendMessage(ChatColor.YELLOW + "Chat Games started!");


        ConfigurationSection questionSection = main.getConfig().getConfigurationSection("Questions");

        for (String questionNumber : questionSection.getKeys(false)) {

            ConfigurationSection question = questionSection.getConfigurationSection(questionNumber);

            keys.add(question);
        }


        scheduler.scheduleSyncRepeatingTask(main, () -> {

            int rand = new Random().nextInt(keys.size());
            ConfigurationSection sec = keys.get(rand);

            Bukkit.broadcastMessage(sec.getString("question"));
            currentTask = sec.getString("answer");

        }, 0L, 6000L);




        task = scheduler.scheduleSyncRepeatingTask(main, () -> {

            currentTask = null;   //ending the task by nullifying the current answer
            Bukkit.broadcastMessage("Current task set to null");

        }, 1200L, 1200L);



        return false;
    }


}
