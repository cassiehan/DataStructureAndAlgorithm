import java.io.*;
import java.util.Scanner;

/**
 * Created by hanxi on 18/09/2015.
 */

public class RedBlackTreeTester {
    /**
     * test the RedBlackTree
     * @param args args[0] - The file name of the word list (to be used as a dictionary).
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        RedBlackTree rbt = new RedBlackTree();
        String fileName = "";
        if (args.length > 0) {
            fileName = args[0];
        }
        InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
        BufferedReader br = new BufferedReader(reader);
        String line="";

        while((line = br.readLine())!=null){
            //System.out.println(line);
            rbt.insert(line);
        }
        br.close();

        Scanner scan = new Scanner(System.in);
        System.out.println("Red Black Tree is loaded with " + rbt.getSize() +" words");
        System.out.println("The height of the tree is "+ rbt.height());
        double maxHeight = 2 * (Math.log((double)(rbt.getSize()+1)) / Math.log(2) + 1e-10);
        System.out.println("2 * Lg(n+1) = " + maxHeight);
        System.out.println();
        System.out.println("Legal commands are: \n" +
                "<p>  display the entire word tree with a level order traversal.\n" +
                "<s>   print the words of the tree in sorted order (use an inorder traversal).\n" +
                "<r>   print the words of the tree in reverse sorted order.\n" +
                "<!>   to quit.\n" +
                "<c>   <word> to spell check this word\n" +
                "<a>   <word> add word to tree.\n" +
                "<f>   <fileName> to check a text file for spelling errors.\n");
        String input = "";
        while((input = scan.nextLine()) != null){
            if(input.length()==1){
                if(input.equals("p")){
                    System.out.println("Level order traversal");
                    rbt.levelOrderTraversal();
                }
                else if(input.equals("s")){
                    System.out.println("Inorder traversal");
                    rbt.inOrderTraversal();
                }
                else if(input.equals("r")){
                    System.out.println("Reverse order traversal");
                    rbt.reverseOrderTraversal();
                }
                else if(input.equals("!")){
                    System.out.println("Bye!");
                    break;
                }
                else{
                    System.out.println("Wrong command! Please enter another command.");
                }
            }
            else if(input.length()>1){
                if(input.substring(0, 1).equals("c")){
                    String word = input.substring(2, input.length());
                    //System.out.println(word);
                    if(rbt.contains(word)){
                        System.out.println("Found " + word + " after " + rbt.getRecentCompares() + " comparisons.");
                    }
                    else{
                        System.out.println(word + " is not in the dictionary. Perhaps you mean "+rbt.closeBy(word));
                    }
                }
                else if(input.substring(0, 1).equals("a")){
                    String word = input.substring(2, input.length());
                    if(rbt.contains(word)){
                        System.out.println("The word '" + word + "' is already in the dictionary");
                    }else{
                        rbt.insert(word);
                        System.out.println("'" + word + "' was added to dictionary");
                    }
                }
                else if(input.substring(0, 1).equals("f")){
                    String file = input.substring(2, input.length());
                    InputStreamReader reader2 = new InputStreamReader(new FileInputStream(file));
                    BufferedReader br2 = new BufferedReader(reader2);
                    String checkLine= "";

                    while((checkLine = br2.readLine())!=null){
                        //System.out.println(checkLine);
                        String[] words = checkLine.split("\\s+");
                        for(int i=0; i<words.length; i++){
                            //System.out.println(words[i]);
                            words[i] = words[i].replaceAll("\\W","");
                            if(!rbt.contains(words[i])){
                                System.out.println("'" + words[i] + "' was not found in dictionary.");
                            }
                        }

                    }
                    br2.close();
                }
                else{
                    System.out.println("Wrong command! Please enter another command.");
                }
            }
            else{
                System.out.println("Please enter a command.");
            }

        }
    }
}
