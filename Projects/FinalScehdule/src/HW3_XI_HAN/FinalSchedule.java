import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by hanxi on 6/10/2015.
 */
public class FinalSchedule {
    public static int[][] edges;
    public static ArrayList<String> vertex;
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
            System.out.println("RECOMMENDAED SCHEDULE OF FINAL EXAMS (NOT NECESSARYLY OPTIMAL)");
            System.out.print("Final Exam Period 1: ");
            ArrayList<String> newclr = greedy();
            for(int i=0; i<newclr.size(); i++){
                System.out.print(newclr.get(i) + " ");
            }
            System.out.println();

            int count = 1;
            for(int i=0; i<vertex.size(); i++){
                if(!newclr.contains(vertex.get(i))){
                    System.out.print("Final Exam Period " + count++ + ": ");
                    System.out.println(vertex.get(i));
                }

            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * pre condition: the graph is not null
     * post condition: the method assigns to newclr those vertices that may be given the same color
     * @return
     */
    public static ArrayList<String> greedy(){
        boolean found;
        String v = null;
        String w = null;
        ArrayList<String> newclr = new ArrayList<String>();
        int count=0;

        for(int i=0; i<vertex.size(); i++){
            found = false;
            v = vertex.get(i);
            for(int j=0; j<newclr.size();j++){
                w = newclr.get(0);
                if(isEdge(vertex.indexOf(v), vertex.indexOf(w))){
                    found = true;
                }
            }
            if(found == false){
                newclr.add(v);
            }
        }
        return newclr;
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
            String a = st.nextToken();
            rbt.insert(a);
        }
    }
}
