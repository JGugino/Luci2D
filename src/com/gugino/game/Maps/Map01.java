package com.gugino.game.Maps;

import java.util.HashMap;

import com.gugino.engine.tilemaps.MapInformation;

public class Map01 extends MapInformation {


    public String[] pattern = new String[] {
        "X,X,P,X,X,X,X,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,P,X,X,X,X,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,P,P,P,P,P,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,P,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,P,P,P,P,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,P,X,X,X,X,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,P,X,X,X,X,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,P,P,P,P,P,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,P,X,X,X,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,P,P,P,P,P,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X"
    };


    public HashMap<String, String> key = new HashMap<>();
    

    public Map01() {
       mapPattern = pattern;

        key.put("X", "tile_0");
        key.put("P", "tile_1");

       mapKey = key;
    }
}