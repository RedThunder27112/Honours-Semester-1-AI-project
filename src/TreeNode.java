import java.util.ArrayList;
import java.util.List;



class TreeNode<T> {
    private T data;
    private TreeNode<T> parent;
    private List<TreeNode<T>> children;

    public TreeNode(T data) {
        this.data = data;
        this.children = new ArrayList<>();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public TreeNode<T> getParent() {
        return parent;
    }

    public void setParent(TreeNode<T> parent) {
        this.parent = parent;
    }

    public List<TreeNode<T>> getChildren() {
        return children;
    }

    public void addChild(TreeNode<T> child) {
        child.setParent(this);
        this.children.add(child);
    }

    public void removeChild(TreeNode<T> child) {
        child.setParent(null);
        this.children.remove(child);
    }
    
    
    
}
