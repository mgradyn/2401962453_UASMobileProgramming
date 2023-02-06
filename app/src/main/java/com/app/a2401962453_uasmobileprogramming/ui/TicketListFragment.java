package com.app.a2401962453_uasmobileprogramming.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.a2401962453_uasmobileprogramming.MainActivity;
import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.adapter.TicketListAdapter;
import com.app.a2401962453_uasmobileprogramming.database.AppDatabase;
import com.app.a2401962453_uasmobileprogramming.database.DBReceiver;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentTicketListBinding;
import com.app.a2401962453_uasmobileprogramming.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class TicketListFragment extends Fragment {

    private FragmentTicketListBinding binding;
    private TicketListAdapter adapter;
    List<Ticket> ticketList = new ArrayList<>();
    private DBReceiver dbreceiver;
    private AppDatabase db;

    private final Executor executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dbreceiver = (DBReceiver) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        getTickets();
        db = dbreceiver.getDB();
        // Inflate the layout for this fragment
        binding = FragmentTicketListBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    private void getTickets() {
        executor.execute(() ->
        {
            ticketList = db.ticketDao().getAll();
            handler.post(() -> {
                if (!ticketList.isEmpty()) {
                    setAdapter();
                }
                else {
                    Log.w("exception", "Ticket List Exception");
                }
            });
        });
    }

    private void setAdapter() {
        adapter = new TicketListAdapter((MainActivity)getActivity(), ticketList);
        binding.ticketRecyclerView.setAdapter(adapter);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.ticketRecyclerView.setLayoutManager(new LinearLayoutManager((MainActivity)getActivity()));
        binding.ticketRecyclerView.setHasFixedSize(true);
        
        super.onViewCreated(view, savedInstanceState);
    }



}