package com.provismet.datagen.AdditionalArmoury;

/*
 * Copyright (C) 2024 Provismet
 *
 * See https://github.com/Provismet/LilyLib/blob/1.21/LICENSE for the full license.
 */

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public abstract class LilySoundProvider implements DataProvider {
    protected final FabricDataOutput output;
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    protected LilySoundProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        this.output = output;
        this.registryLookup = registryLookup;
    }

    protected abstract void generateSoundFile (SoundWriter writer);

    @Override
    public CompletableFuture<?> run (DataWriter writer) {
        final TreeMap<String, SoundEntry> contents = new TreeMap<>();

        return this.registryLookup.thenCompose(lookup -> {
            this.generateSoundFile(new SoundWriter(contents));

            HashMap<String, JsonObject> cachedObjects = new HashMap<>();
            final List<CompletableFuture<?>> futures = new ArrayList<>();
            for (Map.Entry<String, SoundEntry> entry : contents.entrySet()) {
                Identifier id = Identifier.of(entry.getKey());
                JsonObject json;

                if (cachedObjects.containsKey(id.getNamespace())) {
                    json = cachedObjects.get(id.getNamespace());
                }
                else {
                    json = new JsonObject();
                    cachedObjects.put(id.getNamespace(), json);
                }

                JsonObject subObject = new JsonObject();
                subObject.addProperty("subtitle", entry.getValue().subtitle());

                JsonArray soundsFiles = new JsonArray();
                for (Identifier identifier : entry.getValue().soundFiles()) {
                    soundsFiles.add(identifier.toString());
                }
                subObject.add("sounds", soundsFiles);
                json.add(id.getPath(), subObject);
            }

            for (Map.Entry<String, JsonObject> entry : cachedObjects.entrySet()) {
                futures.add(DataProvider.writeToPath(writer, entry.getValue(), this.getFilepath(entry.getKey())));
            }
            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    private Path getFilepath (String namespace) {
        return this.output.resolvePath(DataOutput.OutputType.RESOURCE_PACK).resolve(namespace).resolve("sounds.json");
    }

    @Override
    public String getName () {
        return "Sound";
    }

    protected static final class SoundWriter {
        private final TreeMap<String, SoundEntry> contents;

        private SoundWriter (TreeMap<String, SoundEntry> contentTree) {
            this.contents = contentTree;
        }

        public void add (Identifier identifier, String subtitle, Identifier... soundFiles) {
            Objects.requireNonNull(identifier);
            Objects.requireNonNull(subtitle);
            Objects.requireNonNull(soundFiles);

            if (this.contents.containsKey(identifier.toString())) {
                throw new RuntimeException("Existing sound found - " + identifier + " - Duplicate will be ignored.");
            }

            this.contents.put(identifier.toString(), new SoundEntry(subtitle, soundFiles));
        }

        public void add (Identifier identifier, String subtitle, List<Identifier> soundFiles) {
            Objects.requireNonNull(soundFiles);
            this.add(identifier, subtitle, soundFiles.toArray(new Identifier[0]));
        }

        public void add (SoundEvent sound, String subtitle, Identifier... soundFiles) {
            Objects.requireNonNull(sound);
            this.add(sound.getId(), subtitle, soundFiles);
        }

        public void add (SoundEvent sound, String subtitle, List<Identifier> soundFiles) {
            Objects.requireNonNull(soundFiles);
            this.add(sound, subtitle, soundFiles.toArray(new Identifier[0]));
        }
    }

    private record SoundEntry (String subtitle, Identifier... soundFiles) {

    }
}
