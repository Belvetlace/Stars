package data;

import cs_1c.*;

//------------------------------------------------------
public class Foothill
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      int k, arraySize;
      int[] planeChoice = {1, 2, 3}; // 1=(y,x) 2=(y,z) 3=(z,x)
      int NUM_ROWS = 35;  //y
      int NUM_COLS = 70;  //x
      double xConversion, yConversion, zConversion, tempX, tempY, tempZ,
              rangeX, rangeY, rangeZ;
      double xMin = 0, yMin = 0, xMax = 0, yMax = 0, zMin = 0, zMax = 0;
      boolean TEST = false;

      // read the data from files
      StarNearEarthReader starInput
              = new StarNearEarthReader("nearest_stars.txt");

      if (starInput.readError())
      {
         System.out.println("couldn't open " + starInput.getFileName()
                 + " for input.");
         return;
      }

      if(TEST)System.out.println(starInput.getFileName());
      if(TEST)System.out.println(starInput.getNumStars());

      // create an array of objects for our own use:
      arraySize = starInput.getNumStars();
      SNE_Analyzer[] starArray = new SNE_Analyzer[arraySize];
      for (k = 0; k < arraySize; k++)
         starArray[k] =  new SNE_Analyzer( starInput.getStar(k) );

      // display cartesion coords
      for ( k = 0; k < arraySize; k++ )
      {
         if(TEST)System.out.println(starArray[k].getNameCommon() + " "
          + starArray[k].coordToString() + " " + starArray[k].getRank());
         tempX = starArray[k].getX();
         tempY = starArray[k].getY();
         tempZ = starArray[k].getZ();
         xMin = ( tempX < xMin ) ? tempX : xMin;
         xMax = ( tempX > xMax ) ? tempX : xMax;
         yMin = ( tempY < yMin ) ? tempY : yMin;
         yMax = ( tempY > yMax ) ? tempY : yMax;
         zMin = ( tempZ < zMin ) ? tempZ : zMin;
         zMax = ( tempZ > zMax ) ? tempZ : zMax;
      }
      if(TEST)System.out.println(String.format("xMin = %.2f, yMin = %.2f, " +
                      "xMax = %.2f, yMax = %.2f, zMax = %.2f, zMin = %.2f",
              xMin, yMin, xMax, yMax, zMax, zMin));
      rangeX = xMax - xMin;
      rangeY = yMax - yMin;
      rangeZ = zMax - zMin;
      if(TEST)System.out.println(String.format("rangeX = %.2f, rangeY = %.2f, " +
                      "rangeZ = %.2f", rangeX, rangeY, rangeZ));

      System.out.println("Plane 1: (y,x) Plane 2: (y,z) Plane 3: (z,x)");

      for(int i: planeChoice)
      {
         yConversion = (NUM_ROWS - 1) / rangeY;
         xConversion = (NUM_COLS - 1) / rangeX;
         if (i == 3)
            zConversion = (NUM_ROWS - 1) / rangeZ; // (z,x)
         else
            zConversion = (NUM_COLS - 1) / rangeZ; // (y,z)

         if(TEST)System.out.println(String.format("scaleY = %.2f, scaleX = %.2f, " +
                 "scaleZ = %.2f", yConversion, xConversion, zConversion));
         System.out.println("Plane " + i);

         SparseMat<Character> starMap =
                 new SparseMat<>(NUM_ROWS, NUM_COLS, ' ');

         boolean s;
         // stars set up
         for (k = 0; k < arraySize; k++)
         {
            int x = (int) ((starArray[k].getX() - xMin) * xConversion);
            int y = (int) ((starArray[k].getY() - yMin) * yConversion);
            int z = (int) ((starArray[k].getZ() - zMin) * zConversion);
            //System.out.println(String.format("x = %d, y = %d, z = %d", x, y, z));

            if (starArray[k].getRank() < 10)
            {
               Character rank = Integer.toString(starArray[k].getRank()).charAt(0);
               if(i == 1)
                  s = starMap.set(y, x, rank);
               else if (i == 2)
                  s = starMap.set(y, z, rank);
               else
                  s = starMap.set(z, x, rank);
            } else {
               if(i == 1)
                  s = starMap.set(y, x, '*');
               else if (i == 2)
                  s = starMap.set(y, z, '*');
               else
                  s = starMap.set(z, x, '*');
            }

            if (!s)
               if(TEST)System.out.println("set failed");
         }
         // sun set up
         int sunX, sunY, sunZ;
         sunX = (int) ((0 - xMin) * xConversion);
         sunY = (int) ((0 - yMin) * yConversion);
         sunZ = (int) ((0 - zMin) * zConversion);

         if(i == 1)
            s = starMap.set( sunY, sunX, 'S');
         else if (i == 2)
            s = starMap.set( sunY, sunZ, 'S');
         else
            s = starMap.set(sunZ, sunX, 'S');

         if (!s)
            if(TEST)System.out.println("set failed");

         starMap.showAll();
      }
   }
}
