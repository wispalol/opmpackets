package opmpackets.exception;

public class CancelPacketException extends RuntimeException {
    public static final CancelPacketException INSTANCE = new CancelPacketException();

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
