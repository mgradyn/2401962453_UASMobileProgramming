package com.app.a2401962453_uasmobileprogramming.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.app.a2401962453_uasmobileprogramming.MainActivity;
import com.app.a2401962453_uasmobileprogramming.R;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaDateListAdapter;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaRoomListAdapter;
import com.app.a2401962453_uasmobileprogramming.adapter.CinemaTimeListAdapter;
import com.app.a2401962453_uasmobileprogramming.database.AppDatabase;
import com.app.a2401962453_uasmobileprogramming.database.DBReceiver;
import com.app.a2401962453_uasmobileprogramming.databinding.FragmentBookingBinding;
import com.app.a2401962453_uasmobileprogramming.model.CinemaDate;
import com.app.a2401962453_uasmobileprogramming.model.CinemaTime;
import com.app.a2401962453_uasmobileprogramming.model.Result;
import com.app.a2401962453_uasmobileprogramming.model.Ticket;
import com.app.a2401962453_uasmobileprogramming.tool.OnDateCardClickListener;
import com.app.a2401962453_uasmobileprogramming.tool.OnRoomCardClickListener;
import com.app.a2401962453_uasmobileprogramming.tool.OnTimeCardClickListener;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookingFragment extends Fragment implements OnRoomCardClickListener, OnDateCardClickListener, OnTimeCardClickListener {
    private FragmentBookingBinding binding;
    private Spinner locationSpinner;
    private Result movieDetail;
    BottomNavigationView bottomNavigationView;
    private CinemaRoomListAdapter cinemaRoomListAdapter;
    private CinemaDateListAdapter cinemaDateListAdapter;
    private CinemaTimeListAdapter cinemaTimeListAdapter;

    private Ticket ticket = null;
    private String cinemaLocation = null;
    private String roomType = null;
    private String roomPrice = null;
    private CinemaDate cinemaDate = null;
    private CinemaTime cinemaTime = null;

    private DBReceiver dbreceiver;
    private AppDatabase db;
    private Bundle bundle;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        dbreceiver = (DBReceiver) context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        bundle = new Bundle();
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (getArguments() != null) {
            movieDetail = getArguments().getParcelable("movieDetail");
        }
        else {
            movieDetail = null;
        }

        cinemaRoomListAdapter = new CinemaRoomListAdapter((MainActivity)getActivity(), this);
        cinemaDateListAdapter = new CinemaDateListAdapter((MainActivity)getActivity(), this);
        cinemaTimeListAdapter = new CinemaTimeListAdapter((MainActivity)getActivity(), this);

        db = dbreceiver.getDB();

        // Inflate the layout for this fragment
        binding = FragmentBookingBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        bottomNavigationView = getActivity().findViewById(R.id.bottomNavigationView);
        binding.tvTotalPrice.setVisibility(View.GONE);
        binding.tvPriceTitle.setVisibility(View.GONE);

        Glide.with(this).
                load("https://image.tmdb.org/t/p/w500" + movieDetail.getPosterPath()).
                into(binding.ivBackground);
        binding.tvMovieName.setText(movieDetail.getTitle());
        setLocationSpinner();

        setCinemaRoomAdapter();
        setCinemaDateAdapter();
        setCinemaTimeAdapter();

        binding.btnProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("movieDetail",movieDetail);

                String name = binding.etName.getText().toString();
                String email = binding.etEmail.getText().toString();
                boolean timeHasPass = false;
                if (cinemaDate != null && cinemaTime != null) {
                    Date selectedDate = cinemaDate.getFullDate();
                    int hour = cinemaTime.getHour();
                    int minute = cinemaTime.getMinute();
                    Date currentDate = new Date();

                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(selectedDate);
                    calendar.set(Calendar.HOUR_OF_DAY, hour);
                    calendar.set(Calendar.MINUTE, minute);
                    selectedDate = calendar.getTime();

                    if (selectedDate.compareTo(currentDate) <= 0) {
                        timeHasPass = true;
                    }
                }

                if (TextUtils.isEmpty(name)) {
                    makeToast("Please enter name");
                }
                else if (!isValidEmail(email)) {
                    makeToast("Please enter valid email");
                }
                else if (cinemaLocation == null) {
                    makeToast("Please select the location");
                }
                else if (roomType == null) {
                    makeToast("Please select the room");
                }
                else if (cinemaDate == null) {
                    makeToast("Please select the date");
                }
                else if (cinemaTime == null) {
                    makeToast("Please select the time");
                }
                else if (timeHasPass) {
                    makeToast("Please select another time");
                }
                else {
                    ticket = new Ticket(movieDetail.getTitle(), cinemaLocation, roomType, roomPrice, generateDate(cinemaDate.getFullDate()), cinemaTime.getTime(), name, email);
                    insertDB(ticket);
                    makeToast("Booking Success");
                    bundle.putParcelable("ticketDetail", ticket);
                    Navigation.findNavController(view).navigate(R.id.action_bookingFragment_to_ticketFragment, bundle);
                }
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onStart() {
        bottomNavigationView.setVisibility(View.GONE);
        super.onStart();
    }

    @Override
    public void onStop() {
        bottomNavigationView.setVisibility(View.VISIBLE);
        super.onStop();
    }

    private void setLocationSpinner() {
        ArrayAdapter<CharSequence> locationAdapter = ArrayAdapter.createFromResource(
                getContext(),
                R.array.cinema_locations,
                android.R.layout.simple_spinner_item
        );
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.sCinemaLocation.setAdapter(locationAdapter);
        binding.sCinemaLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                cinemaLocation = adapterView.getItemAtPosition(i).toString();
                Log.d("cinemaLocation", cinemaLocation);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setCinemaRoomAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager((MainActivity)getActivity(), LinearLayoutManager.HORIZONTAL, false);
        binding.cinemaRoomRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaRoomRecyclerView.setHasFixedSize(true);
        binding.cinemaRoomRecyclerView.setAdapter(cinemaRoomListAdapter);
    }

    private void setCinemaDateAdapter() {
        LinearLayoutManager layoutManager
                = new LinearLayoutManager((MainActivity)getActivity(), LinearLayoutManager.HORIZONTAL, false);

        binding.cinemaDateRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaDateRecyclerView.setHasFixedSize(true);
        binding.cinemaDateRecyclerView.setAdapter(cinemaDateListAdapter);
    }

    private void setCinemaTimeAdapter() {
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);

        binding.cinemaTimeRecyclerView.setLayoutManager(layoutManager);
        binding.cinemaTimeRecyclerView.setHasFixedSize(true);
        binding.cinemaTimeRecyclerView.setAdapter(cinemaTimeListAdapter);
    }

    @Override
    public void onCardClick(String type, Double price) {
        Log.d("roomType", type);
        String priceText = "Rp. " + price.toString() + "00";
        binding.tvTotalPrice.setText(priceText);
        binding.tvPriceTitle.setVisibility(View.VISIBLE);
        binding.tvTotalPrice.setVisibility(View.VISIBLE);
        this.roomType = type;
        this.roomPrice = priceText;
    }

    @Override
    public void onCardClick(Date fullDate, String day, String date) {
        Log.d("cinemaDate", date);
        Log.d("cinemaDay", day);
        Log.d("cinemaFullDate", fullDate.toString());
        this.cinemaDate = new CinemaDate(fullDate);
    }

    @Override
    public void onCardClick(String time, int hour, int minute) {
        Log.d("cinemaTime", time);
        Log.d("cinemaHour", String.valueOf(hour));
        Log.d("cinemaMinute", String.valueOf(minute));
        this.cinemaTime = new CinemaTime(hour, minute);

    }

    private void makeToast(String msg) {
        Toast.makeText(
                getContext(), msg, Toast.LENGTH_SHORT
        ).show();
    }

    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private String generateDate(Date fullDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("d MMM yyyy");
        String strDate = formatter.format(fullDate);
        return strDate;
    }

    private void insertDB(Ticket ticket) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                db.ticketDao().insertAll(ticket);
            }
        });
    }
}