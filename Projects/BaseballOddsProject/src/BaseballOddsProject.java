/**
 * Created by hanxi on 26/09/2015.
 */
public class BaseballOddsProject {
    public static void main(String[] args){
        long startTime = System.nanoTime();

        //double output = recursiveRoutine(20,23);
        double output = dynamicApproach(50,40);

        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        double duration = totalTime/1000000000.0;
        System.out.println(duration);
        System.out.println(output);
    }

    /**
     * the method compute the probability of P(i, j) by recursive routine.
     * @param i
     * @param j
     * @return double value of the probability of P(i, j)
     */
    public static double recursiveRoutine(int i, int j){
        if(i == 0 && j > 0){
            return 1.0;
        }
        else if(i > 0 && j==0){
            return 0.0;
        }
        else{
            return (recursiveRoutine(i-1, j) + recursiveRoutine(i, j-1))/2;
        }
    }

    /**
     * the method compute the probability of P(i, j) by dynamic approach
     * @param i
     * @param j
     * @return double value of the probability of P(i, j)
     */
    public static double dynamicApproach(int i, int j){
        double[][] array = new double[i+1][j+1];
        for(int a=0; a<=i; a++){
            array[a][0] = 0.0;
        }

        for(int b=0; b<=j; b++){
            array[0][b] = 1.0;
        }

        for(int x = 1; x <= i; x++){
            for(int y = 1; y <=j; y++){
                array[x][y] = (array[x-1][y] + array[x][y-1])/2;
                //System.out.println(i + " "+j+array[i][j])
            }
        }

        return array[i][j];
    }
}
