package ru.nern.antishadowpatch;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Stream;

import static ru.nern.antishadowpatch.AntiShadowPatch.config;

public class ConfigurationManager
{

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
        public boolean bringBackStackOverflowSuppression = true;
        public boolean bringBackCCESuppression = true;
        public boolean bringBack1_17ItemShadowing = true;
        public boolean bringBack1_18ItemShadowing = true;
        public boolean bringBackInfiniteFurnaceXPBug = true;
        public boolean bringBackFloatingRedstoneComponentsOnTopOfTrapdoor = true;
        public boolean bringBackTrapdoorUpdateSkipping = true;
        public boolean bringBackOldDragonFreezing = true;
        public boolean bringBackChunkSaveState = false;

    }
}
