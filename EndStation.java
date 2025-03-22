public class EndStation extends Station{

    public EndStation(String line, String name) {
        super(line, name);
    }

    public String toString() {
        return "END" + super.toString();
    }

    public void makeEnd() {
        if(previousStation != null) {
            //System.out.println("Previous Station is Null");
            nextStation = previousStation;
        }
        else if(nextStation != null) {
            previousStation = nextStation;
        }
    }

}