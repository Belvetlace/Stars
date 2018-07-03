package data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class SparseMat<E> implements Cloneable
{
   protected int numRows, numCols;
   protected E defaultVal;
   protected ArrayList<LinkedList<MatNode>> rows;
   private static String lineSeparator = System.getProperty("line.separator");

   public SparseMat(int numRows, int numCols, E defaultVal)
   {
      if (numRows < 1 || numCols < 1)
      {
         throw new IllegalArgumentException();
      }
      this.numRows = numRows;
      this.numCols = numCols;
      this.defaultVal = defaultVal;
      allocateEmptyMatrix();
   }

   private void allocateEmptyMatrix()
   {
      rows = new ArrayList<LinkedList<MatNode>>(numRows);
      for (int i = 0; i < numRows; i++)
      {
         rows.add(new LinkedList<MatNode>());
      }
   }

   public E get(int r, int c) throws IndexOutOfBoundsException
   {
      if (r < 0 || r >= numRows || c < 0 || c >= numCols)
      {
         throw new IndexOutOfBoundsException();
      }

      LinkedList<MatNode> matNodes = rows.get(r);
      if (!matNodes.isEmpty())
      {
         Iterator<MatNode> iter = matNodes.iterator();
         MatNode<E> temp;
         while (iter.hasNext())
         {
            temp = iter.next();
            if (c == temp.getCol())
            {
               return temp.getData();
            }
         }
      }
      return defaultVal;

   }

   public boolean set(int r, int c, E x)
   {
      if (r < 0 || r >= numRows || c < 0 || c >= numCols)
      {
         return false;
      }
      LinkedList<MatNode> matNodes = rows.get(r);
      if (matNodes.isEmpty())
      {
         if (!x.equals(defaultVal))
         {
            matNodes.add(new MatNode(c, x));
         }
         return true;
      }

      Iterator<MatNode> iter = matNodes.iterator();
      boolean colunmExist = false;
      MatNode<E> temp = null;
      while (iter.hasNext())
      {
         temp = iter.next();
         if (c == temp.getCol())
         {
            colunmExist = true;
            break;
         }
      }
      if (colunmExist)
      {
         if (x.equals(defaultVal))
         {
            iter.remove();
            return true;
         }
         try
         {
            matNodes.set(matNodes.indexOf(temp), new MatNode(c, x));
            return true;
         } catch (IndexOutOfBoundsException e)
         {
            return false;
         }
      } else
      {
         if (!x.equals(defaultVal))
         {
            iter = matNodes.iterator();
            if (iter.hasNext())
            {
               temp = iter.next();
               if (temp.getCol() > c)
               {
                  matNodes.add(matNodes.indexOf(temp), new MatNode(c, x));
                  return true;
               } else
               {
                  matNodes.add(new MatNode(c, x));
                  return true;
               }
            }
         }
      }
      return true;
   }


   public void showSubSquare(int start, int size)
   {
      int msize = (start + size);
      StringBuilder rStr = new StringBuilder();
      if (start < 0 || start > numRows || start > numCols
              || msize > numCols || msize > numRows)
      {
         System.out.print("");
         return;
      }
      if (size == 0)
      {
         System.out.println("");
         return;
      }
      for (int r = start; r < msize; r++)
      {
         if (rows.get(r).isEmpty())
         {
            for (int i = start; i < msize; i++)
            {
               rStr.append(defaultVal);
               rStr.append(" ");
            }
         } else
         {
            for (int c = start; c < msize; c++)
            {
               try
               {
                  rStr.append(this.get(r, c));
                  rStr.append(" ");
               } catch (IndexOutOfBoundsException e)
               {
                  rStr.append(defaultVal);
                  rStr.append(" ");
               }
            }
         }
         rStr.append(lineSeparator);
      }

      System.out.println(rStr);
   }

   public void showAll()
   {
      StringBuilder rStr = new StringBuilder();

      for (int r = 0; r < numRows; r++)
      {
         if (rows.get(r).isEmpty())
         {
            for (int i = 0; i < numCols; i++)
            {
               rStr.append(defaultVal);
               rStr.append(" ");
            }
         } else {
            for (int c = 0; c < numCols; c++)
            {
               try
               {
                  rStr.append(this.get(r, c));
                  rStr.append(" ");
               } catch (IndexOutOfBoundsException e)
               {
                  rStr.append(defaultVal);
                  rStr.append(" ");
               }
            }
         }
         rStr.append(lineSeparator);
      }

      System.out.println(rStr);
   }

   public ArrayList<LinkedList<MatNode>> getRows()
   {
      return rows;
   }

   public void clear()
   {
      for (int i = 0; i < rows.size(); i++)
      {
         if (!rows.get(i).isEmpty())
         {
            rows.get(i).clear();
         }
      }
   }

   @Override
   protected Object clone() throws CloneNotSupportedException
   {
      SparseMat<MatNode> newObject = (SparseMat<MatNode>) super.clone();
      newObject.rows = (ArrayList<LinkedList<MatNode>>) rows.clone();
      for (int i = 0; i < numRows; i++)
      {
         newObject.rows.set(i, (LinkedList<MatNode>) rows.get(i).clone());
      }
      return newObject;
   }

   public int getRowSize()
   {
      return numRows;
   }

   public int getColSize()
   {
      return numCols;
   }

   protected class MatNode<E> implements Cloneable
   {
      public int col;
      public E data;

      MatNode()
      {
         col = 0;
         data = null;
      }

      MatNode(int cl, E dt)
      {
         col = cl;
         data = dt;
      }

      E getData()
      {
         return data;
      }

      boolean setData(E data)
      {
         this.data = data;
         return true;
      }

      int getCol()
      {
         return col;
      }

      public Object clone() throws CloneNotSupportedException
      {
         MatNode newObject = (MatNode) super.clone();
         return (Object) newObject;
      }
   }
}