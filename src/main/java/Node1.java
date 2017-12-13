/*!!!!!!!DISCLAIMER!!!!!!!
    I did not write this code
    This code was taken from  https://stackoverflow.com/questions/19330731/tree-implementation-in-java-root-parents-and-children
    I just modified the code into something I can work with.
 */

import java.util.ArrayList;
import java.util.List;

public class Node1<T> {
    private List<Node1<T>> children = new ArrayList<Node1<T>>();
    private Node1<T> parent = null;
    private T data = null;
    private boolean flag;
    private boolean listed;

    public Node1(T data) {
        this.data = data;
        flag = true;
        listed = false;
    }

    public boolean isListed() {
        return listed;
    }

    public void setListed(boolean listed) {
        this.listed = listed;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Node1<T> getParent() {
        return parent;
    }

    public Node1(T data, int h, Node1<T> parent) {
        this.data = data;
        this.parent = parent;

    }

    public List<Node1<T>> getChildren() {
        return children;
    }

    public void setParent(Node1<T> parent) {
        //parent.addChild(this);
        this.parent = parent;
    }

    public void addChild(T data, int h) {
        Node1<T> child = new Node1<T>(data);
        child.setParent(this);
        this.children.add(child);
    }

    public void addChild(Node1<T> child) {
        child.setParent(this);
        this.children.add(child);
    }
    public void removeChild(Node1<T> child){
        this.children.remove(child);
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isRoot() {
        return (this.parent == null);
    }

    public boolean isLeaf() {
        if(this.children.size() == 0)
            return true;
        else
            return false;
    }

    public void removeParent() {
        this.parent = null;
    }




}