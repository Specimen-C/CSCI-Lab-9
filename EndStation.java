public class EndStation extends Station{

    public EndStation(String line, String name) {
        super(line, name);
    }

    public String toString() {
        return "END" + super.toString();
    }

    public void makeEnd() {
        if(previous != null) {
            //System.out.println("Previous Station is Null");
            next = previous;
        }
        else if(next != null) {
            previous = next;
        }
    }

}