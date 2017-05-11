package com.example.rakesh.workout;

import android.app.FragmentTransaction;
import android.app.FragmentManager;  //remember to use android.app.FragmentManager(not use support.v4, in its import statement.)
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements WorkoutListFragment.WorkoutListListener {
    WorkoutDetailFragment frag;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // frag=(WorkoutDetailFragment)fm.findFragmentById(R.id.detail_frag);  //to attach fragment to this activity
       // frag.setWorkout(1);

    }

    @Override
    public void itemClicked(long id) {
        View frameLayout = findViewById(R.id.frame_layout);
        if (frameLayout != null) {                             //if there is frameLayout, use 2nd fragment for tablet
            frag = new WorkoutDetailFragment();
            FragmentManager fm = getFragmentManager();   //not use support.v4.app.FragmentManager, in its import statement
            FragmentTransaction ft = fm.beginTransaction();
            frag.setWorkout(id);                               //giving id of selected array item
            ft.replace(R.id.frame_layout, frag);          //replace the fragment
            ft.addToBackStack(null);                       //and add it to back stack
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);  //set animation while repalcing the frames
            ft.commit();        //commit the transaction
        } else{                                                  // else use 2nd activity for smartphones
            Intent intent=new Intent(MainActivity.this,FragDetailActivity.class);
            intent.putExtra("workid",(int) id);  //giving id of selected array item
            startActivity(intent);
        }
    }
}
