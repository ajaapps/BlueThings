package org.aja.bluethings;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.Arrays;

public class BlueThings extends Activity {

    private static String TAG = "org.aja.bluethings";
    private void d(String m) { PLog.d(TAG, m); };

    private static int SIZE = 5;
    // col=idx%SIZE ; row=(int)idx/SIZE ; idx=row*SIZE+col

    private static Integer mStates[] = new Integer[]{ 0,0,0,0,0,
                                                      0,0,0,0,0,
                                                      0,0,1,0,0,
                                                      0,0,0,0,0,
                                                      0,0,0,0,0, };

    private Integer mIds[] = new Integer[]{
          R.id.r0c0, R.id.r0c1, R.id.r0c2, R.id.r0c3, R.id.r0c4,
          R.id.r1c0, R.id.r1c1, R.id.r1c2, R.id.r1c3, R.id.r1c4,
          R.id.r2c0, R.id.r2c1, R.id.r2c2, R.id.r2c3, R.id.r2c4,
          R.id.r3c0, R.id.r3c1, R.id.r3c2, R.id.r3c3, R.id.r3c4,
          R.id.r4c0, R.id.r4c1, R.id.r4c2, R.id.r4c3, R.id.r4c4, };

    @Override
    public void onCreate(Bundle savedInstanceState) {
      d("onCreate()");
      
      super.onCreate(savedInstanceState);
      setContentView(R.layout.main);

      for (int i=0 ; i<SIZE*SIZE ; i++) {
        updateImageView(i);
      };

    }

    public void onClick(View img) {

      int id = img.getId();
      int index = Arrays.asList(mIds).indexOf(id);
      int row = (int) index / SIZE;
      int col = index%SIZE;

      d("flipping neighbours of (" + row + "," + col + ")");
      flipImageAt(row-1,col);
      flipImageAt(row+1,col);
      flipImageAt(row,col-1);
      flipImageAt(row,col+1);

    }     

    private void flipImageAt(int r, int c) {
      if ( r>=0 && r<SIZE && c>=0 && c<SIZE ) { // no wrap-around
        d("flipImageAt(" + r + "," + c + ")");
        int index = r*SIZE + c;
        mStates[index] = mStates[index] == 0 ? 1 : 0;
        updateImageView(index);
      }
    }
 
    private void updateImageView(int index) {
      //d("updateImageView(" + index + ")");
      ImageView img = (ImageView) findViewById(mIds[index]);
      if ( mStates[index] == 1 ) {
        img.setImageResource(R.drawable.composite);
      } else {
        img.setImageResource(R.drawable.yellowpin);
      }
      img.invalidate(); // nexus 7 didn't redraw without
    }

}

