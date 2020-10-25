package plateUtils;

public class DryPlateTray extends PlateTray{

}

/*
*     private final List<Plate> cleanPlates = new ArrayList<>();

    private synchronized Plate takePlate() throws InterruptedException {
        while(cleanPlates.size() == 0) {
            wait();
        }

        Plate plate =  cleanPlates.remove(0);

        return plate;
    }

    private synchronized void putPlate(Plate plate) {
        Objects.requireNonNull(plate);
        cleanPlates.add(plate);
    }
* */