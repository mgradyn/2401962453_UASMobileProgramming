package com.app.a2401962453_uasmobileprogramming.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.adapter.TicketListAdapter;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentTicketListBinding;
import com.app.a2401962453_uasmobileprogramming.model.Ticket;

import java.util.ArrayList;
import java.util.List;


public class TicketListFragment extends Fragment {

    private FragmentTicketListBinding binding;
    private TicketListAdapter adapter;
    List<Ticket> ticketList = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket_list, container, false);
    }
}