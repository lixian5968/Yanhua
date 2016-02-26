package com.yjmfortune.motherhappynewyear;

import java.io.Serializable;

/**
 * Created by lixian on 2016/2/2.
 */
public class MyYanhuaBean implements Serializable {

    public  int mPlayID;
    public int x;
    public  int y;
    public  int trail;
    public boolean open =true;
    public  int endY;
    public  int type;
    public MyYanhuaBean() {
    }

    public MyYanhuaBean(int mPlayID, int x, int y, int trail, boolean open, int endY, int type) {
        this.mPlayID = mPlayID;
        this.x = x;
        this.y = y;
        this.trail = trail;
        this.open = open;
        this.endY = endY;
        this.type = type;
    }
}
