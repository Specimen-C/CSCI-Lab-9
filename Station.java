import java.util.Iterator;


public class Station{

    protected String name; 
    protected String line;
    protected boolean inService;
    public Station next;
    protected Station previous;

    public Station(String line, String name) {
        this.line = line;
        this.name = name;
        this.inService = true;
        this.next = null;
        this.next = null;
    }   

    public String toString() {
        String connections = "";
        if(previous == null) {
            //System.out.println("Previous Station is Null");
            connections += "previous station: none";
        }
        else {
            connections += "previous station: " + previous.name;
        }
        if(next == null) {
            //System.out.println("Next Station is Null");
            connections += ", next station: none";
        }
        else {
            connections += ", next station: " + next.name;
        }
        
        return "STATION " + name + ": " + line + " line, in service: " + inService + ", " + connections;
        
        
    }

    public boolean equals(Station s) {
        //System.out.println("Comparing " + this.toString() + " and " + s.toString());
        return line.equals(s.line) && name.equals(s.name);
    }

    public void addNext(Station station) {
        next = station;
        station.previous = this;
        //System.out.println("Next Station  = " + next.toString());
    }

    public void addPrev(Station station) {
        previous = station;
        station.next = this;
    }

    public void switchAvailable() {
        inService = !inService;
    }

    public boolean isAvailable() {
        return inService;
    }

    public void connect(Station station) {
        this.next = station;
        station.previous = this;
    }

    
    public void connect(TransferStation station) {
        //System.out.println("Station line = " + station.line + "\nThis.line = " + line);
        //Terrible solution but IDK how else to do without hardcoding
        
        if(!line.equals("orange")) {
            //System.out.println("They are not equal");
            station.addTransferStationPrev(this);
            this.next = station;
        }
        else {
            //System.out.println("They are  equal");
            this.next = station;
            station.previous = this;
        }
        /*
        station.addTransferStationPrev(this);
        this.next = station;
        station.previous = this;
        */
    }

    public int tripLength(Station station) {
        return tripLengthHelper(station, this, 0);
    }


    public int tripLengthHelper(Station target, TransferStation current, int counter) {
        System.out.println("HelperTRANSFER(" + target.toString() + " | " + current.toString() + " | " + counter + ")");
        if(current.equals(target)) { 
            return counter;
        }
        //System.out.println("HELLO WHAT THE FUCK");
        int[] offshootLengths = new int[current.otherStations.size() + 1];
        int iter = 0;
        for(Station station : current.otherStations) {
            System.out.println("iter = " + iter + "Station = " + station.toString());
            if(station.equals(target)) {
                return counter + 1;
            }
            if(!station.next.equals(current) || (station instanceof EndStation)) {
                System.out.println("Off Shoot going to " + station.toString());
                offshootLengths[iter] = tripLengthHelper(target, station, counter + 1);
            }

            iter++;
        }
        offshootLengths[offshootLengths.length - 1] = tripLengthHelper(target, current.next, counter + 1);
        for(int i = 0; i < offshootLengths.length; i++) {
            System.out.println("offshootLengths[" + i + "] = " + offshootLengths[i]);
            if(offshootLengths[i] > 0) {
                System.out.println("\n----------------------------------------------------------------------------------------------\n");
                return offshootLengths[i];  
            }
        }

        return -5;
    }


    public int tripLengthHelper(Station target, Station current, int counter) {
        
        if(current instanceof TransferStation) {
            return tripLengthHelper(target, (TransferStation) current, counter);
        }
        else {
            System.out.println("HelperSTATION(" + target.toString() + " | " + current.toString() + " | " + counter + ")");
            if(current.equals(target)) { 
                return counter;
            }
            else if(current instanceof EndStation && counter != 0) {
                return 0;
            }
            return tripLengthHelper(target, current.next, counter + 1);
        }
    }


    public String getName() {
        return name;
    }

}