package routing;


import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MyGraph implements Graph {

	public List<Node> kn;
	public Map<Long, List<Edge>> Edgesin;
	public Map<Long, List<Edge>> Edgesout;
	public Map<Long, coordinates> coo;
    public double mx;
    public double Mx;
    public double my;
    public double My;
    
    @Override
    public Node getNode(long id) {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNode'");
    }

    @Override
    public Coordinate getNWCoordinate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNWCoordinate'");
    }

    @Override
    public Coordinate getSECoordinate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSECoordinate'");
    }

    @Override
    public Iterator<Node> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    @Override
    public int numEdges() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numEdges'");
    }

    @Override
    public int numNodes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'numNodes'");
    }

    @Override
    public int removeIsolatedNodes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeIsolatedNodes'");
    }

    @Override
    public int removeUntraversableEdges(RoutingAlgorithm ra, TravelType tt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeUntraversableEdges'");
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
