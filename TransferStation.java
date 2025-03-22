import java.util.*;

public class TransferStation extends Station {

    protected ArrayList<Station> transfers;

    public TransferStation(String line, String name) {
        super(line, name);
        transfers = new ArrayList<Station>();
    }

    public String toString() {
        String transferString = "\n\tTransfers: \n";
        if(transfers.size() > 0) {
            Iterator<Station> iter = transfers.iterator(); 
            while (iter.hasNext()) { 
                transferString += "\t" + iter.next().toString() + "\n";
            }
        }

        return "TRANSFER" + super.toString() + transferString;
    }

    public void addTransferStationPrev(Station station) {
        transfers.add(station);
        station.nextStation = this;
    }

    public void addTransferStationNext(Station station) {
        transfers.add(station);
        station.previousStation = this;
    }

}