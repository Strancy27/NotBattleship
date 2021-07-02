import java.util.Scanner;

public class Game {


    public static void lineBreaks(){
          System.out.println("\n\n\n\n\n\n\n\n\n\n" +
          "\n\n\n\n\n\n\n\n\n\n" +
          "\n\n\n\n\n\n\n\n\n\n");
    }

    public static void waitForEnter(){
        try {
            System.in.read();
        }catch(Exception e){

        }
    }

    public static boolean boatsAlive(Boat[] team){
        for(int i = 0; i < team.length; i++){
            if(team[i].getHealth()>0){
                return true;
            }
        }
        int loser = team[0].getTeam();
        int winner = -1;
        if(loser == 1){
            winner = 2;
        }
        else{
            winner = 1;
        }
        System.out.print("Team " + loser + " has lost all of their ships. Team " + winner + " is the winner!");
        return false;
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);

        System.out.println("\nWelcome to the Game of !Battleship! A game of love, laugh, blood, sweat and tears\n" +
                "(Tears if you are the loser anyways lol.) The objective of the game is destroy all of the\n" +
                "your opponents boats using clever strategy. Each boat has a unique property so make sure \n" +
                "to use each of their skills to the best of your advantage.");

        int mapWidth = 10;
        int mapHeight = 10;

        //Creates the map and sets its size
        World map = new World(mapWidth,mapHeight);

        int boatsPerTeam = 6;
        //Creates array to hold all of Team one's Boats
        Boat[] teamOne = new Boat[boatsPerTeam];

        //Creates array to hold all of Team two's Boats
        Boat[] teamTwo = new Boat[boatsPerTeam];

        System.out.println(map.drawTeamMap(teamOne,1));

        // Stops Game if teams aren't the same size
        if(teamOne.length != teamTwo.length){
            System.out.println("Unfair Advantage!");
            System.exit(0);
        }

        //lineBreaks();
        System.out.println("Great now that we have that settled. It's time for one of you to leave. No don't leave \n" +
                "the room lol, just make sure you aren't in view of the computer anymore. If you are still\n" +
                "in front of the computer you are Player 1. Click enter to proceed.");
        waitForEnter();
        /*
        System.out.println("Welcome Player 1. To start pick a coordinate to place your AircraftCarrier. It must be Below the half way point" +
                "of the map.");



        Coordinates aircraftCarrierLocation = new Coordinate()
        s.next
        */



        Coordinates[][] startingPoints = new Coordinates[mapWidth][mapHeight];
        for(int i = 0; i < mapWidth; i++){
            for(int j = 0; j < mapHeight; j++){
                startingPoints[i][j] = new Coordinates(i,j);
            }
        }


        //Team Ones Boats
        teamOne[0] = new AircraftCarrier(1,startingPoints[5][9],0);
        teamOne[1] = new Battleship(1,startingPoints[4][9] ,0);
        teamOne[2] = new Cruiser(1,startingPoints[0][9],0);
        teamOne[3] = new Destroyer(1,startingPoints[2][9],0);
        teamOne[4] = new Destroyer(1,startingPoints[7][9],0);
        teamOne[5] = new Submarine(1,startingPoints[9][9],0);

        //Team Two's Boats
        teamTwo[0] = new AircraftCarrier(2,startingPoints[4][0],4);
        teamTwo[1] = new Battleship(2,startingPoints[5][0] ,4);
        teamTwo[2] = new Cruiser(2,startingPoints[9][0] , 4);
        teamTwo[3] = new Destroyer(2,startingPoints[7][0] ,4);
        teamTwo[4] = new Destroyer(2,startingPoints[2][0] ,4);
        teamTwo[5] = new Submarine(2,startingPoints[0][0] ,4);

        for(int i = 0; i<teamOne.length; i++){
            map.setOccupant(teamOne[i], teamOne[i].getLocation());
            map.setOccupant(teamTwo[i], teamTwo[i].getLocation());
        }
        //lineBreaks();
        System.out.println("Okay Great! Now we can start the actual game! Player 1, it's your turn. Press enter when\n" +
                "when ready.\n");
        int turn = 0;
        Boat[] team;
        int teamNum = 1;
        while(boatsAlive(teamOne) && boatsAlive(teamTwo)) {
            int mapView = 1;
            if(turn % 2 == 0){
                team = teamOne;
                teamNum = 1;
            }
            else{
                team = teamTwo;
                teamNum = 2;
            }
            System.out.println("---------------------------------------------------\n" +
                    "Player " + teamNum);
            while (mapView >= 1 && mapView <= 3) {
                System.out.println(map.drawTeamMap(team, mapView));
                System.out.println("1. Blind Map\n" +
                        "2. Direction Map\n" +
                        "3. Health \n" +
                        "4. Act"
                );
                mapView = s.nextInt();

            }

            for (int i = 0; i < team.length; i++) {
                if(team[i].getAlive()) {
                    System.out.println("\n" + team[i].getID() + " is located at " + team[i].getLocation() + " facing " + team[i].getDirection());
                    System.out.println(team[i].getActions());
                    int choice1 = s.nextInt();
                    int choice2 = 4;
                    if (team[i].getID().contains("C")) {
                        System.out.println("The Cruiser is blazing with speed. Choose your second action.\n" + team[i].getActions());
                        choice2 = s.nextInt();
                    }
                    int[] choices = {choice1, choice2};
                    System.out.println(team[i].act(choices, map, 0));
                }
            }
            turn++;
        }

    }
}
