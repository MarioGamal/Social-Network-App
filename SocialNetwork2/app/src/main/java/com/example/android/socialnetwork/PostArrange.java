package com.example.android.socialnetwork;

import java.util.Comparator;

/**
 * Created by Martin on 5/10/2018.
 */

public class PostArrange implements Comparator<post> {

    public int compare (post a , post b ){
        if(a.id_of_the_post > b.id_of_the_post) return 1 ;
        else if(a.id_of_the_post < b.id_of_the_post) return -1 ;
        else return 0 ;
    }
}
