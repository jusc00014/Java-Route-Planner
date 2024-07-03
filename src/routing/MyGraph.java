package routing;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyGraph implements Graph {

	//public List<Node> kn;
    public Map<Long, Node> kn;
	public Map<Long, List<Edge>> Edgesin;
	public Map<Long, List<Edge>> Edgesout;
	public Map<Long, Coordinate> coo;
    public double mx;
    public double Mx;
    public double my;
    public double My;
    public int nume = 0;
    public List<Long> idList;
    
    @Override
    public Node getNode(long id) {
        return(kn.get(id));
    }

    @Override
    public Coordinate getNWCoordinate() {
        Coordinate nw = new Coordinate(Mx, My);
        return(nw);
    }

    @Override
    public Coordinate getSECoordinate() {
        Coordinate se = new Coordinate(mx, my);
        return(se);
    }

    //Written with AI
    @Override
    public Iterator<Node> iterator() {
        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Node> {
        private final Iterator<Map.Entry<Long, Node>> iter;

        public NodeIterator() {
            this.iter = kn.entrySet().iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public Node next() {
            return iter.next().getValue();
        }

        @Override
        public void remove() {
            iter.remove();
            return;
        }
    }

    @Override
    public int numEdges() {
        return(nume);
    }

    @Override
    public int numNodes() {
        return(kn.size());
    }

    @Override
    public int removeIsolatedNodes() {
        Iterator<Node> it = iterator();
        int i = 0;
        Node k = it.next();
        if (k.numEdges() == 0) {
            it.remove();
            i++;
        }
        while (it.hasNext()) {
            k = it.next();
            if (k.numEdges() == 0) {
                it.remove();
                i++;
            }
        }
        return(i);
    }

    @Override
    public int removeUntraversableEdges(RoutingAlgorithm ra, TravelType tt) {
        if (ra.isBidirectional()) {
        Iterator<Node> it = iterator();
        int i = 0;
        Node k = it.next();
            Iterator<Edge> iter = k.iterator();
            MyEdge e = (MyEdge) iter.next();
            if (e.s == k) {
                switch (tt) {
                case TravelType.BIKE:
                    if (!e.sbd) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.CAR:
                    if (!e.scd) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.FOOT:
                    if (!e.sfd) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.ANY:
                    if (!(e.sfd || e.scd || e.sbd)) {
                        iter.remove();
                        i++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Travel-Type");
                }
            }
            if (e.d == k) {
                switch (tt) {
                case TravelType.BIKE:
                    if (!e.dbs) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.CAR:
                    if (!e.dcs) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.FOOT:
                    if (!e.dfs) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.ANY:
                    if (!(e.dfs || e.dcs || e.dbs)) {
                        iter.remove();
                        i++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Travel-Type");
                }
            }
        while (it.hasNext()) {
            k = it.next();
            iter = k.iterator();
            e = (MyEdge) iter.next();
            if (e.s == k) {
                switch (tt) {
                case TravelType.BIKE:
                    if (!e.sbd) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.CAR:
                    if (!e.scd) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.FOOT:
                    if (!e.sfd) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.ANY:
                    if (!(e.sfd || e.scd || e.sbd)) {
                        iter.remove();
                        i++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Travel-Type");
                }
            }
            if (e.d == k) {
                switch (tt) {
                case TravelType.BIKE:
                    if (!e.dbs) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.CAR:
                    if (!e.dcs) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.FOOT:
                    if (!e.dfs) {
                        iter.remove();
                        i++;
                    }
                    break;
                case TravelType.ANY:
                    if (!(e.dfs || e.dcs || e.dbs)) {
                        iter.remove();
                        i++;
                    }
                    break;
                default:
                    throw new IllegalArgumentException("Invalid Travel-Type");
                }
            }
        }
        return(i);
    } else {
        return (0);
    }
    }

    @Override
    public boolean isOverlayGraph() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isOverlayGraph'");
    }

    @Override
    public Node getNodeInUnderlyingGraph(long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNodeInUnderlyingGraph'");
    }
    
}
