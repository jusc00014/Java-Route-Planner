package routing;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class Factory {

	/**
	 * Create a graph from the description in a .nae file.
	 *
	 * @param fileName
	 *            A path to an NAE file.
	 *
	 * @return The graph as described in the .nae file.
	 *
	 * @throws IOException
	 *             If an Input/Output error occurs.
	 */
	public static Graph createGraphFromMap(String fileName) throws IOException {
		MyGraph g = new MyGraph();
		//List<Node> nodes = new ArrayList<Node>();	//Liste alle Knoten in einem Array auf, sodass wir darüber iterieren können
		Map<Long, Node> nodes = new HashMap<>(); 
		Map<Long, List<Edge>> incommingEdges = new HashMap<>();	//Mape ID zu einer Liste von incomming edges
		Map<Long, List<Edge>> outgoingEdges = new HashMap<>();
		Map<Long, Coordinate> koordinaten = new HashMap<>();
		try {
			FileReader read = new FileReader(fileName);
			BufferedReader buf = new BufferedReader(read);
			String line;
			double minx = 0;
			double maxx = 0;
			double miny = 0;
			double maxy = 0;
			boolean anz = true;
			int num = 0;
			while ((line = buf.readLine())!= null) { //Solange noch Zeilen gelesen werden
				line = line.trim();
				String[] parts = line.split("\\s+");
				if (parts[0].equals("N")){
					List<Edge> inedges = new ArrayList<Edge>();
					List<Edge> outedges = new ArrayList<Edge>();
					MyNode k = new MyNode();
					k.id = Long.parseLong(parts[1]);
					double a = Double.parseDouble(parts[2]);
					double b = Double.parseDouble(parts[3]);
					k.lati = a;
					k.longi = b;
					if (anz) {
						maxx = a;
						minx = a;
						maxy = b;
						miny = b;
						anz = false;
					} else {
						maxx = Math.max(maxx, a);
						minx = Math.min(minx, a);
						maxy = Math.max(maxy, b);
						miny = Math.min(miny, b);
					}
					nodes.put(k.id, k);
					incommingEdges.put(k.id, inedges);
					outgoingEdges.put(k.id, outedges);
					Coordinate c = new Coordinate(a, b);
					koordinaten.put(k.id, c);
					k.c = c;
					List<Edge> edin = new ArrayList<Edge>();
					k.ein = edin;
					List<Edge> edout = new ArrayList<Edge>();
					k.eout = edout;
				} else if (parts[0].equals("E")) {
					MyEdge e = new MyEdge();
					e.s = nodes.get(Long.parseLong(parts[1]));
					e.d = nodes.get(Long.parseLong(parts[2]));
					e.scd = parts[3].equals("1");
					e.dcs = parts[4].equals("1");
					e.sbd = parts[5].equals("1");
					e.dbs = parts[6].equals("1");
					e.sfd = parts[7].equals("1");
					e.dfs = parts[8].equals("1");
					Coordinate c = (e.s).getCoordinate();
					Coordinate d = (e.d).getCoordinate();
					e.length = routing.Coordinate.distance(c.getLatitude(), c.getLongitude(), d.getLongitude(), d.getLongitude());
					MyNode src = (MyNode) (e.s);
					MyNode dst = (MyNode) (e.d);
					num +=2;
					src.eout.add(e);
					dst.ein.add(e);
					src.ein.add(e);
					dst.eout.add(e);
				} else {
					buf.close();
					read.close();
					throw new IllegalArgumentException("File has wrong format");
				}
			}
			System.out.println(minx + " -> " + maxx + "    " + miny + " -> " + maxy);
			List <Long> idList = new ArrayList<>();
			for (Long key : nodes.keySet()) {
				idList.add(key);
			}
			g.idList = idList;
			g.Edgesin = incommingEdges;
			g.Edgesout = outgoingEdges;
			g.coo = koordinaten;
			g.kn = nodes;
			g.mx = minx;
			g.Mx = maxx;
			g.My = maxy;
			g.my = miny;
			g.nume = num;
			buf.close();
			read.close();
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}

	/*	//Zu testzwecken
		for (Map.Entry<Long, Node> entry : (g.kn).entrySet()) {
			MyNode k = (MyNode) entry.getValue();
			System.out.println("Knoten: " + k.getId());
			for (Edge eg : k.eout) {
				System.out.println("from " + eg.getStart() + " to " + eg.getEnd());
			}
		} */
		return (g);
	}

	/*
	 * Return a node finder algorithm for the graph g. The graph argument allows
	 * the node finder to build internal data structures.
	 *
	 * @param g
	 *            The graph the nodes are looked up in.
	 * @return A node finder algorithm for that graph.
	 */
	public static NodeFinder createNodeFinder(Graph g) {
		NextNode find = new NextNode();
		find.g = g;
		return(find);
	}

	/**
	 * == BONUS ==
	 *
	 * Compute the overlay graph (or junction graph).
	 *
	 * Note: This is part of a bonus exercise, not of the regular project.
	 *
	 * @return The overlay graph for the given graph g.
	 */
	public static Graph createOverlayGraph(Graph g) {
		// TODO: Implement me.
		return null;
	}

	/**
	 * Return a routing algorithm for the graph g. This allows to inspect the
	 * graph and choose from different routing strategies if appropriate.
	 *
	 * @param g
	 *            The graph the routing is performed on.
	 * @return A routing algorithm suitable for that graph.
	 */
	public static RoutingAlgorithm createRoutingAlgorithm(Graph g) {
		// TODO: Implement me.
		return null;
	}

}
