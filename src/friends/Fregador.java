package friends;

import plateUtils.Plate;
import plateUtils.PlateTray;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class Fregador extends Friends{

    public Fregador(PlateTray plateTray) {
        super(plateTray);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            // Primero cogemos un plato de la pila (supuestamente infinita)
            // de platos sucios
            Plate plate = takePlate();

            // Ahora ceniciento lo limpia.
            try {
                TimeUnit.SECONDS.sleep(time.nextInt(4) + 4);

                // Tenemos el plato limpio, coloquémoslo en la bandeja.
                putPlate(plate);

            } catch (InterruptedException e) {
                System.out.println("(Ceniciento - Fregador) Voy!!!!!");
                return;
            }
        }
    }

    @Override
    public Plate takePlate() {
        return new Plate();
    }

    @Override
    public void putPlate(Plate plate) {
        System.out.printf("%s: %s ha fregado el plato con serie %s\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                plate.getSerial());

        plateTray.putPlate(plate);
    }
}
