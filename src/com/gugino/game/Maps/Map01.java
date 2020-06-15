package com.gugino.game.Maps;

import java.util.HashMap;

import com.gugino.engine.tilemaps.MapInformation;

public class Map01 extends MapInformation {


    public String[] pattern = new String[] {
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,p,X,X,X,X,p,X,X,X,p,X,p,q,q,q,q,X,q,q,q,q,X,q,q,q,p,X,p,q,q,q,X,X",
        "X,X,X,p,X,X,X,X,p,X,X,X,p,X,p,X,X,X,X,X,X,p,p,X,X,X,X,X,p,X,p,X,X,X,p,X",
        "X,X,X,p,X,X,X,X,p,X,X,X,p,X,p,X,X,X,X,X,X,p,p,X,X,p,q,q,p,X,p,X,p,X,p,X",
        "X,X,X,p,X,X,X,X,p,X,X,X,p,X,p,X,X,X,X,X,X,p,p,X,X,p,X,X,X,X,p,X,X,X,p,X",
        "X,X,X,p,q,q,q,X,p,q,q,q,p,X,p,q,q,q,q,X,q,q,q,q,X,p,q,q,q,X,p,q,q,q,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X",
        "X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X,X"
    };


    public HashMap<String, String> key = new HashMap<>();
    

    public Map01() {
       mapPattern = pattern;

        key.put("X", "tile_0");
        key.put("p", "tile_1");
        key.put("q", "tile_2");

       mapKey = key;
    }
}