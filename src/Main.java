import friends.Fregador;
import friends.Friends;
import friends.Organizador;
import friends.Secador;
import plateUtils.CleanPlateTray;
import plateUtils.DryPlateTray;
import plateUtils.Plate;
import plateUtils.PlateTray;

import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    /**
     * Simula a los amigos que van a limpiar, secar y organizar los platos.
     */
    Thread friendThreads[] = new Thread[3];

    /**
     * Simula las bandejas en las que se almacenan los platos
     */
    PlateTray cleanPlateTray = new CleanPlateTray();
    PlateTray dryPlateTray = new DryPlateTray();

    /*
        Formateador de fecha
     */
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /*
        Array para recorrer los amigos en caso de necesitarlo
     */
    Friends[] friends = new Friends[3];

    /*
        Nombre de los hilos
     */
    String[] names = new String[3];

    Main() throws InterruptedException {
        // Nombre de los hilos
        names[0] = "Fregador";
        names[1] = "Secador";
        names[2] = "Organizador";

        // Inicializamos a los amigos y les pasamos las bandejas necesarias
        friends[0] = new Fregador(cleanPlateTray);
        friends[1] = new Secador(cleanPlateTray, dryPlateTray);
        friends[2] = new Organizador(dryPlateTray);

        // Creamos los hilos
        for(int i = 0; i < friends.length; i++) {
            friendThreads[i] = new Thread(friends[i], names[i]);
        }

        // Iniciamos los hilos
        for(int i = 0; i < friends.length; i++) {
            friendThreads[i].start();
        }

        // Dormimos la siesta
        TimeUnit.SECONDS.sleep(20);

        System.out.println("(Cumpleañero) ¡Chavales, que vamos a soplar las velas, id terminando!");

        // Vamos interrumpiendo a los hilos
        for(int i = 0; i < friends.length; i++) {
            friendThreads[i].interrupt();
        }

        // Tiempo de cortesía para los hilos
        // Realmente esta parte no es necesaria, pero quiero
        // asegurarme de que los hilos puedan responder
        // adecuadamente antes que el hilo principal,
        // cuando son interrumpidos.
        for(int i = 0; i < friends.length; i++) {
            friendThreads[i].join();
        }

        // Saludamos
        System.out.println("Todos: Cumpleaños feliz");

    }

    public static void main(String[] args) throws InterruptedException {
        new Main();
    }
}
