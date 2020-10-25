package plateUtils;

public class CleanPlateTray extends PlateTray{

}

/*
*
    private final List<Plate> plates = new ArrayList<>();

    private synchronized Plate takePlate() throws InterruptedException {
        while(plates.size() == 0) {
            wait();
        }

        Plate plate =  plates.remove(0);

        return plate;
    }

    private synchronized void putPlate(Plate plate) {
        Objects.requireNonNull(plate);
        plates.add(plate);
    }
* */