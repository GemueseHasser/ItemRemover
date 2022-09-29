package de.jonas;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * <p>Die Haupt- und Main-Klasse dieses Plugins, durch die das gesamte Plugin initialisiert wird. Der
 * {@link ItemRemover} ist die Klasse, in der alle Unterinstanzen registriert werden.</p>
 *
 * <p>Der {@link ItemRemover} entfernt periodisch alle Items, die in in der Config aufgeführten Welten herumliegen.</p>
 */
public final class ItemRemover extends JavaPlugin {

    //<editor-fold desc="STATIC FIELDS">
    /** Die Instanz, womit man auf dieses Plugin zugreifen kann. */
    @Getter
    private static ItemRemover instance;
    //</editor-fold>


    //<editor-fold desc="setup and start">
    @Override
    public void onEnable() {
        super.onEnable();

        // initialize plugin instance
        instance = this;

        // load config
        getConfig().options().copyDefaults(true);
        saveConfig();

        getLogger().info("Das Plugin wurde erfolgreich aktiviert!");
    }
    //</editor-fold>

    //<editor-fold desc="shutdown">
    @Override
    public void onDisable() {
        super.onDisable();

        getLogger().info("Das Plugin wurde deaktiviert.");
    }
    //</editor-fold>
}
