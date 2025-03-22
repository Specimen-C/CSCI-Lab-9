public class Station{


    protected String name; 
    protected String line;
    protected boolean inService;
    protected Station nextStation;
    protected Station previousStation;

    public Station(String line, String name) {
        this.line = line;
        this.name = name;
        this.inService = true;
        this.nextStation = null;
        this.previousStation = null;
    }   

    public String toString() {
        String connections = "";
        if(previousStation == null) {
            //System.out.println("Previous Station is Null");
            connections += "previous station: none";
        }
        else {
            connections += "previous station: " + previousStation.name;
        }
        if(nextStation == null) {
            //System.out.println("Next Station is Null");
            connections += ", next station: none";
        }
        else {
            connections += ", next station: " + nextStation.name;
        }
        
        return "STATION " + name + ": " + line + " line, in service: " + inService + ", " + connections;
        
        
    }

    public boolean equals(Station s) {
        return line.equals(s.line) && name.equals(s.name);
    }

    public void addNext(Station station) {
        nextStation = station;
        station.previousStation = this;
        //System.out.println("Next Station  = " + nextStation.toString());
    }

    public void addPrev(Station station) {
        previousStation = station;
        station.nextStation = this;
    }

    public void switchAvailable() {
        inService = !inService;
    }

    public boolean isAvailable() {
        return inService;
    }

    


}