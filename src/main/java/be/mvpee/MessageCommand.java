package be.mvpee;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.ChatColor;

public class MessageCommand implements CommandExecutor {

    //  /message <player> <message>

    private Main main;

    public MessageCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length >= 2) {
                if (Bukkit.getPlayerExact(args[0]) != null) {
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++) {
                        builder.append(args[i]).append(" ");
                    }

                    player.sendMessage(
                            ChatColor.GREEN + "You -> " +
                            ChatColor.RED + target.getName() + ": " +
                            ChatColor.WHITE + builder
                    );

                    target.sendMessage(
                            ChatColor.RED + player.getName() + " -> " +
                            ChatColor.GREEN + "You: " +
                            ChatColor.WHITE + builder
                    );

                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());
                } else {
                    player.sendMessage(ChatColor.RED + "This player is not online.");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Usage: /message <player> <message>");
            }
        }
        return false;
    }

}
