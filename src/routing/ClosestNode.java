package routing;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

public class ClosestNode implements NodeFinder {
    
    MyGraph g;
    int n = 0;
    int s = 0;
    int w = 0;
    int e = 0;
    int rows = 0;
    int col = 0;
    ArrayList<Node>[] sq;


    public int findSquare(Coordinate c){
        int y = (int) ((c.getLatitude()+90)*1000);
        int x = (int) ((c.getLongitude()+180)*1000);
        y = y-s;
        x = x-w;
        return(y*col+x);
    }

    public void einteilung() {  //Works
        Iterator<Node> it = this.g.iterator();
        Node kk = it.next();
        Coordinate c = (Coordinate) kk.getCoordinate();
        int ind = findSquare(c);
        List<Node> klist = sq[ind];
        if (klist == null) {
            klist = new ArrayList<Node>();
        }
        klist.add(kk);       
        while (it.hasNext()) {
            kk = it.next();
            c = kk.getCoordinate();
            ind = findSquare(c);
            klist = sq[ind];
            if (klist == null) {
                sq[ind] = new ArrayList<Node>();
            }
            sq[ind].add(kk);
        }
        return;
    }

    public int nearestsquare(Coordinate c) {
        int y = (int) ((c.getLatitude()+90)*1000);
        int x = (int) ((c.getLongitude()+180)*1000);
        y = y-s;
        x = x-w;
        int squ = y*col+x;
        List<Node> quad = sq[squ];
        int i = 0;
        int j = 1;
        int k = 1;
        int richtung = 0; //0 == hoch, 1 == rechts, 2 == runter, 3 == links; gibt an, wo das nächste Feld zu suchen ist
        int ex = 0;
        int grenzen = n-s;
        int grenzee = e-w;
        while(quad == null) {
            if (i == 2) {
                k++;
                j = k;
                i = 0;
            }
            if (i < 2) {
                switch (richtung){
                case 0:
                    y = y + 1;
                    break;
                case 1:
                    x = x + 1;
                    break;
                case 2:
                    y = y - 1;
                    break;
                case 3:
                    x = x - 1;
                    break;
                }
                if(x >= 0 && y < n-s && y >= 0 && x < e-w){
                    squ = y*col+x;                    
                    quad = sq[squ];
                }
                j--;
            }
            if (j == 0) {
                i++;
                richtung = (richtung + 1) % 4;
                j = k;
            }
        }
        return(squ);
    }

    public double dista (Coordinate c, Node n) {
        double d = 0;
        Coordinate f = n.getCoordinate();
        d = routing.Coordinate.distance(c.getLatitude(), c.getLongitude(), f.getLatitude(), f.getLongitude());
        return(d);
    }

    public Node durchsuchealle(Coordinate c, Coordinate low, Coordinate up, int ind){
        int sn = (int) ((low.getLatitude()+90)*1000-s);
        int we = (int) ((low.getLongitude()+180)*1000-w);
        int boundn = (int) ((up.getLatitude()+90)*1000-s);
        int bounde = (int) ((up.getLongitude()+180)*1000-w);
        sn = Math.max(0, sn);
        we = Math.max(0,we);
        boundn = Math.min(n, boundn);
        bounde = Math.min(e, bounde);
        List <Node> k = sq[sn*col+we];
        double t = 0;
        Node res = sq[ind].get(0);
        double dist = dista(c, res);
        for (int i = sn; i <= boundn; i++) {
            for (int j = we; j <= bounde; j++) {
                if (i*col+j < col*rows){                    
                    k = sq[i*col+j];
                    if (k != null){
                        for (Node kn : k){
                            t = dista(c,kn);
                            if (t < dist) {
                                dist = t; 
                                res = kn;                                
                            }
                        }
                    }
                }
            }
        }
        if(res.getId() == 1){
            long id = 0;
            Node kn = g.kn.get(id);
            if (dista(c,kn)<dista(c,res)) {
                res = kn;
            }
        }
        return(res);
    }
    
    @Override
    public Node getNodeForCoordinates(Coordinate c) {
        Coordinate d = g.getNWCoordinate();
        n = (int) ((d.getLatitude()+90)*1000) + 1;
        w = (int) ((d.getLongitude()+180)*1000);
        Coordinate ttf = g.getNWCoordinate();
        d = g.getSECoordinate();
        s = (int) ((d.getLatitude()+90)*1000);
        e = (int) ((d.getLongitude()+180)*1000) + 1;
        rows = n-s;
        col = e-w;
        sq = new ArrayList[rows*col];
        einteilung();
        int ind = nearestsquare(c);
        Node kn = sq[ind].get(0);
        d = kn.getCoordinate();
        double dist = routing.Coordinate.distance(c.getLatitude(), c.getLongitude(), d.getLatitude(), d.getLongitude());
        CoordinateBox B = c.computeBoundingBox(dist);           //Gibt ein Quadrat mit min und max-Koordinaten zurück
        d = B.getLowerBound();
        Coordinate f = B.getUpperBound();
        kn = durchsuchealle(c, d, f, ind);
        return(kn);
    }    
}
