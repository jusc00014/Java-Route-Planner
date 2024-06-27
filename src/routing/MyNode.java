package routing;

import java.util.Iterator;

public class MyNode implements Node {

    long id;
    double x;
    double y;
    Edge incomming;
    Edge outgoing;


    @Override
    public Coordinate getCoordinate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCoordinate'");
    }

    @Override
    public Edge getEdge(int idx) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEdge'");
    }

    @Override
    public long getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

    @Override
    public Iterator<Edge> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public int numEdges() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numEdges'");
    }

    @Override
    public void addEdge(Edge e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addEdge'");
    }

    @Override
    public void removeEdge(int i) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeEdge'");
    }
    
}
