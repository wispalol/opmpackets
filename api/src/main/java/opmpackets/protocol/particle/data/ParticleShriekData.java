package opmpackets.protocol.particle.data;

import opmpackets.protocol.nbt.NBTCompound;
import opmpackets.protocol.nbt.NBTInt;
import opmpackets.protocol.player.ClientVersion;
import opmpackets.wrapper.PacketWrapper;
import org.jspecify.annotations.NullMarked;

import java.util.Objects;

@NullMarked
public class ParticleShriekData extends ParticleData {

    private int delay;

    public ParticleShriekData(int delay) {
        this.delay = delay;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public static ParticleShriekData read(PacketWrapper<?> wrapper) {
        return new ParticleShriekData(wrapper.readVarInt());
    }

    public static void write(PacketWrapper<?> wrapper, ParticleShriekData data) {
        wrapper.writeVarInt(data.getDelay());
    }

    public static ParticleShriekData decode(NBTCompound compound, ClientVersion version) {
        int delay = compound.getNumberTagOrThrow("delay").getAsInt();
        return new ParticleShriekData(delay);
    }

    public static void encode(ParticleShriekData data, ClientVersion version, NBTCompound compound) {
        compound.setTag("delay", new NBTInt(data.delay));
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) return false;
        ParticleShriekData that = (ParticleShriekData) obj;
        return this.delay == that.delay;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.delay);
    }
}
