package opmpackets.protocol.mapper;

import opmpackets.util.mappings.TypesBuilderData;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public interface MappedEntityBuilder<T extends MappedEntity> {

    default T build() {
        return this.build(null);
    }

    @ApiStatus.Internal
    T build(@Nullable TypesBuilderData data);
}
