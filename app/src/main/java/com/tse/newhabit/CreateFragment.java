package com.tse.newhabit;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {
    private Button createBtn;
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
        Button createBtn = (Button) view.findViewById(R.id.create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                View dView = LayoutInflater.from(getActivity()).inflate(R.layout.create_dialog,null);
                final TimePicker timePicker = (TimePicker)dView.findViewById(R.id.time_picker);

                timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
                    @Override
                    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                        Toast.makeText(getActivity(),"新增提醒："+hourOfDay+"時"+minute+"分",Toast.LENGTH_SHORT).show();
                    }
                });
                timePicker.setIs24HourView(true);
                builder.setView(dView);
                builder.show();
            }
        });
    }
}
