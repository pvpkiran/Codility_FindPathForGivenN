import java.util.LinkedList;
import java.util.List;

public class Application {

	public static void main(String[] args) {
		int n = 29;
		Application aa = new Application();
		System.out.println("Length : " + aa.solution(n));
	}

	public int solution(int n) {

		if(n == 1 || n == 2) return n;
		Node root = new Node(1);
		Node leftNode = new Node(2);
		
		leftNode.parent=root;
		root.left=leftNode;
		
		List<Node> l = new LinkedList<Node>();
		l.add(leftNode);

		boolean found = false;
		for (int i = 1; i < n ; i++) {
			List<Node> temp = new LinkedList<Node>();
			for (Node node : l) {

				Node left = new Node(node.value * 2);
				Node right = new Node(node.value + 1);

				create(left, right, node);

				if(left.value == n || right.value == n){
					found= true;
					break;
				}
				temp.add(left);
				temp.add(right);
			}
			if(found) break;
			l.clear();
			l = temp;
		}
		levelOrderTraversal(root, n);

		System.out.println();
		return height(root);
	}

	public void create(Node left, Node right, Node current) {
		current.left = left;
		current.right = right;
		left.parent=current;
		right.parent=current;
	}

	public  int height(Node root)
	{
	    if(root==null)
	        return 0;
	    else
	        return Math.max( height(root.left), height(root.right))+1;
	}
	
	public void levelOrderTraversal(Node root, int n) {

		List<Node> queue = new LinkedList<Node>();
		queue.add(root);

		while (queue.size() > 0) {
			List<Node> temp = new LinkedList<Node>();

			for (Node current : queue) {
				System.out.print(current.value + ", ");
				if(current.value == n){
					System.out.println("\n");
					System.out.print("Path : ");
					printPath(current);
				}
				if (current.left != null) {
					temp.add(current.left);
				}
				if (current.right != null) {
					temp.add(current.right);
				}
			}
			System.out.println();
			queue.clear();
			queue = temp;
		}
	}
	
	public void printPath(Node current){
		while(current.parent != null){
			System.out.print(current.value + ", ");
			current= current.parent;
		}
		System.out.print(current.value);
		
	}
}
