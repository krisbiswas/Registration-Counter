package com.kris.biswas;

//import javax.sound.midi.SysexMessage;
//import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Screen> screens = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
//        File f = new File("C:\\Users\\Krishnandu Biswas\\IdeaProjects\\Reservation Counter\\src\\com\\kris\\biswas\\inputs.txt");

        while (true){
            System.out.print("add-screen <Screen name> <no. of rows> <total seats per row> <aisle seats>\n" +
                    "reserve-seats <screen name> <row> <seat No.>\nget-available-seats <Screen name> <row>\t");
            String input = sc.nextLine();
            String[] command = input.split(" ");
            switch (command[0]){
                case "add-screen":
                    if(command[1].matches(".*\\d.*")){
                        System.out.println("failure: Invalid screen name");break;
                    }else{
                        for(Screen s:screens){
                            if(s.name.equalsIgnoreCase(command[1])){
                                System.out.print("failure: screen already exists");
                            }
                        }
                        int rows = Integer.parseInt(command[2]);
                        int tspr = Integer.parseInt(command[3]);
                        int[] aisle = new int[command.length-4];
                        for(int i=0;i<aisle.length;i++){
                            aisle[i] = Integer.parseInt(command[i+4]);
                        }
                        screens.add(new Screen(command[1],rows,tspr,aisle));
                        System.out.println("success");
                        break;
                    }
                case "reserve-seats":
                    if(screens.isEmpty()){
                        System.out.println("failure: no screens");break;
                    }
                    int row = Integer.parseInt(command[2]);
                    int[] seatRequest = new int[command.length-3];
                    for(int i=0;i<seatRequest.length;i++){
                        seatRequest[i] = Integer.parseInt(command[i+3]);
                    }
                    int screenCount = 0;
                    for(Screen s:screens){
                        if(s.name.equalsIgnoreCase(command[1])){
                            if(s.reserve(row,seatRequest)){
                                System.out.println("success");break;
                            }else{
                                System.out.println("failure:some seats are not available");break;
                            }
                        }else {
                            screenCount++;
                        }
                    }
                    if(screenCount == screens.size()){
                        System.out.println("failure: screen not found");
                    }
                    break;
                case "get-available-seats":
                    if(screens.isEmpty()){
                        System.out.println("failure: no screens");break;
                    }
                    screenCount = 0;
                    for(Screen s:screens){
                        if(s.name.equalsIgnoreCase(command[1])){
                            row = Integer.parseInt(command[2]);
                            for(Integer seat:s.availableSeats(row)){
                                System.out.print(seat+" ");
                            }
                            break;
                        }else{
                            screenCount++;
                        }
                    }
                    if(screenCount == screens.size()){
                        System.out.println("failure: screen not found");
                    }
                    break;
                case "get-contiguous-seats":
                    break;
            }
        }
    }
}
