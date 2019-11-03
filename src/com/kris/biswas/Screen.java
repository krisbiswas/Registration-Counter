package com.kris.biswas;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Screen {
    String name;
    int rows;
    int column;         //total seats per row
    int[][] seats;
    int[] aisle;

    Screen(String name,int rows,int totalSeatsPerRow, int[] aisle){
        this.name = name;
        this.rows = rows;
        this.column = totalSeatsPerRow;
        this.aisle = aisle;
        this.seats = new int[this.rows][column];
    }

    private boolean isAvailable(int row,int position){
        return seats[row-1][position-1] == 0;
    }
    public boolean reserve(int row,int[] columns){
        for(int position:columns){
            if(isAvailable(row,position)){
                seats[row-1][position-1] = 1;
            }else{
                return false;
            }
        }
        return true;
    }

    public List<Integer> availableSeats(int row){
        List <Integer> availableSeats = new LinkedList<Integer>();
        for(int i=1;i<=this.column;i++) {
            if (isAvailable(row,i)) {
                availableSeats.add(i);
            }
        }
        return availableSeats;
    }
}
