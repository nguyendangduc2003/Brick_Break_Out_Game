package Level;

public class Level {
    public static int[][] arr = {

            {0,0,0,0,0,0,
            0,0,0,0,0,0,
            0,0,0,0,0,0,
            0,0,0,0,0,0,
            1,0,0,0,0,0,},

            //level 1
            {0,0,1,1,0,0,
            0,1,1,1,1,0,
            1,1,1,1,1,1,
            1,1,1,1,1,1,
            1,1,1,1,1,1},

            //level 2
            {0,0,1,1,0,0,
            0,1,1,1,1,0,
            1,1,1,1,1,1,
            0,1,1,1,1,0,
            0,0,1,1,0,0}
    };

    public static int[] getLevel(int level){
        return arr[level];
    }

    public static int getLevelMax(){
        return arr.length;
    }
}
