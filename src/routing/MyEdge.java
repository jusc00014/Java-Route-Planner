package routing;

public class MyEdge implements Edge {

    Node s;
    Node d;
    boolean scd;
    boolean dcs;
    boolean sbd;
    boolean dbs;
    boolean sfd;
    boolean dfs;
    double length;

    

    @Override
    public boolean allowsTravelType(TravelType tt, Direction dir) {
        if (tt == TravelType.BIKE) {
            if (dir == Direction.FORWARD) {
                return(sbd);
            } else if (dir == Direction.BACKWARD) {
                return(dbs);
            } else if (dir == Direction.ANY) {
                return(sbd || dbs);
            } else {
                throw new IllegalArgumentException("Invalid direction");
            }
        } else if (tt == TravelType.CAR) {
            if (dir == Direction.FORWARD) {
                return(scd);
            } else if (dir == Direction.BACKWARD) {
                return(dcs);
            } else if (dir == Direction.ANY) {
                return(scd || dcs);
            } else {
                throw new IllegalArgumentException("Invalid direction");
            }
        } else if (tt == TravelType.FOOT) {
            if (dir == Direction.FORWARD) {
                return(sfd);
            } else if (dir == Direction.BACKWARD) {
                return(dfs);
            } else if (dir == Direction.ANY) {
                return(sfd || dfs);
            } else {
                throw new IllegalArgumentException("Invalid direction");
            }
        } else if (tt == TravelType.ANY) {

            if (dir == Direction.FORWARD) {
                return(sbd || scd || sfd);
            } else if (dir == Direction.BACKWARD) {
                return(dbs || dfs || dfs);
            } else if (dir == Direction.ANY) {
                return(sbd || scd || sfd || dbs || dfs || dfs);
            } else {
                throw new IllegalArgumentException("Invalid direction");
            }
        } else {
            throw new IllegalArgumentException("Invaild travel type");
        }
    }

    @Override
    public Node getEnd() {
        return(this.d);
    }

    @Override
    public double getLength() {
        return(this.length);
    }

    @Override
    public Node getStart() {
        return(this.s);
    }
    
}
