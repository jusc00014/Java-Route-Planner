package routing;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.HashMap;
import java.math.BigDecimal;

public class NextNode implements NodeFinder{

    Graph g;
    Map<Double, Map<Double, Square>> sq;

    @Override
    public Node getNodeForCoordinates(Coordinate c) {
        this.sq = erstelleSystem();                                  //Erstelle ein System aus Quadranten
        System.out.println("20");
        einteilung();                                         //Füge alle Knoten hinzu
        System.out.println("22");
        Node nn = nearestsquare(c);                          //Finde den Quadranten, der der Koordinate am nächste ist und mindestens einen Knoten hat
        System.out.println("23");
        Coordinate d = nn.getCoordinate();
        double dist = routing.Coordinate.distance(c.getLatitude(), c.getLongitude(), d.getLatitude(), d.getLongitude());
        CoordinateBox B = c.computeBoundingBox(dist);           //Gibt ein Quadrat mit min und max-Koordinaten zurück
        d = B.getLowerBound();
        Coordinate f = B.getUpperBound();
        Square q = getField(d.getLongitude(), d.getLatitude());
        for (double y = q.minlat; y <= f.getLatitude(); y = y+0.01){  //Durchsuche alle Quadranten, die in dem gegebnen Quadrat liegen
            Map<Double, Square> v = sq.get(y);
            for (double x = q.minlong; x <= f.getLongitude(); x = x+0.01){
                Square s = v.get(x);
                for (Node m : s.k) {
                    d = m.getCoordinate();
                    double h = routing.Coordinate.distance(c.getLatitude(), c.getLongitude(), d.getLongitude(), d.getLatitude());
                    if (h < dist) {
                        dist = h;
                        nn = m;
                    }
                }
            }
        }
        return(nn);
    }

    public class Square {
        public double minlong;
        public double maxlong;
        public double minlat;
        public double maxlat;
        public List<Node> k = new ArrayList<Node>();
    }

    public Map<Double, Map<Double, Square>> erstelleSystem() {
        Coordinate c = g.getNWCoordinate();
        double n = c.getLatitude();
        double w = c.getLongitude();
        c = g.getSECoordinate();
        double s = c.getLatitude();
        double e = c.getLongitude();
        Map<Double, Map<Double, Square>> qs = new HashMap<>();
        BigDecimal zeile = BigDecimal.valueOf(round(s, 2)); //Written with AI
        BigDecimal spalte = BigDecimal.valueOf(round(e, 2));
        int i = 0;
        System.out.println("n, w, s, e: " + n + " " + w +" " + s +" " + e + "     " +zeile + " " +spalte);
        while (zeile.compareTo(BigDecimal.valueOf(n)) <= 0) {
            Map<Double, Square> hm = new HashMap<>();
            qs.put(zeile.doubleValue(), hm);
            while (spalte.compareTo(BigDecimal.valueOf(w)) <= 0) {
                i++;
                Square q = new Square();
                q.minlong = zeile.doubleValue();
                q.minlat = spalte.doubleValue();
                qs.get(zeile.doubleValue()).put(spalte.doubleValue(), q);                 
                spalte = spalte.add(BigDecimal.valueOf(0.01));              
                q.maxlong = zeile.add(BigDecimal.valueOf(0.01)).doubleValue();
                q.maxlat = spalte.doubleValue();

                System.out.println("Quadrant: " + q.minlat + " -> " + q.maxlat + "   " + q.minlong + " -> " + q.maxlong);
            }
            zeile = zeile.add(BigDecimal.valueOf(0.01));
            spalte = BigDecimal.valueOf(round(e, 2));
        }
        Map<Double, Square> hm = qs.get(49.26);
        System.out.println("84");
        for (double schlüssel : hm.keySet()) {
            System.out.println(schlüssel + " -> " + hm.get(schlüssel));
        }
        Square q = hm.get(6.99);
        System.out.println("Knoten: " + i);
        System.out.println("Q: " + q.minlat + " -> " + q.maxlat + "   " + q.minlong + " -> " + q.maxlong);
        this.sq = qs;
        return(qs);
    }

    //Written with AI
    private static double round(double value, int decimalPoints) {
        double factor = Math.pow(10, decimalPoints);
        return Math.floor(value * factor) / factor;
    }

    public Square getField (double a, double b) {
        double x = round(a, 2);
        double y = round(b, 2);
        Map<Double, Square> s = this.sq.get(y);
        if (s == null) {
            s = new HashMap<>();
            sq.put(y, s);
        }
        Square q = s.get(x);
        if (q == null) {
            q = new Square();
            q.minlat = y;
            q.minlong = x;
            q.maxlat = y + 0.01;
            q.maxlong = x + 0.01;
            s.put(x,q);
        }
        return(q);
    }

    public void einteilung() {
        Iterator<Node> it = this.g.iterator();
        Node kk = it.next();
        Coordinate c = (Coordinate) kk.getCoordinate();
        Square q = getField(c.getLatitude(), c.getLongitude());
        System.out.println(c.getLatitude() + " " + c.getLongitude());
        System.out.println(q.minlat+ " -> " + q.maxlat + "      " + q.minlong +" -> " + q.maxlong);
        q.k.add(kk);
        while (it.hasNext()) {
            kk = it.next();
            c = (Coordinate) kk.getCoordinate();
            q = this.getField(c.getLatitude(), c.getLongitude());
            q.k.add(kk);
        }
        return;
    }

    public Node nearestsquare(Coordinate c) {
        double y = c.getLatitude();
        double x = c.getLongitude();
        System.out.println("127");
        Square q = getField(x, y);
        System.out.println("129");
        int i = 0;
        int j = 1;
        int k = 1;
        int richtung = 0; //0 == hoch, 1 == rechts, 2 == runter, 3 == links; gibt an, wo das nächste Feld zu suchen ist
        int ex = 0;
        while(q.k.size() == 0) {
            System.out.println("Quadrat " + ex + ": " + q.minlat + " " + q.minlong);
            if (ex > 4000) {
                break;
            }
            ex++;
            System.out.println("135");
            if (i == 2) {
                k++;
                j = k;
                i = 0;
            }
            if (i < 2) {
                switch (richtung){
                case 0:
                    y = y + 0.01;
                    break;
                case 1:
                    x = x + 0.01;
                    break;
                case 2:
                    y = y - 0.01;
                    break;
                case 3:
                    x = x - 0.01;
                    break;
                }
                q  = getField(x,y);
                j--;
            }
            if (j == 0) {
                i++;
                richtung = (richtung + 1) % 4;
                j = k;
            }
        }
        return(q.k.get(0));
    }
    
}
