package opmpackets.protocol.valueproviders.floats;

import opmpackets.protocol.mapper.MappedEntity;
import opmpackets.protocol.util.NbtCodec;
import opmpackets.protocol.util.NbtCodecs;
import opmpackets.protocol.util.NbtMapCodec;
import org.jspecify.annotations.NullMarked;

/**
 * @versions 1.17+
 */
@NullMarked
public interface FloatProviderType<T extends FloatProvider> extends MappedEntity {

    NbtCodec<FloatProviderType<?>> CODEC = NbtCodecs.forRegistry(FloatProviderTypes.getRegistry());

    NbtMapCodec<T> getCodec();
}
