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
		List<Node> nodes = new ArrayList<Node>();	//Liste alle Knoten in einem Array auf, sodass wir darüber iterieren können
		Map<Long, List<Edge>> incommingEdges = new HashMap<>();	//Mape ID zu einer Liste von incomming edges
		Map<Long, List<Edge>> outgoingEdges = new HashMap<>();
		Map<Long, coordinates> koordinaten = new HashMap<>();
		try {
			FileReader read = new FileReader(fileName);
			BufferedReader buf = new BufferedReader(read);
			String line;
			double minx = 0;
			double maxx = 0;
			double miny = 0;
			double maxy = 0;
			boolean anz = true;
			while ((line = buf.readLine())!= null) { //Solange noch Zeilen gelesen werden
				line = line.trim();
				String[] parts = line.split("\\s+");
				if (parts[0].equals("N")){
					List<Edge> inedges = new ArrayList<Edge>();
					List<Edge> outedges = new ArrayList<Edge>();
					MyNode k = new MyNode();
					k.id = Long.parseLong(parts[1]);
					k.x = Double.parseDouble(parts[2]);
					k.y = Double.parseDouble(parts[3]);
					if (anz) {
						maxx = k.x;
						minx = k.x;
						maxy = k.y;
						miny = k.y;
						anz = false;
					} else {
						maxx = Math.max(maxx, k.x);
						minx = Math.min(minx, k.x);
						maxy = Math.max(maxy, k.y);
						miny = Math.min(miny, k.y);
					}
					nodes.add(k);
					incommingEdges.put(k.id, inedges);
					outgoingEdges.put(k.id, outedges);
					coordinates c = new coordinates(k.x, k.y);
					koordinaten.put(k.id, c);
				} else if (parts[0].equals("E")) {
					MyEdge e = new MyEdge();
					e.s = Long.parseLong(parts[1]);
					e.d = Long.parseLong(parts[2]);
					e.scd = parts[3].equals("1");
					e.dcs = parts[4].equals("1");
					e.sbd = parts[5].equals("1");
					e.dbs = parts[6].equals("1");
					e.sfd = parts[7].equals("1");
					e.dfs = parts[8].equals("1");
					coordinates c = koordinaten.get(e.s);
					coordinates d = koordinaten.get(e.d);
					double x = Math.abs(c.getx() - d.getx());
					double y = Math.abs(c.gety() - d.gety());
					e.length = Math.sqrt(x*x + y*y);
					if (e.scd != false || e.sbd != false || e.sfd != false) {
						outgoingEdges.get(e.s).add(e);
						incommingEdges.get(e.d).add(e);
					}
					if (e.dcs != false || e.dbs != false || e.dfs != false) {
						outgoingEdges.get(e.s).add(e);
						incommingEdges.get(e.d).add(e);
					}
				} else {
					throw new IllegalArgumentException("File has wrong format");
				}
			}
			g.Edgesin = incommingEdges;
			g.Edgesout = outgoingEdges;
			g.coo = koordinaten;
			g.kn = nodes;
			g.mx = minx;
			g.Mx = maxx;
			g.My = maxy;
			g.my = miny;
			buf.close();
			read.close();
		} catch (IOException e) {
			throw new FileNotFoundException(e.getMessage());
		}
		return (g);
	}

	/**
	 * Return a node finder algorithm for the graph g. The graph argument allows
	 * the node finder to build internal data structures.
	 *
	 * @param g
	 *            The graph the nodes are looked up in.
	 * @return A node finder algorithm for that graph.
	 */
	public static NodeFinder createNodeFinder(Graph g) {
		// TODO: Implement me.
		return null;
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
