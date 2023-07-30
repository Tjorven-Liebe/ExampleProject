package de.tjorven.messages;

import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MessageHandler {

    private final YamlConfiguration configuration;
    public final String prefix;
    public final String noperm;

    public MessageHandler(String file, String resource) {
        File file1 = new File(file);
        this.configuration = YamlConfiguration.loadConfiguration(file1);
        if (!file1.exists()) {
            file1.getParentFile().mkdirs();
            try {
                file1.createNewFile();
                configuration.load(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(resource)));
                configuration.save(file1);
            } catch (IOException | InvalidConfigurationException e) {
                throw new RuntimeException(e);
            }
        }
        this.prefix = configuration.getString("prefix.prefix");
        this.noperm = configuration.getString("prefix.noperm");
    }

    public String getMessage(String key) {
        return ChatColor.translateAlternateColorCodes('&', prefix + configuration.getString(key));
    }
}
