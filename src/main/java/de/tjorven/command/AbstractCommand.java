package de.tjorven.command;

import de.tjorven.messages.MessageHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.checkerframework.checker.units.qual.A;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;

public abstract class AbstractCommand extends MessageHandler implements TabExecutor {

    private final ConsoleCommandSender console = Bukkit.getConsoleSender();
    private @Nullable Player player;
    private String[] args;
    private String label;
    private CommandSender sender;
    private Command command;

    public AbstractCommand() {
        super("plugins/pluginname/messages.yml", "messages.yml");
    }

    abstract boolean command();

    abstract List<String> tab();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) this.player = player;
        this.args = args;
        this.label = label;
        this.sender = sender;
        this.command = command;
        return command();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player player) this.player = player;
        this.args = args;
        this.label = label;
        this.sender = sender;
        this.command = command;
        return tab();
    }

    @Nullable
    public Player getPlayer() {
        return player;
    }

    public String[] getArgs() {
        return args;
    }

    public String getLabel() {
        return label;
    }

    public CommandSender getSender() {
        return sender;
    }

    public Command getCommand() {
        return command;
    }

    public ConsoleCommandSender getConsole() {
        return console;
    }
}
