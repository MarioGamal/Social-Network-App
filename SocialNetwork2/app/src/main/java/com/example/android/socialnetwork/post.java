package com.example.android.socialnetwork;

import java.util.Comparator;
import java.util.Vector;

/**
 * Created by Martin on 5/1/2018.
 */

public class post {
    String content;
    int no_likes;
    Vector<user>liked;
    int id_of_the_post;
    int id_of_the_user;

    public int getId_of_the_post() {
        return id_of_the_post;
    }

    public void setId_of_the_post(int id_of_the_post) {
        this.id_of_the_post = id_of_the_post;
    }

    public post(){
        content = "";

        no_likes = -1;
        id_of_the_user=-1;
        id_of_the_post=-1;
    }

    public post(String content, int no_likes, Vector<user> liked, int id_of_the_post, int id_of_the_user) {
        this.content = content;
        this.no_likes = no_likes;
        this.liked = liked;
        this.id_of_the_post = id_of_the_post;
        this.id_of_the_user = id_of_the_user;
    }

    public post(String content , int no_likes , int id_of_the_post , int id_of_the_user){
        this.content=content;
        this.no_likes=no_likes;
        this.id_of_the_post = id_of_the_post;
        this.id_of_the_user = id_of_the_user;
    }

    public post(String content , int no_likes , int id_of_the_post){
        this.content = content ;
        this.no_likes = no_likes ;
        this.id_of_the_post = id_of_the_post ;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNo_likes() {
        return no_likes;
    }

    public void setNo_likes(int no_likes) {
        this.no_likes = no_likes;
    }

    public Vector<user> getLiked() {
        return liked;
    }

    public void setLiked(Vector<user> liked) {
        this.liked = liked;
    }


}
