package org.aja.bluethings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Arrays;

public class BlueThings extends Activity {

    public static String TAG = "org.aja.bluethings.ACT";

    private static int SIZE = 5;
    // col = i%SIZE    row = (int) i/SIZE   i = row*SIZE + col

    private static Integer mStates[] = new Integer[]{ 0,0,0,0,0,
                                                      0,0,0,0,0,
                                                      0,0,1,0,0,
                                                      0,0,0,0,0,
                                                      0,0,0,0,0, };

    private        Integer    mIds[] = new Integer[]{ 0,0,0,0,0,
                                                      0,0,0,0,0,
                                                      0,0,0,0,0,
                                                      0,0,0,0,0,
                                                      0,0,0,0,0, };

    @Override
    public void onCreate(Bundle savedInstanceState) {
      PLog.d(TAG, "onCreate()");
      
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      // get ID's of views with tags like r2c4
      for (int i=0 ; i<SIZE*SIZE ; i++) {
        int row = (int) i/SIZE;
        int col = i%SIZE;
        String tag = "r" + String.valueOf(row) + "c" + String.valueOf(col);
        View view = findViewById(R.id.outerlila).findViewWithTag(tag);
        mIds[i] = view.getId();
        updateImage(i);
      };

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
      if ( r>=0 && r<SIZE && c>=0 && c<SIZE ) { // no wrap-around
        PLog.d(TAG, "flipImageAt(" + r + "," + c + ")");
        int index = r*SIZE + c;
        mStates[index] = mStates[index] == 0 ? 1 : 0;
        updateImage(index);
      }
    }
 
    private void updateImage(int index) {
      PLog.d(TAG, "updateImage(" + index + ")");
      ImageView img = (ImageView) findViewById(mIds[index]);
      if ( mStates[index] == 1 ) {
        img.setImageResource(R.drawable.composite);
      } else {
        img.setImageResource(R.drawable.yellowpin);
      }
    }

}

