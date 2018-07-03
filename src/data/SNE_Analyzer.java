package data;

import cs_1c.StarNearEarth;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class SNE_Analyzer extends StarNearEarth
{
   private double x, y, z;

   // constructors
   public SNE_Analyzer()
   {
      /* TBD */

   }

   // construct an SNE_Analyzer from a StartNearEarth object
   public SNE_Analyzer( StarNearEarth sne )
   {
      setRank(sne.getRank());
      setNameCns(sne.getNameCns());
      setNumComponents(sne.getNumComponents());
      setNameLhs(sne.getNameLhs());
      setRAsc(sne.getRAsc());
      setDec(sne.getDec());
      setPropMotionMag(sne.getPropMotionMag());
      setPropMotionDir(sne.getPropMotionDir());
      setParallaxMean(sne.getParallaxMean());
      setParallaxVariance(sne.getParallaxVariance());
      SetBWhiteDwarfFlag(sne.getWhiteDwarfFlag());
      setSpectralType(sne.getSpectralType());
      setMagApparent(sne.getMagApparent());
      setMagAbsolute(sne.getMagAbsolute());
      setMass(sne.getMass());
      setNotes(sne.getNotes());
      setNameCommon(sne.getNameCommon());
      calcCartCoords();
   }

   // accessors
   double getX() { return x; }
   double getY() { return y; }
   double getZ() { return z; }

   public void calcCartCoords()
   {
      // turn parallax to distance in light years (LY)
      // LY = 3.262 / parallax in arcsec
      double LY = 3.262 / getParallaxMean();
      // RA(rad) = RAD(deg)*(pi/180)
      double RA = getRAsc() * (Math.PI / 180);
      // DEC(rad) = DEC(deg)*(pi/180)
      double DEC = getDec() *(Math.PI / 180);
      // right ascension (RA), declination (DEC) and
      // parallax numbers into Cartesian (x, y, z)
      x = LY * cos(DEC) * cos(RA);
      y = LY * cos(DEC) * sin(RA);
      z = LY * sin(DEC);
   }

   public String coordToString()
   {
      return String.format("(%.2f, %.2f, %.2f)", x, y, z );
   }
}