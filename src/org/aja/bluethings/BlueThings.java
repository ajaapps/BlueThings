package org.aja.bluethings;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

public class BlueThings extends Activity implements ImageFragment.Listener {

    public static String TAG = "org.aja.bluethings.ACT";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        //PLog.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        if (savedInstanceState == null) {
          // fresh start: fill table   (Q: Why use fragments? A: Having fun coding.)
          TableLayout table = (TableLayout) findViewById(R.id.tablelayout);
          int rowcount = table.getChildCount();
          int colcount = rowcount;
          FragmentTransaction traction = getFragmentManager().beginTransaction();

          for ( int rownum = 0 ; rownum < rowcount ; rownum++ ) { 
            TableRow row = (TableRow) table.getChildAt(rownum);
            int rowid = row.getId();

            for ( int colnum = 0 ; colnum < colcount ; colnum++ ) {
              ImageFragment fragment;
              if ( isMiddleThing(rowcount, colcount, rownum, colnum ) ) {
                fragment = ImageFragment.newInstance(rownum, colnum, true);
              } else {
                fragment = ImageFragment.newInstance(rownum, colnum, false);
              }
              String tag = makeTag(rownum, colnum);
              traction.add(rowid, fragment, tag);
            }

          };
          traction.commit();

        }

    }

    // is (row,col) is the middle cell of the table?
    private boolean isMiddleThing(int rowcount, int colcount, int row, int col ) {
      if ( (rowcount*colcount)%2 == 0 ) { return false; } // there is no middle cell
      if ( row != (rowcount-1)/2 )      { return false; } // not the middle row
      if ( col != (colcount-1)/2 )      { return false; } // not the middle column
      return true;
    }

    // implement ImageFragment.Listener
    public void flipNeighbours(int row, int col) {

      PLog.d(TAG, "flipNeighbours(" + row + "," + col + ")");
      TableLayout tl = (TableLayout) findViewById(R.id.tablelayout);
      int rowcount = tl.getChildCount();
      int colcount = rowcount;

      findAndFlip(row-1,col);
      findAndFlip(row+1,col);
      findAndFlip(row,col-1);
      findAndFlip(row,col+1);

    }     

    private void findAndFlip(int r, int c) {
      //PLog.d(TAG, "findAndFlip(" + r + "," + c + ")");
      String tag = makeTag(r,c);
      ImageFragment frgmnt = (ImageFragment) getFragmentManager().findFragmentByTag(tag);
      if (frgmnt != null ) { frgmnt.flipState(); };
    }

    private String makeTag(int row, int col) {
      return String.valueOf(row) + ":" + String.valueOf(col);
    }

}
