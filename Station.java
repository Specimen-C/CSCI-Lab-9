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
        next = station;
        station.previous = this;
    }

    public void connect(TransferStation station) {
        next = station;
        if(line.equals(station.line)) {
            station.previous = this;
        }

        //station.addTransferStationPrev(this);
    }

    public void connect(EndStation station) {
        next = station;
        station.previous = this;
        station.next = this;
    }


    public String getName() {
        return name;
    }

}