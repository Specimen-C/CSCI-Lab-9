import java.util.*;

public class TransferStation extends Station {

    protected ArrayList<Station> otherStations;

    public TransferStation(String line, String name) {
        super(line, name);
        otherStations = new ArrayList<Station>();
    }

    public String toString() {
        String transferString = "\n\tTransfers: \n";
        if(otherStations.size() > 0) {
            Iterator<Station> iter = otherStations.iterator(); 
            while (iter.hasNext()) { 
                transferString += "\t" + iter.next().toString() + "\n";
            }
        }

        return "TRANSFER" + super.toString() + transferString;
    }

    public void addTransferStationPrev(Station station) {
        otherStations.add(station);
        station.previous = this;
    }

    public void addTransferStationNext(Station station) {
        otherStations.add(station);
        station.next = this;
    }

}