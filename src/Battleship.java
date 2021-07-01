public class Battleship extends Boat implements Attacker{
    public Battleship(int team, Coordinates location, int direction){
        super(team, location, direction, 4, 3,1);
    }

    public String getID(){
        return "B" + getTeam();
    }

    public String getActions() {
        return "Choose any of the following actions for Battleship:\n" +
                "1. Move\n" +
                "2. Turn left\n" +
                "3. Turn right\n" +
                "4. Attack";
    }

    public String act(int[] choice, World map, int round){
        if(choice[0] == 1){
            return move(map);
        }
        if(choice[0] == 2){
            return turn(-1);
        }
        if(choice[0] == 3){
            return turn(1);
        }
        else{
            return attack(map);
        }
    }

    public String attack(World map){
        Coordinates target = new Coordinates(getLocation().getX(),getLocation().getY());
        Boat attackedBoat = map.getOccupant(map.getAdjacentLocation(target,getDirectionNum()));
        for(int i = 0; i < getVision(); i++){
            if(attackedBoat != null){
                return "Fire cannons! " + attackedBoat.takeHit(getStrength());
            }
            else{
                target = map.getAdjacentLocation(target,getDirectionNum());
            }
        }
        return "There are no boats in range currently.";
    }

}
