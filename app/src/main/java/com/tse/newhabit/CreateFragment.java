package com.tse.newhabit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {
    private Button createBtn;
    private RecyclerView drv;

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState) {
        super.onViewCreated(view ,saveInstanceState);
        final EditText title = (EditText) view.findViewById(R.id.create_newHabit);
        Button createBtn = (Button) view.findViewById(R.id.create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.arr.add(new habit(title.getText().toString().trim()));
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dView = LayoutInflater.from(getActivity()).inflate(R.layout.create_dialog,null);
                final Button done = (Button) dView.findViewById(R.id.done);
                final Button set = (Button) dView.findViewById(R.id.setAlarm);
                final TimePicker timePicker = (TimePicker)dView.findViewById(R.id.time_picker);
                drv = dView.findViewById(R.id.drv);
                drv.setLayoutManager(new LinearLayoutManager(getActivity()));
                drv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                drv.setAdapter(new dialogRV(getActivity()));
                drv.setItemViewCacheSize(30);
                set.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.arr.get(MainActivity.arr.size()-1).addRemindTime(timePicker.getHour(),timePicker.getMinute());
                    }
                });
                timePicker.setIs24HourView(true);
                builder.setView(dView);
                final AlertDialog close = builder.show();
                done.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.arr.get(MainActivity.arr.size()-1).showAlarm();
                        close.dismiss();
                    }
                });
            }
        });
    }
}
