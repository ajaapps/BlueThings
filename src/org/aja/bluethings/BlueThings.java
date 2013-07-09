package org.aja.bluethings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Arrays;

public class BlueThings extends Activity {

    public static String TAG = "org.aja.bluethings.ACT";
    private static int SIZE = 5;
    private static Integer mStates[] = new Integer[SIZE*SIZE];
    private static Integer    mIds[] = new Integer[SIZE*SIZE];
    // col = i%SIZE    row = (int) i/SIZE   i = row*SIZE + col

    @Override
    public void onCreate(Bundle savedInstanceState) {
      PLog.d(TAG, "onCreate()");
      
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      if ( savedInstanceState == null ) {

        for (int i=0 ; i<SIZE*SIZE ; i++) {
          mStates[i] = 0;
          int row = (int) i/SIZE; int col = i%SIZE;
          String tag = "r" + String.valueOf(row) + "c" + String.valueOf(col);
          View img = findViewById(R.id.outerlila).findViewWithTag(tag);
          mIds[i] = img.getId();
          updateImage(i);
        };
        if ( SIZE*SIZE%2 == 1 ) { // flip middle cell
          flipImageAt((int)SIZE/2,(int)SIZE/2);
        }

      } else {

        for (int i=0 ; i<SIZE*SIZE ; i++) {
          updateImage(i);
        }

      }

    }

    public void onClick(View img) {
      int id = img.getId();
      int index = Arrays.asList(mIds).indexOf(id);
      int row = (int) index / SIZE;
      int col = index%SIZE;
      PLog.d(TAG, "flipping neighbours of (" + row + "," + col + ")");
      flipImageAt(row-1,col);
      flipImageAt(row+1,col);
      flipImageAt(row,col-1);
      flipImageAt(row,col+1);
    }     

    private void flipImageAt(int r, int c) {
      if ( r>=0 && r<SIZE && c>=0 && c<SIZE ) {
        PLog.d(TAG, "flipImageAt(" + r + "," + c + ")");
        int index = r*SIZE + c;
        mStates[index] = mStates[index] == 0 ? 1 : 0;
        updateImage(index);
      }
    }
 
    private void updateImage(int index) {
      ImageView img = (ImageView) findViewById(mIds[index]);
      if ( mStates[index] == 1 ) {
        img.setImageResource(R.drawable.composite);
      } else {
        img.setImageResource(R.drawable.yellowpin);
      }
    }

}

