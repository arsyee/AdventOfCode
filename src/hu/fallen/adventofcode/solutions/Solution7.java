package hu.fallen.adventofcode.solutions;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Solution7 {

    public static void printSolution() {
        ArrayList<String> input;
        try {
            input = (ArrayList<String>) Files.readAllLines(FileSystems.getDefault().getPath("res", "input7.txt"));
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(calculate(input));
        System.out.println(calculate2(input));
    }
    
    static class Node {
        public int weight = 0;
        public String name = null;
        public List<String> children = new ArrayList<String>();
        
        @Override
        public String toString() {
            return this.toString(true);
        }
        
        public String toString(boolean verbose) {
            StringBuilder result = new StringBuilder();
            result.append(this.name).append(" (").append(this.weight).append(")");
            if (verbose && this.children.size() > 0) {
                result.append(" -> ").append(String.join(" ", this.children.toArray(new String[0])));
            }
            return result.toString();
        }
    }
    
    static class NodeTree {
        public Node node = null;
        public int weight = 0;
        public List<NodeTree> children = new ArrayList<NodeTree>();

        private NodeTree() {}
        
        public NodeTree(List<Node> nodeList) {
            int terminate = 2000;
            int first = 0;
            outer: while (!nodeList.isEmpty() && --terminate > 0) {
                if (node == null) {
                    node = nodeList.get(first);
                    weight = node.weight;
                    nodeList.remove(first);
                    continue outer;
                }
                for (int i = 0; i < nodeList.size(); ++i) {
                    Node test = nodeList.get(i);
                    if (insertUnder(test) || insertAbove(test)) {
                        nodeList.remove(i);
                        continue outer;
                    }
                }
            }
        }
        
        private boolean insertUnder(Node test) {
            if (node.children.contains(test.name)) {
                NodeTree branch = new NodeTree();
                branch.node = test;
                branch.weight = test.weight;
                weight += test.weight;
                children.add(branch);
                return true;
            }
            for (NodeTree child : children) {
                if (child.insertUnder(test)) {
                    weight += test.weight;
                    return true;
                }
            }
            return false;
        }
        
        private boolean insertAbove(Node test) {
            if (test.children.contains(node.name)) {
                NodeTree branch = new NodeTree();
                branch.children = children;
                branch.node = node;
                branch.weight = weight;
                
                List<NodeTree> newChildren = new ArrayList<NodeTree>();
                newChildren.add(branch);
                
                children = newChildren;
                node = test;
                weight = test.weight + branch.weight;
                return true;
            }
            return false;
        }
        
        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append(weight).append(" ");
            result.append(node).append("\n");
            for (NodeTree subTree : children) {
                result.append(subTree.toString());
            }
            // TODO Auto-generated method stub
            return result.toString();
        }
    }
    
    public static String calculate(List<String> input) {
        List<Node> nodeList = buildList(input);
        printNodeList(nodeList);
        System.out.println("---");
        NodeTree nodeTree = new NodeTree(nodeList);
        System.out.println(nodeTree.toString());
        return nodeTree.node.name;
    }

    private static List<Node> buildList(List<String> input) {
        List<Node> nodeList = new ArrayList<Node>();
        for (int i = 0; i < input.size(); ++i) {
            Node node = new Node();
            String[] data = input.get(i).split(" ");
            node.name = data[0];
            node.weight = Integer.parseInt(data[1].substring(1, data[1].length()-1));
            for (int j = 3; j < data.length; ++j) {
                node.children.add(data[j].split(",")[0]);
            }
            nodeList.add(node);
        }
        return nodeList;
    }

    public static int calculate2(List<String> input) {
        System.out.println("--- ---\ncalculate2 entered");
        List<Node> nodeList = buildList(input);
        NodeTree nodeTree = new NodeTree(nodeList);
        try {
            Node unbalanced = findUnbalanced(nodeTree);
            System.out.println("unbalanced is: "+unbalanced.name);
            NodeTree anchestorTree = getAncestorTree(unbalanced, nodeTree);
            System.out.println("anchestor is: "+anchestorTree.node.name);
            int siblingWeight = 0;
            int nodeWeight = 0;
            for (NodeTree child : anchestorTree.children) {
                if (unbalanced.name.equals(child.node.name)) {
                    nodeWeight = child.weight;
                } else {
                    siblingWeight = child.weight;
                }
            }
            int adjustment = nodeWeight-siblingWeight;
            System.out.println("nodeWeight: "+nodeWeight+", siblingWeight: "+siblingWeight+" -> adjustment: "+adjustment);
            return unbalanced.weight - adjustment;
        } catch (BalancedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static NodeTree getAncestorTree(Node unbalanced, NodeTree nodeTree) {
        for (NodeTree child : nodeTree.children) {
            if (unbalanced.name.equals(child.node.name)) return nodeTree;
            if (getAncestorTree(unbalanced, child) != null) return getAncestorTree(unbalanced, child);
        }
        return null;
    }

    private static Node findUnbalanced(NodeTree nodeTree) throws BalancedException {
        System.out.println("findUnbalanced called on "+nodeTree.node);
        switch (nodeTree.children.size()) {
            case 0:
                throw new BalancedException();
            case 1:
                return nodeTree.node;
            case 2:
                if (nodeTree.children.get(0).weight == nodeTree.children.get(1).weight) {
                    return nodeTree.node;
                } else {
                    throw new BalancedException();
                }
            default:
                int weight =    nodeTree.children.get(1).weight == nodeTree.children.get(0).weight
                             || nodeTree.children.get(2).weight == nodeTree.children.get(0).weight
                             ?  nodeTree.children.get(0).weight
                             :  nodeTree.children.get(1).weight;
                int unbalancedIndex = -1;
                for (int i = 0; i < nodeTree.children.size(); ++i) {
                    if (nodeTree.children.get(i).weight != weight) {
                        if (unbalancedIndex < 0) {
                            unbalancedIndex = i;
                        } else {
                            throw new BalancedException();
                        }
                    }
                }
                if (unbalancedIndex < 0) return nodeTree.node;
                return findUnbalanced(nodeTree.children.get(unbalancedIndex));
        }
    }
    
    static void printNodeList(List<Node> nodeList) {
        for (Node node : nodeList) {
            System.out.println(node.toString());
        }
    }
    
    static class BalancedException extends Exception {
        private static final long serialVersionUID = 1L;

        public BalancedException() {
            super("findUnbalanced entered a balanced node");
        }
    }
    
}
