package com.app.a2401962453_uasmobileprogramming.list;

import com.app.a2401962453_uasmobileprogramming.config.NetworkConfig;
import com.app.a2401962453_uasmobileprogramming.model.CinemaRoom;

import java.util.Arrays;
import java.util.List;


public class DefaultList {
    public static final List<CinemaRoom> cinemaRoomList = Arrays.asList(
            new CinemaRoom("Bronze", "cinema1"),
            new CinemaRoom("Silver", "cinema2"),
            new CinemaRoom("Gold", "cinema3"),
            new CinemaRoom("Emerald", "cinema4"),
            new CinemaRoom("Diamond", "cinema5")
    );

}
