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
          // fresh start
          // (Q: why use fragments? A: just to play around a bit.)
          TableLayout tl = (TableLayout) findViewById(R.id.tablelayout);
          int rowcount = tl.getChildCount();
          int colcount = rowcount;
          FragmentTransaction ft = getFragmentManager().beginTransaction();

          for ( int rownum = 0 ; rownum < rowcount ; rownum++ ) { 
            TableRow row = (TableRow) tl.getChildAt(rownum);
            int rowid = row.getId();

            for ( int colnum = 0 ; colnum < colcount ; colnum++ ) {
              ImageFragment tf = ImageFragment.newInstance(rownum, colnum);
              String tag = makeTag(rownum, colnum);
              ft.add(rowid, tf, tag);
            }

          };
          ft.commit();

        }

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
      String tag = makeTag(r,c);
      ImageFragment frgmnt = (ImageFragment) getFragmentManager().findFragmentByTag(tag);
      if (frgmnt != null ) { frgmnt.flipState(); };
    }

    private String makeTag(int row, int col) {
      return String.valueOf(row) + ":" + String.valueOf(col);
    }

}
