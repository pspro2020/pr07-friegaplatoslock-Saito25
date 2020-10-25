package friends;

import plateUtils.Plate;
import plateUtils.PlateTray;

import java.time.format.DateTimeFormatter;
import java.util.Random;

public abstract class Friends implements Runnable {

    /**
     * Almacena la bandeja que contiene los platos.
     */
    protected final PlateTray plateTray;

    /*
        Nos servirá para generar el tiempo de espera
     */
    protected final Random time = new Random();

    /*
        Genera un objeto que formatea la hora en el formato:
        HORA:MINUTOS:SEGUNDOS
     */
    protected final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    protected Friends(PlateTray plateTray) {
        this.plateTray = plateTray;
    }

    /**
     * Take a plate from somewhere specified by the subclass
     *
     * @return a plate
     */
    protected abstract Plate takePlate() throws InterruptedException;

    /**
     * Put a plate into a plate tray, it is specified in the PlateTray field from class
     */
    protected abstract void putPlate(Plate plate);
}
