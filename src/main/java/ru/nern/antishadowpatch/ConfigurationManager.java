package ru.nern.antishadowpatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Objects;
import java.util.stream.Stream;

import static ru.nern.antishadowpatch.AntiShadowPatch.config;

public class ConfigurationManager
{
    private static final String CONFIG_VERSION = FabricLoader.getInstance().getModContainer("antishadowpatch").get().getMetadata().getVersion().getFriendlyString();
    public static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private static final File file = new File(FabricLoader.getInstance().getConfigDir().toFile(), "antishadowpatch_config.json");

    public static void loadConfig() {
        try {
            if (file.exists()) {
                StringBuilder contentBuilder = new StringBuilder();
                try (Stream<String> stream = Files.lines(file.toPath(), StandardCharsets.UTF_8)) {
                    stream.forEach(s -> contentBuilder.append(s).append("\n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                config = gson.fromJson(contentBuilder.toString(), Config.class);
            } else {
                config = new Config();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        setConfig(config);
    }

    public static void saveConfig() {
        config.lastLoadedVersion = CONFIG_VERSION;
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(gson.toJson(getConfig()));
            fileWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onInit()
    {
        if(!file.exists())
        {
            saveConfig();
        }else{
            loadConfig();
            if(!Objects.equals(config.lastLoadedVersion, CONFIG_VERSION)) saveConfig();
        }
    }

    public static void setConfig(Config config) {
        AntiShadowPatch.config = config;
    }

    public static Config getConfig() {
        return config;
    }

    public static class Config
    {
        private String lastLoadedVersion = "";
        public Blocks blocks;
        public Entities entities;
        public Items items;

        public static class Blocks{
            public boolean bringBackStackOverflowSuppression = true;
            public boolean gracefulStackOverflowHandling = true;
            public boolean bringBackCCESuppression = true;
            public boolean bringBackTrapdoorUpdateSkipping = true;
            public boolean bringBackChunkSaveState = false;
            public boolean bringBackFloatingRedstoneComponentsOnTopOfTrapdoor = true;
        }

        public static class Entities{
            public boolean bringBackOldDragonFreezing = true;
            public boolean bringBackInfiniteFurnaceXPBug = true;
        }

        public static class Items{
            public boolean bringBack1_17ItemShadowing = true;
            public boolean bringBack1_18ItemShadowing = true;
        }

        public Config()
        {
            blocks = new Blocks();
            entities = new Entities();
            items = new Items();
        }

    }
}
