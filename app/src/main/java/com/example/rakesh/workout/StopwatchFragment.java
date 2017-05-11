package com.example.rakesh.workout;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class StopwatchFragment extends Fragment {
    TextView tv;
    Button sta,sto,res;
    private int deciseconds;
    private boolean running;
    private boolean wasrunning;
    public StopwatchFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout= inflater.inflate(R.layout.fragment_stopwatch, container, false);
        if (savedInstanceState!=null) {
            deciseconds=savedInstanceState.getInt("deciseconds");
            running=savedInstanceState.getBoolean("running");
            wasrunning=savedInstanceState.getBoolean("wasrunning");
            if (wasrunning)
                running = true;
        }
        runTimer(layout);
        sta=(Button)layout.findViewById(R.id.button);
        sto=(Button)layout.findViewById(R.id.button2);
        res=(Button)layout.findViewById(R.id.button3);

        sta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=true;
            }
        });
        sto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;
            }
        });
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running=false;
                deciseconds=0;
            }
        });
        return layout;
    }

    @Override
    public void onPause() {
        super.onPause();
        wasrunning=running;
        running=false;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (wasrunning)
            running=true;
    }

    private void runTimer(final View view){
        final Handler handler=new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv=(TextView)view.findViewById(R.id.textView3);
                int decisecs=deciseconds%10;
                int secs=(deciseconds/10)%60;
                int mins=(deciseconds/600)%60;
                int hrs=deciseconds/360000;
                String time=String.format("%d:%02d:%02d:%d",hrs,mins,secs,decisecs);
                tv.setText(time);
                if (running)
                    deciseconds++;
                handler.postDelayed(this,100);
            }
        });
    }
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt("deciseconds",deciseconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasrunning",wasrunning);
        //super.onSaveInstanceState(outState);
    }
}
