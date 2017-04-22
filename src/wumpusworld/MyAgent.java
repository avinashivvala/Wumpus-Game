package wumpusworld;

/**
 * Contains starting code for creating your own Wumpus World agent.
 * Currently the agent only make a random decision each turn.
 * 
 * @author Johan Hagelbäck
 */
public class MyAgent implements Agent
{

    /**
     *
     */
    @SuppressWarnings("FieldMayBeFinal")
    private World w;
    int rnd;
    static int reward[][] = {{-10000,-10000,-10000,-10000,-10000,-10000},
                                {-10000,0,0,0,0,-10000},
                                {-10000,0,0,0,0,-10000},
                                {-10000,0,0,0,0,-10000},
                                {-10000,0,0,0,0,-10000},
                                {-10000,-10000,-10000,-10000,-10000,-10000}};
    static int simulation = 1;
    //static int step = 1;
    
    /**
     * Creates a new instance of your solver agent.
     * 
     * @param world Current world state 
     */
    public MyAgent(World world)
    {
        w = world;   
    }
   
            
    /**
     * Asks your solver agent to execute an action.
     */

    @Override
    @SuppressWarnings("UnnecessaryReturnStatement")
    public void doAction()
    {
        //Location of the player
        int cX = w.getPlayerX();
        int cY = w.getPlayerY();
        //System.out.print(cX+"--"+cY);
        
        //Basic action:
        //Grab Gold if we can.
        if (w.hasGlitter(cX, cY))
        {
            w.doAction(World.A_GRAB);
            return;
        }
        
        //Basic action:
        //We are in a pit. Climb up.
        if (w.isInPit())
        {
            w.doAction(World.A_CLIMB);
            return;
        }
        
        //Test the environment
        if (w.hasBreeze(cX, cY))
        {
            System.out.println("I am in a Breeze");
        }
        if (w.hasStench(cX, cY))
        {
            System.out.println("I am in a Stench");
        }
        if (w.hasPit(cX, cY))
        {
            System.out.println("I am in a Pit");
        }
        if (w.getDirection() == World.DIR_RIGHT)
        {
            System.out.println("I am facing Right");
        }
        if (w.getDirection() == World.DIR_LEFT)
        {
            System.out.println("I am facing Left");
        }
        if (w.getDirection() == World.DIR_UP)
        {
            System.out.println("I am facing Up");
        }
        if (w.getDirection() == World.DIR_DOWN)
        {
            System.out.println("I am facing Down");
        }
        
        if(w.isUnknown(2, 1) && w.isUnknown(1, 2)){
            //System.out.println("unknown position");
            simulation = 1;
            for(int a=1; a<=4; a++){
                for(int b=1; b<=4; b++){
                    reward[a][b] = 0;
                }
            }
        }
        /*if(w.isVisited(2, 1)){
            System.out.println("known position");
        }*/
        if(simulation == 1) {
            System.out.println("simulation");
            bestmove();
            simulation = 0;
        }
        /*for(int i=1;i<5;i++){
            for(int j=1;j<5;j++){
                System.out.print(reward[i][j]);
                System.out.print(".");
            }
            System.out.println(".");
        }*/
        
        //System.out.println("out of simulation");
        //System.out.println(reward[cX+1][cY]+" "+reward[cX][cY+1]+" "+reward[cX][cY-1]+" "+reward[cX-1][cY]);
        
        
       
       
       if(reward[cX+1][cY]>=reward[cX][cY+1] && reward[cX+1][cY]>=reward[cX][cY-1] && reward[cX+1][cY]>=reward[cX-1][cY]) {
           
            
            if(w.getDirection() == 1){
                if ((reward[cX+1][cY] == 123456 || reward[cX+1][cY] == -501) && w.wumpusAlive()){
                    w.doAction(World.A_SHOOT);
                    return;
                }
                w.doAction(World.A_MOVE);
            }
            else {
                if(w.getDirection() == 2 || w.getDirection() == 3 )
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                return;
            }
        }
       else if(reward[cX][cY+1]>=reward[cX-1][cY] && reward[cX][cY+1]>=reward[cX][cY-1] && reward[cX][cY+1]>=reward[cX+1][cY]) {
            if(w.getDirection() == 0){
                if ((reward[cX][cY+1] == 123456 || reward[cX][cY+1] == -501) && w.wumpusAlive()){
                    w.doAction(World.A_SHOOT);
                    return;
                }
                w.doAction(World.A_MOVE);
            }
            else {
                if(2 - w.getDirection() >= 0)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                return;
            }
        }
        else if(reward[cX-1][cY]>=reward[cX][cY-1] && reward[cX-1][cY]>=reward[cX+1][cY] && reward[cX-1][cY]>=reward[cX][cY+1]) {
            if(reward[cX+1][cY] == 0){
               w.doAction(World.A_MOVE);
               return;
           }
            if(w.getDirection() == 3){
                if ((reward[cX-1][cY] == 123456 || reward[cX-1][cY] == -501) && w.wumpusAlive()){
                    w.doAction(World.A_SHOOT);
                    return;
                }
                w.doAction(World.A_MOVE);
            }
            else {
                if(w.getDirection() == 0 || w.getDirection() == 1)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                return;
            }
        }
        else if(reward[cX][cY-1]>=reward[cX+1][cY] && reward[cX][cY-1]>=reward[cX][cY+1] && reward[cX][cY-1]>=reward[cX-1][cY]) {
             if(w.getDirection() == 2){
                if ((reward[cX][cY-1] == 123456 || reward[cX][cY-1] == -501) && w.wumpusAlive()){
                    w.doAction(World.A_SHOOT);
                    return;
                }
                w.doAction(World.A_MOVE);
            }
            else {
                if(w.getDirection() == 3 || w.getDirection() == 0)
                    w.doAction(World.A_TURN_LEFT);
                else
                    w.doAction(World.A_TURN_RIGHT);
                return;
            }
        }
      
    }    
     
    public void bestmove() {
        //System.out.println("inside bestmove"+x+"----------"+y);
        
        int xx = w.getPlayerX();
        int yy = w.getPlayerY();
        int step = 1;
        //System.out.println("inside bestmove"+xx+"----------"+yy);
        reward[xx][yy] = 1;
        //System.out.println(reward[xx][yy]);
        for (int i = 1; i >=0; i--) {
            World ww = w.cloneWorld();
        int x = ww.getPlayerX();
        int y = ww.getPlayerY();
            if (ww.getDirection() == i) {
                if(i==0 && ww.isValidPosition(x, y+1)) {
                    ww.doAction(World.A_MOVE);
                    int j=nextmove(ww, step);
                }
                else if(i==1 && ww.isValidPosition(x+1, y)){
                    //System.out.println("first move");
                    ww.doAction(World.A_MOVE);
                    int j=nextmove(ww, step);
                }
                else if(i==2 && ww.isValidPosition(x, y-1)){
                    ww.doAction(World.A_MOVE);
                    int j=nextmove(ww, step);
                }
                else if(i==3 && ww.isValidPosition(x-1, y))
                    ww.doAction(World.A_MOVE);
                
            }
            else {
                while(i != ww.getDirection()){
                    //System.out.println(ww.getDirection());
                    ww.doAction(World.A_TURN_LEFT);
                    //System.out.println("inside while");
                }
                ww.doAction(World.A_MOVE);
                nextmove(ww, step);
            }
        }
    }
    
    public int nextmove(World ww, int step) {
        //System.out.println("inside nextmove");
        //World nw = ww.cloneWorld();
        int xx = ww.getPlayerX();
        int yy = ww.getPlayerY();
       
        int temp;
        //System.out.println(xx+"-----------"+yy);
        if(ww.hasGlitter(xx, yy) && ww.hasWumpus(xx, yy)){
            reward[xx][yy] = 123456;
            return 1;
        }
        if(ww.hasGlitter(xx, yy)) {
            reward[xx][yy] += 10000; 
            return 1;
        }
        if(ww.hasWumpus(xx, yy)) {
            reward[xx][yy] += -501;
            return 0;
        }
        if(ww.hasPit(xx, yy))
            reward[xx][yy] += -1000;
        if(ww.hasBreeze(xx, yy) || ww.hasStench(xx, yy) || ww.hasPlayer(xx, yy))
            reward[xx][yy] += 1;
        step++;
        for (int i = 0; i < 4; i++) {
            World nw = ww.cloneWorld();
        int x = nw.getPlayerX();
        int y = nw.getPlayerY();
            if (nw.getDirection() == i) { //System.out.println(step);
                if((i==0 && nw.isValidPosition(x, y+1) && nw.isUnknown(x, y+1)) || (i==1 && nw.isValidPosition(x+1, y) && nw.isUnknown(x+1, y)) || (i==2 && nw.isValidPosition(x, y-1) && nw.isUnknown(x, y-1)) || (i==3 && nw.isValidPosition(x-1, y) && nw.isUnknown(x-1, y))) {
                    nw.doAction(World.A_MOVE);
                    temp = nextmove(nw, step);
                    if(temp == 1){
                        reward[x][y] += 100*step;
                        return 1;
                    }
                }
            }
            else {
                while(i != nw.getDirection())
                    nw.doAction(World.A_TURN_LEFT);
                //System.out.println(nw.getDirection());
                if((i==0 && nw.isValidPosition(x, y+1) && nw.isUnknown(x, y+1)) || (i==1 && nw.isValidPosition(x+1, y) && nw.isUnknown(x+1, y)) || (i==2 && nw.isValidPosition(x, y-1) && nw.isUnknown(x, y-1)) || (i==3 && nw.isValidPosition(x-1, y) && nw.isUnknown(x-1, y))) {
                    nw.doAction(World.A_MOVE);
                     temp = nextmove(nw, step);
                    if(temp == 1){
                        reward[x][y] += 100*step;
                        return 1;
                    }
                }
            }
        }
     
        return 0;
    }
    
}
