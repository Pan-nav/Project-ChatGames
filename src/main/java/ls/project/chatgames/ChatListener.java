package ls.project.chatgames;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;


public class ChatListener implements Listener {

    Main main = Main.instance;
    ChatGames games = main.getGames();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {

        String message = e.getMessage();

        ItemStack reward = new ItemStack(Material.DIAMOND);

        if (message.equalsIgnoreCase(games.currentTask)){   //checking if the message sent by player is the same as the answer
            e.getPlayer().getInventory().addItem(reward);
            Bukkit.broadcastMessage(ChatColor.AQUA + e.getPlayer().getName() + " Has answered the question!");

            games.currentTask = null; //setting the answer to null
            Bukkit.getScheduler().cancelTask(games.task); //cancelling the runnable to set answer to null periodically
        }

    }
}
