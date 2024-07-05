package routing;

import java.util.List;

public class Routalg implements RoutingAlgorithm {

    @Override
    public Route computeRoute(Graph g, List<Node> nodes, TravelType tt) throws NoSuchRouteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeRoute'");
    }

    @Override
    public RouteLeg computeRouteLeg(Graph g, long startId, long endId, TravelType tt) throws NoSuchRouteException {
        // TODO Auto-generated method stub
        throw new NoSuchRouteException();
    }

    @Override
    public RouteLeg computeRouteLeg(Graph G, Node start_node, Node end_node, TravelType TT)
            throws NoSuchRouteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'computeRouteLeg'");
    }

    @Override
    public boolean isBidirectional() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isBidirectional'");
    }
    
}
