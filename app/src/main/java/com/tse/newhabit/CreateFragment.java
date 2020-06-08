package com.tse.newhabit;


import android.app.AlertDialog;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;


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
        final Intent it = getActivity().getIntent();
        final EditText title = (EditText) view.findViewById(R.id.create_newHabit);
        final ArrayList<Habit> habitList =  MainActivity.HabitList;
        Button createBtn = (Button) view.findViewById(R.id.create_btn);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                final View dView = LayoutInflater.from(getActivity()).inflate(R.layout.create_dialog,null);
                final dialogRV DAdapter = new dialogRV(getActivity());
                    drv = dView.findViewById(R.id.drv);
                    drv.setLayoutManager(new LinearLayoutManager(getActivity()));
                    drv.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
                    drv.setAdapter(DAdapter);
                    drv.setItemViewCacheSize(21);
                final Button done = (Button) dView.findViewById(R.id.done);
                final Button set = (Button) dView.findViewById(R.id.setAlarm);
                final TimePicker timePicker = (TimePicker)dView.findViewById(R.id.time_picker);
                if(title.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"請輸入一項習慣",Toast.LENGTH_LONG).show();
                }else{
                    habitList.add(new Habit(title.getText().toString().trim()));
                    set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            final ArrayList<Calendar> calendars = habitList.get(habitList.size()-1).getCalendar();
                            Calendar tempCalendar = Calendar.getInstance();
                            tempCalendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                            tempCalendar.set(Calendar.MINUTE,timePicker.getMinute());
                            if(habitList.get(habitList.size()-1).addRemindTime(getActivity(),tempCalendar)){
                                DAdapter.addData(habitList.get(habitList.size()-1).getNumOfAlarm(),habitList.get(habitList.size()-1).rData());
                                DAdapter.notifyItemChanged(habitList.get(habitList.size()-1).getNumOfAlarm());
                            }else{
                                Toast.makeText(getActivity(),"已存在相同時間提醒",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    timePicker.setIs24HourView(true);
                    builder.setView(dView);
                    final AlertDialog close = builder.show();
                    done.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            habitList.get(habitList.size()-1).showAlarm();
                            title.setText("");
                            close.dismiss();
                            Habit item = MainActivity.HabitList.get(MainActivity.HabitList.size()-1);
                            FirebaseFirestore db = FirebaseFirestore.getInstance();
                            Map<String,Object> habit = new HashMap<>();
                            habit.put("habitName",item.getTitle());
                            habit.put("beginDate",item.getDateTime());
                            habit.put("alarms",item.getHabitAlarmList());
                            db.collection(it.getStringExtra("USERID"))
                                    .add(habit)
                                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                        @Override
                                        public void onSuccess(DocumentReference documentReference) {
                                            Log.d("response","成功:"+documentReference.getId());
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Log.w("response","失敗:",e);
                                        }
                                    });
                        }
                    });
            }}
        });

    }
}
