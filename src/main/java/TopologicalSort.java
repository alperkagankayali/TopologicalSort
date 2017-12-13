/*
We hereby solemnly state that the following topological sorting program was written
from scratch by the members of our group. Not even a single line of code has been
borrowed from some other source.

Alper Kağan Kayalı
 */
import java.util.ArrayList;
import java.util.Scanner;

public class TopologicalSort {
    static ArrayList<Node1> nodes = new ArrayList<Node1>();
    static ArrayList<Node1> result = new ArrayList<Node1>();
    static ArrayList<Node1> fishHook = new ArrayList<Node1>();
    static ArrayList<Node1> crossedData = new ArrayList<Node1>();
    public static void getNodes(Node1 root){

        Node1 temp = root;

        String data = (String)(temp.getData());
        if(nodes.size() == 0)
            nodes.add(temp);
        else{
            boolean flag = true;
            for(int i = 0; i < nodes.size();i++){
                if(data.equals((String)(nodes.get(i).getData()))){
                    flag = false;
                }
            }
            if(flag)
                nodes.add(root);
        }
        for(int i = 0; i < root.getChildren().size();i++){
            getNodes((Node1)root.getChildren().get(i));
        }

    }
    public static void createFishHooks(){
        //deep copying the nodes into the fishhook pairs.
        for(int i = 0; i < nodes.size(); i++){
            Node1 temp = new Node1(nodes.get(i).getData());
            fishHook.add(temp);
        }
        for(int i = 0; i < nodes.size();i++){
            if(nodes.get(i).getChildren().size()>0){
                Node1 temp = new Node1(((Node1)(nodes.get(i).getChildren().get(0))).getData());
                fishHook.get(i).addChild(temp);
                for(int j = 0; j < nodes.get(i).getChildren().size()-1;j++){
                    Node1 temp1 = new Node1(((Node1)(nodes.get(i).getChildren().get(j+1))).getData());
                    Node1 temp2 = new Node1(((Node1)(nodes.get(i).getChildren().get(j))).getData());
                    temp2.addChild(temp1);
                    fishHook.add(temp2);
                }
            }
        }
    }

    public static void getList(){
        if(fishHook.size() != 1){
            Node1 node = getLeft();
            if(node == null)
                return;
            else{
                result.add(node);
                node.setListed(true);
                getList();
            }
        }
        else{
            result.add(fishHook.get(0));
        }
    }
    public static Node1 getLeft(){
        for(int i = 0; i < fishHook.size() ;i++){
            String data = "";
            if(fishHook.get(i).getChildren().size() != 0) {
                data = (String) (((Node1) (fishHook.get(i).getChildren().get(0))).getData());
            }

            for(int j = 0; j < nodes.size();j++){
                String data1 = (String)(nodes.get(j).getData());
                if(!data.equals("") && data.equals(data1)){
                    nodes.get(j).setFlag(false);
                }
                if(data.equals("") && ((String)(fishHook.get(i).getData())).equals(data1)){
                    nodes.get(j).setFlag(false);
                }
            }
        }
        ArrayList<Node1> temparr = new ArrayList<Node1>();
        for(int i = 0;i < nodes.size();i++){
            if(nodes.get(i).isFlag() && !nodes.get(i).isListed()){
                temparr.add(nodes.get(i));
            }
            nodes.get(i).setFlag(true);
        }
        if(temparr.size() == 1){
            temparr.get(0).setListed(true);
            for(int i = 0; i < fishHook.size();i++){
                if(((String)(fishHook.get(i).getData())).equals((String)(temparr.get(0).getData()))){
                    crossedData.add(fishHook.get(i));
                    fishHook.remove(fishHook.get(i));
                }
            }
            return temparr.get(0);
        }

        else{
            Node1 temp = tieBreaker(temparr);
            for(int i = 0; i < fishHook.size();i++){
                if(((String)(fishHook.get(i).getData())).equals((String)(temp.getData()))){
                    crossedData.add(fishHook.get(i));
                    fishHook.remove(fishHook.get(i));
                }
            }
            temp.setListed(true);
            return temp;
        }

    }
    public static Node1 tieBreaker(ArrayList<Node1> x){
        Node1 temp = null;
        boolean flag = true;
        for(int i = result.size()-1;i>-1 && flag;i--) {
            for (int j = 0; j < result.get(i).getChildren().size() && flag; j++) {
                String data1 = (String) (((Node1) (result.get(i).getChildren().get(j))).getData());
                for (int k = 0; j < x.size() && flag; k++) {
                    if (data1.equals((String) (x.get(k).getData()))){
                        temp = x.get(k);
                        flag = false;
                    }
                }
            }
        }
        return temp;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Which graph do you want to sort(1 for 1st, 2 for 2nd, 3 for 3rd)?");
        int ans = sc.nextInt();
        if(ans == 2){
            Node1 root = new Node1("fstream");
            Node1 temp1 = new Node1("iostream");
            Node1 temp2 = new Node1("istream");
            Node1 temp3 = new Node1("ostream");
            Node1 temp4 = new Node1("ios");
            root.addChild(temp1);
            temp1.addChild(temp2);
            temp1.addChild(temp3);
            temp2.addChild(temp4);
            temp3.addChild(temp4);
            getNodes(root);
        }
        if(ans == 3){
            System.out.println("select the beginning object(1 for consultant manager, 2 for director, 3 for permanent manager)?");
            int ans1 = sc.nextInt();
            Node1 root1 = new Node1("Consultant Manager");
            Node1 root2 = new Node1("Director");
            Node1 root3 = new Node1("Permanent Manager");
            Node1 temp1 = new Node1("Consultant");
            Node1 temp2 = new Node1("Manager");
            Node1 temp3 = new Node1("Temporary Employee");
            Node1 temp4 = new Node1("Employee");
            Node1 temp5 = new Node1("Permanent Employee");
            root1.addChild(temp1);
            root1.addChild(temp2);
            temp1.addChild(temp3);
            temp2.addChild(temp4);
            temp3.addChild(temp4);
            root2.addChild(temp2);
            root3.addChild(temp2);
            root3.addChild(temp5);
            temp5.addChild(temp4);
            if(ans1 == 1){
                getNodes(root1);
            }
            if(ans1 == 2){
                getNodes(root2);
            }
            if(ans1 == 3){
                getNodes(root3);
            }
        }
        if(ans == 1){


            //Creating the graph
            Node1 root1 = new Node1("ClassCastException");
            Node1 root2 = new Node1("ArrayIndexOutOfBoundsException");
            Node1 root3 = new Node1("NullPointerException");
            Node1 root4 = new Node1("InputMismatchException");
            Node1 root5 = new Node1("ArithmeticException");
            Node1 temp1 = new Node1("RuntimeException");
            Node1 temp2 = new Node1("Exception");
            Node1 finalnode = new Node1("Throwable");
            Node1 root6 = new Node1("IOException");
            Node1 root7 = new Node1("AWTError");
            Node1 root8 = new Node1("ThreadDeath");
            Node1 root9 = new Node1("OutOfMemoryError");
            Node1 temp3 = new Node1("Error");
            root1.addChild(temp1);
            root2.addChild(temp1);
            root3.addChild(temp1);
            root4.addChild(temp1);
            root5.addChild(temp1);
            temp1.addChild(temp2);
            temp2.addChild(finalnode);
            root6.addChild(temp2);
            root7.addChild(temp3);
            root8.addChild(temp3);
            root9.addChild(temp3);
            temp3.addChild(finalnode);
            //xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
            System.out.println("select the beginning object(1 for RuntimeException, 2 for IOException, 3 for AWTError, 4 for ThreadDeath, 5 for OutOfMemoryError)?");
            int ans3 = sc.nextInt();
            if(ans3 == 1){
                System.out.println("select the specific exception(1 for ClassCastException, 2 for ArrayIndexOutOfBoundsException, 3 for NullPointerException, 4 for InputMismatchException, 5 for ArithmeticException)?");
                int ans4 = sc.nextInt();
                switch(ans4){
                    case(1): getNodes(root1); break;
                    case(2): getNodes(root2); break;
                    case(3): getNodes(root3); break;
                    case(4): getNodes(root4); break;
                    case(5): getNodes(root5); break;
                }
            }
            else{
                switch(ans3){
                    case(2): getNodes(root6); break;
                    case(3): getNodes(root7); break;
                    case(4): getNodes(root8); break;
                    case(5): getNodes(root9); break;
                }
            }
        }

        createFishHooks();
        getList();
        for(int i = 0; i < result.size(); i++){
            System.out.println(result.get(i).getData());
        }
    }
}
