package friends;

import plateUtils.Plate;
import plateUtils.PlateTray;

import java.time.LocalTime;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Secador extends Friends {

    private PlateTray anotherPlateTray;

    private Secador(PlateTray plateTray) {
        super(plateTray);
    }

    public Secador(PlateTray plateTray, PlateTray anotherPlateTray) {
        this(plateTray);
        Objects.requireNonNull(anotherPlateTray);
        this.anotherPlateTray = anotherPlateTray;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            try {
                // Primero cogemos un plato de la pila de platos limpios pero mojados
                Plate plate = takePlate();

                // Ahora ceniciento lo limpia.
                TimeUnit.SECONDS.sleep(time.nextInt(3) + 1);

                // Tenemos el plato limpio, coloquémoslo en la bandeja.
                putPlate(plate);

            } catch (InterruptedException e) {
                System.out.println("(JuanitoPalote - Secador) Valep!!!");
                return;
            }
        }
    }

    @Override
    public Plate takePlate() throws InterruptedException {
        return plateTray.takePlate();
    }

    @Override
    public void putPlate(Plate plate) {
        System.out.printf("%s: %s ha secado el plato con serie %s\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                plate.getSerial());

        anotherPlateTray.putPlate(plate);
    }
}
