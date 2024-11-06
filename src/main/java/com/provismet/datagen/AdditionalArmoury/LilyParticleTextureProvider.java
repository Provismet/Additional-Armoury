package com.provismet.datagen.AdditionalArmoury;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.DataOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.DataWriter;
import net.minecraft.particle.ParticleType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

public abstract class LilyParticleTextureProvider implements DataProvider {
    protected final FabricDataOutput output;
    private final DataOutput.PathResolver pathResolver;
    private final CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup;

    protected LilyParticleTextureProvider (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        this.output = output;
        this.pathResolver = output.getResolver(DataOutput.OutputType.RESOURCE_PACK, "particles");
        this.registryLookup = registryLookup;
    }

    protected abstract void generate (RegistryWrapper.WrapperLookup registryLookup, ParticleWriter writer);

    @Override
    public CompletableFuture<?> run (DataWriter writer) {
        return this.registryLookup.thenCompose(lookup -> {
            List<ParticleEntry> particleEntries = new ArrayList<>();
            ParticleWriter particleWriter = new ParticleWriter(particleEntries);

            this.generate(lookup, particleWriter);

            final List<CompletableFuture<?>> futures = new ArrayList<>();
            for (ParticleEntry entry : particleEntries) {
                JsonObject json = new JsonObject();
                JsonArray textures = new JsonArray();
                for (Identifier texture : entry.textures) {
                    textures.add(texture.toString());
                }
                json.add("textures", textures);
                futures.add(DataProvider.writeToPath(writer, json, this.getPath(entry.identifier())));
            }

            return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
        });
    }

    private Path getPath (Identifier soundId) {
        return this.pathResolver.resolveJson(soundId);
    }

    @Override
    public String getName () {
        return "ParticleTexture";
    }

    protected static final class ParticleWriter {
        private final List<ParticleEntry> entries;

        private ParticleWriter (List<ParticleEntry> entries) {
            this.entries = entries;
        }

        public void add (Identifier particle, Identifier... textures) {
            Objects.requireNonNull(particle);
            Objects.requireNonNull(textures);
            this.entries.add(new ParticleEntry(particle, textures));
        }

        public void add (Identifier particle, List<Identifier> textures) {
            Objects.requireNonNull(textures);
            this.add(particle, textures.toArray(Identifier[]::new));
        }

        public void add (ParticleType<?> particle, Identifier... textures) {
            this.add(Registries.PARTICLE_TYPE.getId(particle), textures);
        }

        public void add (ParticleType<?> particle, List<Identifier> textures) {
            this.add(Registries.PARTICLE_TYPE.getId(particle), textures);
        }
    }

    private record ParticleEntry (Identifier identifier, Identifier... textures) {

    }
}
