package routing;

public class MyEdge implements Edge {

    long s;
    long d;
    boolean scd;
    boolean dcs;
    boolean sbd;
    boolean dbs;
    boolean sfd;
    boolean dfs;
    double length;

    

    @Override
    public boolean allowsTravelType(TravelType tt, Direction dir) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'allowsTravelType'");
    }

    @Override
    public Node getEnd() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEnd'");
    }

    @Override
    public double getLength() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getLength'");
    }

    @Override
    public Node getStart() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStart'");
    }
    
}
