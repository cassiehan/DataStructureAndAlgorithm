import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * Created by hanxi on 6/10/2015.
 */
public class OptimalFinalSchedule {
    public static int[][] edges;
    public static ArrayList<String> vertex;
    public static int[] color;
    public static void main(String[] args){
        String fileName = args[0];
        try {
            RedBlackTree rbt = new RedBlackTree();
            BufferedReader in = new BufferedReader(new FileReader(fileName));
            String line;
            while((line = in.readLine())!=null){
                addIntoRBT(rbt, line);
            }

            /*
            Display each distinct course and the unique number assigned to it.
             */
            rbt.inOrderTraversal();
            vertex = rbt.getVertex();
            for(int i=0; i<vertex.size(); i++){
                System.out.println(vertex.get(i) + " -> " + i);
            }

            System.out.println();
            System.out.println();

            /*
            Display the adjacency matrix for the graph.
             */
            BufferedReader in2 = new BufferedReader(new FileReader(fileName));
            edges = new int[vertex.size()][vertex.size()];
            String line2;
            while((line2 = in2.readLine())!=null){
                addEdge(line2);
            }

            System.out.println("  |0 1 2 3 4 5 6 7 8");
            System.out.println("--------------------");
            int counter=0;
            for(int i=0; i<vertex.size(); i++){
                System.out.print(counter++ +" |");
                for(int j=0; j<vertex.size(); j++){
                    System.out.print(edges[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            System.out.println();

            /*
            Display the final exam schedule.
             */
            int noc = numofColor();
            //System.out.println("noc "+noc);
            assignColor(noc);
//            for(int i=0; i<color.length; i++){
//                System.out.print(color[i] + " ");
//            }
//            System.out.println();

            HashMap<Integer, String> map = new HashMap<Integer, String>();
            for(int i=0; i<color.length;i++){
                if(map.containsKey(color[i])){
                    map.put(color[i], map.get(color[i])+" "+vertex.get(i));
                    //System.out.println(color[i] + " " + vertex.get(i));
                }else{
                    map.put(color[i], vertex.get(i));
                    //System.out.println(color[i] + " " + vertex.get(i));
                }
            }


            System.out.println("RECOMMENDAED SCHEDULE OF FINAL EXAMS (OPTIMAL)");


            for(int i=0; i<map.size(); i++){
                System.out.println("Final Exam Period " + (i + 1) + ": " + map.get(i+1));
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * pre condition: the graph is not null
     * post condition: to test the least possible number of colors
     * @return
     */
    public static int numofColor(){
        int noc = 0;
        int optimal = vertex.size();
        for(int i=optimal;i>0;i--){
            if(!assignColor(i)){
                noc = i+1;
                break;
            }
        }
        return noc;
    }

    /**
     * pre condition: the graph is not null, and the number of number is pre-designed
     * post condition: to assign colors to the vertice
     * @param noc
     * @return
     */
    public static boolean assignColor(int noc){
        color = new int[vertex.size()];
        try{
            solve(0,noc);
            return false;
        }catch(Exception ex){
            return true;
        }
    }

    /**
     * pre condition: the graph is not null
     * post condition: to assign colors recursively
     * @param v
     * @param numberofColor
     */
    public static void solve(int v, int numberofColor){
        for(int i=1; i<numberofColor; i++){
            if(isPossible(v, i)){
                color[v] = i;
                solve(v + 1, numberofColor);
                color[v] = 0;
            }
        }
    }

    /**
     * pre condition: the vertix exist
     * post condition: to check if it is valid to assign that color to the vertex
     * @param v a vertex
     * @param c a color
     * @return return true if the color can be assigned to this vertex
     */
    public static boolean isPossible(int v, int c){
        for(int i=0; i<vertex.size(); i++){
            if(edges[v][i]==1 && c==color[i]){
                return false;
            }
        }
        return true;
    }

    /**
     * pre condition: the graph is not null
     * post condition: the method return true if the two vertices have a edge between them
     * @param v
     * @param w
     * @return true if the two vertices have a edge between them
     */
    public static boolean isEdge(int v, int w){
        if(edges[v][w]==1){
            return true;
        }else{
            return false;
        }
    }

    /**
     * pre condition: the graph is not null
     * post condition: the method add edges into the adjacent matrix
     * @param line
     */
    public static void addEdge(String line){
        StringTokenizer st = new StringTokenizer(line, " ");
        st.nextToken();
        st.nextToken();
        ArrayList<Integer> edge = new ArrayList<Integer>();
        while(st.hasMoreTokens()){
            int index = vertex.indexOf(st.nextToken());
            if(!edge.contains(index)){
                edge.add(index);
            }
        }

        for(int i=0; i<edge.size(); i++){
            for(int j=0; j<edge.size(); j++){
                if(i==j){
                    edges[edge.get(i)][edge.get(j)]=0;
                }else{
                    edges[edge.get(i)][edge.get(j)]=1;
                }
            }
        }
    }

    /**
     * pre condition: the input line is not null
     * post condition: the method add the input line into the Red Black Tree
     * @param rbt
     * @param line
     */
    public static void addIntoRBT(RedBlackTree rbt, String line){
        StringTokenizer st = new StringTokenizer(line, " ");
        st.nextToken();
        st.nextToken();
        while(st.hasMoreTokens()){
            rbt.insert(st.nextToken());
        }
    }
}
