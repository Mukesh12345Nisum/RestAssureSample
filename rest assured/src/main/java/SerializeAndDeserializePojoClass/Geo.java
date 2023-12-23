package SerializeAndDeserializePojoClass;

public class Geo
{
  private double lat;
  private double longt;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongt() {
        return longt;
    }

    public void setLongt(double longt) {
        this.longt = longt;
    }

    public Geo(double lat, double longt)
    {
        this.lat = lat;
        this.longt = longt;
    }
}
