package friends;

import plateUtils.Plate;
import plateUtils.PlateTray;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class Organizador extends Friends{

    public Organizador(PlateTray plateTray) {
        super(plateTray);
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {

            try {
                // Primero cogemos un plato de la pila de platos Secos
                Plate plate = takePlate();

                // Ahora ceniciento lo limpia.
                TimeUnit.SECONDS.sleep(time.nextInt(2) + 1);

                // Tenemos el plato limpio, coloquémoslo en la bandeja.
                putPlate(plate);

            } catch (InterruptedException e) {
                System.out.println("(RamoncitoKebab - Organizador) 'Enga, voy'!");
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
        System.out.printf("%s: %s ha guardado el plato con serie %s en la alacena\n",
                LocalTime.now().format(dateTimeFormatter),
                Thread.currentThread().getName(),
                plate.getSerial());
    }
}
