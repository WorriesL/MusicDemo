package com.example.mymusicdemo.views;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    private int mSpace;

    public GridSpaceItemDecoration(int space, RecyclerView parent){
        mSpace = space;
        getRecyclerViewOffsets(parent);
    }

    /**
     *
     * @param outRect Item的矩形边界
     * @param view ItemView
     * @param parent RecyclerView
     * @param state RecyclerView的状态
     */
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.left = mSpace;

//        判断Item是不是每一行第一个Item
//        if(parent.getChildLayoutPosition(view) % 3 == 0){
//            outRect.left = 0;
//        }




    }

    private void getRecyclerViewOffsets(RecyclerView parent){

//        View margin
//        margin为正，则View会距离边界产生一个距离
//        margin为负，则View会超出边界产生一个距离
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) parent.getLayoutParams();
        layoutParams.leftMargin = -mSpace;
        parent.setLayoutParams(layoutParams);
    }
}
