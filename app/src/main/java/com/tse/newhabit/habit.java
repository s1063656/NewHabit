package com.tse.newhabit;

import java.util.ArrayList;

public class habit {
    private String Title;
    private Boolean [] check = new Boolean [30];

    public habit(String t){
         this.Title = t;
         init();
    }
    public void init(){
        for(int i=0;i<30;i++){
            check[i]=false;
        }
    }
}
