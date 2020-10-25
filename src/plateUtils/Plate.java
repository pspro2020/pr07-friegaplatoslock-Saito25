package plateUtils;

public class Plate {

    private final int serial;
    private static int counterSerial = 0;

    public Plate() {
        serial = ++counterSerial;
    }

    public int getSerial() {
        return serial;
    }
}
