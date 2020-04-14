package com.tse.newhabit;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView mRView_cell;
    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle saveInstanceState){
        super.onViewCreated(view ,saveInstanceState);
        mRView_cell = view.findViewById(R.id.RView_cell);
        mRView_cell.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRView_cell.setAdapter(new RViewAdapter(getActivity()));

    }
}
