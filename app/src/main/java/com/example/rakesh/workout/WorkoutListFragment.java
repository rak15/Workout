package com.example.rakesh.workout;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutListFragment extends ListFragment {      //list fragment does not need activity and won't have layout
    static interface WorkoutListListener{                    //creating an interface to communicate to other fragments through MainActivity
        void itemClicked(long id);
    }
    private WorkoutListListener listener;                     //interface reference variable

    public WorkoutListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String[] names = new String[Workout.workouts.length];
        for (int i = 0; i < names.length; i++) {
            names[i] = Workout.workouts[i].getName();                 //in fragment, we can't get array name directly as we did in activity
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                inflater.getContext(), android.R.layout.simple_list_item_1,      // in fragment, we can't use 'this' as context, so inflater.getContext()
                names);
        setListAdapter(adapter);               //to display array data in list view (in fragment)
        return super.onCreateView(inflater, container, savedInstanceState);  //Calling the superclass onCreateView() method gives you the default layout for the ListFragment
    }

    @Override
    public void onAttach(Activity activity) {  //here the activity as argument is the activity to which this fragment is attached
        super.onAttach(activity);
        this.listener=(WorkoutListListener)activity;  // casting activity to interface reference variable i.e. listener
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //super.onListItemClick(l, v, position, id);
        if (listener!=null){
            listener.itemClicked(id);   //itemClicked(id) method implemented in main activity and will be called by activity when an item in the ListView is clicked.
        }
    }
}
