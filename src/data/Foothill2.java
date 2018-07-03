package data;

import cs_1c.*;

// Main file for StarNearEarth Analyzer Project


//------------------------------------------------------
public class Foothill2
{
   // -------  main --------------
   public static void main(String[] args) throws Exception
   {
      String outString;
      int k, arraySize, row, col;
      double maxX, minX, maxY, minY, maxZ, minZ,
              xRange, yRange, zRange,
              xConversion, yConversion, zConversion;
      final int NUM_COLS = 70;
      final int NUM_ROWS = 35;

      StarNearEarthReader starInput
              = new StarNearEarthReader("nearest_stars.txt");

      if (starInput.readError())
      {
         System.out.println("couldn't open " + starInput.getFileName()
                 + " for input.");
         return;
      }

      // do this just to see if our read went well
      System.out.println(starInput.getFileName());
      System.out.println(starInput.getNumStars());

      // create an array of objects for our own use:
      arraySize = starInput.getNumStars();
      SNE_Analyzer[] starArray = new SNE_Analyzer[arraySize];
      for (k = 0; k < arraySize; k++)
         starArray[k] =  new SNE_Analyzer( starInput.getStar(k) );

      // display cartesian coords
      for ( k = 0; k < arraySize; k++ )
         System.out.println( starArray[k].getNameCommon() + " "
                 + starArray[k].coordToString());

      // now for the graphing
      // get max and min coords for scaling
      maxX = minX = maxY = minY = maxZ = minZ = 0;
      for (k = 0; k < arraySize; k++)
      {
         // not shown;
      }
      xRange = maxX - minX;
      yRange = maxY - minY;
      zRange = maxZ - minZ;

      // form 50 x 25 grid for display: x-y projection
      //xConversion = // not shown
      //        yConversion = // not shown

      SparseMat<Character> starMap = new SparseMat<Character>(NUM_ROWS, NUM_COLS, ' ');

      for (k = 0; k < arraySize; k++)
      {
         //row = // not shown
         //col = // not shown

         if (starArray[k].getRank() < 10)
         {
            Character rank = ' ';
            //starMap.set(row, col, rank);
         }
         else
         {
            //starMap.set(row, col, '*');
         }
      }

      // set sun at center
      //row = // not shown
      //col = // not shown
      //starMap.set( row, col, 'S' );

      for (row = 0; row < NUM_ROWS; row++)
      {
         outString = "";
         // inner loop that builds outString not shown
         System.out.println( outString );
      }
   }
}