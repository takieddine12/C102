package com.bottom.bottomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

public class MainActivity extends BaseActivity {

    private BottomSheetBehavior bottomSheetBehavior;
    private RecyclerView recyclerView;
    private View bottomSheetView;
    private BottomAdapter bottomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomSheetView = findViewById(R.id.bottomSheet);
        recyclerView = findViewById(R.id.rv);

        bottomSheetBehavior  = BottomSheetBehavior.from(bottomSheetView);
        bottomSheetBehavior.setPeekHeight(200);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setMaxHeight(1200);
        bottomSheetBehavior.addBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                double upperState = 0.66;
                double lowerState = 0.33;
                if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_SETTLING ) {
                    if(slideOffset >= upperState){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }
                    if(slideOffset > lowerState && slideOffset < upperState){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                    }
                    if(slideOffset <= lowerState){
                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }
            }
        });

        setRecyclerView();

    }

    private void setRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this,4);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(false);
        bottomAdapter = new BottomAdapter(Utils.getImages());
        recyclerView.setAdapter(bottomAdapter);
        bottomAdapter.onSized(new BottomAdapter.OnSizeListener() {
            @Override
            public void onClicked(int height, int width) {
                Toast.makeText(MainActivity.this,
                        "Height is : " + height + " Width is : " + width +
                                "\n Row Count is : " + bottomAdapter.getItemCount(),Toast.LENGTH_LONG).show();
            }
        });
    }
}