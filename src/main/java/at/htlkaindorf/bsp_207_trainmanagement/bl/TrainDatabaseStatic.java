package at.htlkaindorf.bsp_207_trainmanagement.bl;

import lombok.Data;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Data
public class TrainDatabaseStatic {
    private static TrainDatabaseStatic theInstance;
    private List<Train> trains;

    private TrainDatabaseStatic() {

        trains = new ArrayList<>();
        trains.add(new Train(0, new ArrayList<String>(Arrays.asList("Kaindorf", "Lebring", "Wildon", "Werndorf", "Feldkirchen", "Don Bosco", "Graz")), "S-Bahn"));
        trains.add(new Train(1, new ArrayList<String>(Arrays.asList("Kaindorf", "Don Bosco", "Graz")), "REX"));
        trains.add(new Train(2, new ArrayList<String>(Arrays.asList("Leibnitz", "Graz")), "Intercity"));
    }

    public static synchronized TrainDatabaseStatic getInstance() {
        if (theInstance == null) {
            theInstance = new TrainDatabaseStatic();
        }
        return theInstance;
    }

    public void addStation(int id, String station) throws NoSuchElementException {
        exists(id).addStation(station);
    }

    public void addTrain(Train train) throws KeyAlreadyExistsException {
        if (trains.stream().filter(trains -> trains.getId() == train.getId()).collect(Collectors.toList()).size() == 0) {
            trains.add(train);
        } else {
            throw new KeyAlreadyExistsException();
        }
    }

    private Train exists(int id) throws NoSuchElementException {
        if (trains.stream().filter(train -> train.getId() == id).collect(Collectors.toList()).size() == 1) {
            return trains.stream().filter(train -> train.getId() == id).collect(Collectors.toList()).get(0);
        }
        throw new NoSuchElementException();
    }

    public Train getById(int id) throws NoSuchElementException {
        return exists(id);
    }
}
