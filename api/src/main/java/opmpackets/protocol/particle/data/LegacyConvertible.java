package opmpackets.protocol.particle.data;

import opmpackets.protocol.player.ClientVersion;

public interface LegacyConvertible {

    LegacyParticleData toLegacy(ClientVersion version);

}
