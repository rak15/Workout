package com.example.rakesh.workout;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {
    private long workoutId;  //This is the ID of the workout the user chooses. Later, we’ll use it
                              //to set the values of fragment’s views with the workout details
    public WorkoutDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (savedInstanceState != null) {                         // to save the prev value of workoutid while rotating
            workoutId = savedInstanceState.getLong("workoutid");
        }else {
            StopwatchFragment stopwatchFragment = new StopwatchFragment();
            FragmentManager fm = getChildFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.stopwatch_container, stopwatchFragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);   //to attach layout to its fragment
    }

    @Override
    public void onStart() {
        super.onStart();
        View v=getView();
        if (v!=null) {
            TextView title = (TextView) v.findViewById(R.id.textView);
            TextView description = (TextView) v.findViewById(R.id.textView2);
            Workout workout=Workout.workouts[(int)workoutId];
            title.setText(workout.getName());
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(long id) {    //This is a setter method for the workout ID.
        this.workoutId = id;             //The main activity will use this method to set the value of the workout ID.
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //super.onSaveInstanceState(outState);
        savedInstanceState.putLong("workoutid",workoutId);
    }
}
