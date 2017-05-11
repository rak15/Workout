package com.example.rakesh.workout;

import android.app.FragmentManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FragDetailActivity extends AppCompatActivity {
    WorkoutDetailFragment fdetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag_detail);
        fdetail=new WorkoutDetailFragment();
        FragmentManager fm=getFragmentManager();
        fdetail=(WorkoutDetailFragment)fm.findFragmentById(R.id.activity_frag_detail);  //converting 2nd activity layout into 2nd fragment layout
        Intent intent=getIntent();
        int work=intent.getIntExtra("workid",0);
        fdetail.setWorkout(work);
    }
}
