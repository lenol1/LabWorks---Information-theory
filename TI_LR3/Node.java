package TI_LR3;

public class Node {
        String letter;
        Double prob;
        Node left = null;
        Node right = null;
        Node(String letter, Double prob)
        {
            this.letter = letter;
            this.prob = prob;
        }
        public Node(String letter, Double prob, Node left, Node right)
        {
            this.letter = letter;
            this.prob = prob;
            this.left = left;
            this.right = right;
        }
}