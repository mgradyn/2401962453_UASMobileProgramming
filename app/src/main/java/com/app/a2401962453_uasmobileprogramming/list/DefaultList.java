package com.app.a2401962453_uasmobileprogramming.list;

import com.app.a2401962453_uasmobileprogramming.config.NetworkConfig;
import com.app.a2401962453_uasmobileprogramming.model.CinemaDate;
import com.app.a2401962453_uasmobileprogramming.model.CinemaRoom;
import com.app.a2401962453_uasmobileprogramming.model.CinemaTime;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class DefaultList {
    public static final List<CinemaRoom> cinemaRoomList = Arrays.asList(
            new CinemaRoom("Bronze", "cinema1"),
            new CinemaRoom("Silver", "cinema2"),
            new CinemaRoom("Gold", "cinema3"),
            new CinemaRoom("Emerald", "cinema4"),
            new CinemaRoom("Diamond", "cinema5")
    );

    public static final List<CinemaDate> cinemaDateList = Arrays.asList(
            new CinemaDate(new Date()),
            new CinemaDate(new Date(new Date().getTime() + 86400000)),
            new CinemaDate(new Date(new Date().getTime() + 86400000 * 2)),
            new CinemaDate(new Date(new Date().getTime() + 86400000 * 3)),
            new CinemaDate(new Date(new Date().getTime() + 86400000 * 4)),
            new CinemaDate(new Date(new Date().getTime() + 86400000 * 5))
    );

    public static final List<CinemaTime> cinemaTimeList = Arrays.asList(
            new CinemaTime(11, 0),
            new CinemaTime(13, 30),
            new CinemaTime(15, 0),
            new CinemaTime(17, 30),
            new CinemaTime(19, 0),
            new CinemaTime(21, 30)
    );

}
