package krekks.easyparkour.misc.placeholder;

import krekks.easyparkour.KEP;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

public class TestPlaceHolder extends PlaceholderExpansion {

    private final KEP plugin;

    public TestPlaceHolder(KEP plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getIdentifier() {
        return "kepleaderboard";
    }

    @Override
    public @NotNull String getAuthor() {
        return "Krekkers";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        //look into making this work for a list

        return null; // Placeholder is unknown by the expansion
    }

}