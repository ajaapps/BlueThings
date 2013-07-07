package org.aja.bluethings;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.ClassCastException;


public class ImageFragment extends Fragment implements OnClickListener {

    public static String TAG = "org.aja.bluethings.FRM";  
    private int mRow;
    private int mColumn;
    private boolean mState;
    private Listener mListener;

    public interface Listener {
      public void flipNeighbours(int row, int col);
    }
    
    public static ImageFragment newInstance(int row, int col) {
      //PLog.d(TAG, "newInstance(" + row + "," + col + ")");
      ImageFragment tf = new ImageFragment();
      Bundle b = new Bundle();
      b.putInt("row", row);
      b.putInt("col", col);
      tf.setArguments(b);
      return tf;
    }

    @Override 
    public void onAttach(Activity a) { 
      super.onAttach(a); 
      //PLog.d(TAG, "onAttach(a)"); 

      try {
        mListener = (Listener) a;
      } catch (ClassCastException e) {
        throw new ClassCastException(a.toString() + " must implement Listener.");
      }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle sIS) {

      super.onCreateView(inflater, container, sIS);
      //PLog.d(TAG, "onCreateView(i,c,b)");

      if ( sIS == null ) {
        mRow = getArguments().getInt("row", -1);
        mColumn = getArguments().getInt("col", -1);
        mState = false;
      } else {
        mRow = sIS.getInt("row");
        mColumn = sIS.getInt("col");
        mState = sIS.getBoolean("state");
      }

      View vw = inflater.inflate(R.layout.imageview, container, false);
      vw.setOnClickListener(this);
      setImage(vw);
    
      return vw;

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
      //PLog.d(TAG, "onSaveInstanceState()");
      outState.putInt("row", mRow);
      outState.putInt("col", mColumn);
      outState.putBoolean("state", mState);
      super.onSaveInstanceState(outState);
    }

    // implement OnClickListener
    public void onClick(View view) {
      //PLog.d(TAG, "onClick(v). (row,col)=(" + mRow + "," + mColumn + ")");
      mListener.flipNeighbours(mRow, mColumn);
    }

    public void flipState() {
      //PLog.d(TAG, "flipState(). (r,c)=(" + mRow + "," + mColumn + ")");
      mState = !mState;
      setImage(this.getView());
    }

    private void setImage(View view) {
      //PLog.d(TAG, "setImage(v). (r,c)=(" + mRow + "," + mColumn + "). mState=" + mState);
      ImageView iv = (ImageView) view;
      if ( mState ) {
        iv.setImageResource(R.drawable.bluething);
      } else {
        iv.setImageDrawable(null);
      }
    }

}

