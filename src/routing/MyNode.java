package routing;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyNode implements Node {

    public long id;
    public double lati;
    public double longi;
    public Coordinate c;
    public List<Edge> eout;
    public List<Edge> ein;


    @Override
    public Coordinate getCoordinate() {
        return(this.c);
    }

    @Override
    public Edge getEdge(int idx) {
        int k = 0;
        for (Edge e : eout) {
            if (k == idx){
                return(e);
            }
            k++;
        }
        throw new IllegalArgumentException("This index is not valid");
    }

    @Override
    public long getId() {
        return(this.id);
    }

    @Override 
    //Written with AI
    public Iterator<Edge> iterator() {
        return new EdgeIterator(this.eout);
    }

    public class EdgeIterator implements Iterator<Edge> {
        private Iterator<Edge> edgeIterator;
        public EdgeIterator (List<Edge> edges) {
            this.edgeIterator = edges.iterator();
        }
        @Override
        public boolean hasNext() {
            return edgeIterator.hasNext();
        }
        @Override
        public Edge next() {
            return edgeIterator.next();
        }
        @Override
        public void remove() {
            edgeIterator.remove();
            return;
        }
    }

    @Override
    public int numEdges() {
        return(this.eout.size());
    }

    @Override
    public void addEdge(Edge e) {
        if (e.getStart() == this) {
            if (e.allowsTravelType(TravelType.ANY, Direction.FORWARD)) {
                this.eout.add(e);
            }
            if (e.allowsTravelType(TravelType.ANY, Direction.BACKWARD)) {
                this.ein.add(e);
            }
        }
        if (e.getEnd() == this) {
            if (e.allowsTravelType(TravelType.ANY, Direction.BACKWARD)) {
                this.eout.add(e);
            }
            if (e.allowsTravelType(TravelType.ANY, Direction.FORWARD)) {
                this.ein.add(e);
            }
        }
        return;
    }

    @Override
    public void removeEdge(int i) {
        Edge e = eout.get(i);
        MyNode k;
        if (e.getStart() == this) {
            k = (MyNode) e.getEnd();
        } else {
            k = (MyNode) e.getStart();
        }
        k.ein.remove(i);
        eout.remove(i);
        int h = 0;
        for (Edge f : k.eout) {
            if (f == e) {
                k.eout.remove(h);
            } else {
                h++;
            }
        }
        return;
    }
    
}
