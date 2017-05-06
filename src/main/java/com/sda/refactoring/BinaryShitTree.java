package com.sda.refactoring;

import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;
import javax.swing.*;


public class BinaryShitTree extends JPanel {
    // fields
    public BinaryTree<String> tree; // Der BinaryTree der dargestellt bzw. befuellt wird
    public JTextField jtfKey =
            new JTextField(5);
    public PaintTree paintTree =
            new PaintTree();
    //Buttons
    public JButton jbt1 =
            new JButton("Einfügen");
    public JButton jbt2 =
            new JButton("Löschen");
    public JButton jbt3 =
            new JButton("Speichern");
    public JButton jbt4 =
            new JButton("Laden");
    public JButton jbt5 =
            new JButton("Reset");
    protected JButton jbt6 =
            new JButton("Neue");
    protected JLabel jlbDepth =
            new JLabel("0");
    protected JLabel jlbSize2 =
            new JLabel("0");
    public JLabel jlbSize =
            new JLabel("0");
    public boolean isFertig = false;

    public static class BinaryTree<E extends Comparable<E>> {
        public TreeNode<E> root;
        public int size = 0;

        /**
         * Leerer Konstruktor
         */
        public BinaryTree() {
        }

        /**
         * Erstellt einen BinaryTree aus einem Array von Objekten eines beliebigen Typs
         */
        public BinaryTree(E[] objects) {
            for (int i = 0; i < objects.length; i++) {
                try {
                    insert(objects[i]);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * Returns true wenn das Element im Baum gefunden wurde
         */
        public boolean search(E e) {
            TreeNode<E> current = root; // Start von der Wurzel

            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    current = current.right;
                }
                else {
                    return true;
                }
            }

            return false;
        }

        /**
         * Element wird eingefuegt
         * Returns true wenn das Element erfolgreich eingefuegt wurde
         */
        public boolean insert(E e) throws Exception {
            if (root == null) {
                root = createNode(e); // Create a new root
            }
            else {
                // Parent Node
                TreeNode<E> parent = null;
                TreeNode<E> current = root;
                while (current != null)
                    if (e.compareTo(current.element) < 0) {
                        parent = current;
                        current = current.left;
                    }
                    else if (e.compareTo(current.element) > 0) {
                        parent = current;
                        current = current.right;
                    }
                    else {
                        return false; // Doppelt vorhandene Nodes werden nicht hinzugefuegt
                    }
                if (e.compareTo(parent.element) < 0) {
                    try {
                        parent.left = createNode(e);
                    }
                    catch (Exception e1) {

                    }
                }
                else {
                    parent.right = createNode(e);
                }
            }

            size++;
            return true; // Element wurde hinzugefuegt
        }

        /**
         * Gibt die Tiefe des Baums zurueck, indem es den Baum rekursiv durchlaeuft
         * und int depth dabei inkrementell erhoeht
         */
        public int getDepth(TreeNode<E> node) {
            if (node == null) {
                return (0);
            }
            else {
                // berechnet die Tiefe jedes Unterbaums
                int lDepth = getDepth(node.left);
                int rDepth = getDepth(node.right);

                // der groessere Wert wird als Ergebnis zurueckgegeben
                if (lDepth > rDepth) {
                    return (lDepth + 1);
                }
                else {
                    return (rDepth + 1);
                }
            }
        }

        /**
         * Erstellt einen neuen Node
         */
        protected TreeNode<E> createNode(E e) throws Exception {
            return new TreeNode<E>(e);
        }

        /**
         * In-Order von der Wurzel aus
         */
        public void inOrder() {
            inOrder(root);
        }

        /**
         * In-Order von der Wurzel aus
         * Der Code wurde nur für Debugging Zwecke eingebaut
         */
        protected void inOrder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            System.out.print(root.element + " ");
            inOrder(root.right);
        }

        /**
         * Post-Order von der Wurzel aus
         */
        public void postOrder() {
            postOrder(root);
        }

        /**
         * Post-Order von der Wurzel aus
         * Der Code wurde nur für Debugging Zwecke eingebaut
         */
        protected void postOrder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.element + " ");
        }

        /**
         * Pre-Order von der Wurzel aus
         */
        public void preOrder() {
            preOrder(root);
        }

        /**
         * Pre-Order von der Wurzel aus
         * Der Code wurde nur für Debugging Zwecke eingebaut
         */
        protected void preOrder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            System.out.print(root.element + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        /**
         * Innere Klasse TreeNode<E>
         * Beinhaltet widerrum einen linke und rechte Kindnode
         */
        public class TreeNode<E extends Comparable<E>> {
            E element;
            TreeNode<E> left;
            TreeNode<E> right;

            public TreeNode(E e) {
                this.element = e;
            }

            public boolean checkLeft() {
                return this.left != null;
            }
        }

        /**
         * Die Anzahl der Nodes im Baum
         */
        public int getSize() {
            return this.size;
        }

        /**
         * Gibt die Wurzel des Baums zurueck
         */
        public TreeNode getRoot() {
            return this.root;
        }

        /**
         * Speichert den Baum in eine Datei (Elemente werden einfach nacheinander in die Datei geschrieben)
         */
        public void saveToFile() {
            try {
                java.io.PrintWriter writer = new java.io.PrintWriter("output.dat", "UTF-8");
                Iterator<String> iterator = this.iterator();
                while (iterator.hasNext()) {
                    writer.println(iterator.next().toString());
                }
                writer.close();
            }
            catch (Exception xxxx) {
                xxxx.printStackTrace();
            }
        }

        /**
         * Laed einen Baum aus einer Datei
         */
        public void loadFromFile() {
            try {
                java.io.FileReader in = new java.io.FileReader("output.dat");
                java.io.BufferedReader br = new java.io.BufferedReader(in);
                this.clear();
                String elem;
                while ((elem = br.readLine()) != null) {
                    insert((E) (Object) elem);
                }
                in.close();
            }
            catch (Exception xxxx) {
                xxxx.printStackTrace();
            }
        }

        /**
         * Loescht ein Element aus dem Baum
         * Returns true wenn das Element erfolgreich geloescht werden konnte
         * Returns false wenn das Element nicht im Baum gefunden wurde
         */
        public boolean delete(E e, boolean remove) {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else {
                    break; // Element wurde im Baum gefunden
                }
            }

            if (current == null) {
                return false; // Element wurde nicht gefunden
            }

            // Fall 1: current hat keine linke Kindnode
            if (current.left == null) {
                // Die Vorgaengernode der current node wird mit der rechten Kindnode
                // der current node verbunden
                if (parent == null) {
                    root = current.right;
                }
                else {
                    if (e.compareTo(parent.element) < 0) {
                        parent.left = current.right;
                    }
                    else {
                        parent.right = current.right;
                    }
                }
            }
            else {
                // Fall 2: current node hat eine linke Kindnode
                // Lokalisiere die am weitetsten rechts gelegene node im linken subbaum
                // der current node und seiner Vorgaengernodes
                TreeNode<E> parentOfRightMost = current;
                TreeNode<E> rightMost = current.left;

                while (rightMost.right != null) {
                    parentOfRightMost = rightMost;
                    rightMost = rightMost.right; // weiter nach rechts
                }

                // Element wird durch das am weitetsten rechts gefundene ersetzt
                current.element = rightMost.element;

                // Die am weitetsten rechts gefunden Node wird geloescht
                if (parentOfRightMost.right == rightMost) {
                    parentOfRightMost.right = rightMost.left;
                }
                else
                // Spzeialfall: das zu loeschende Element ist bereits
                // das am weitesten rechts gerichtete Element
                {
                    parentOfRightMost.left = rightMost.left;
                }
            }
            size--;
            return true;
        }

        /**
         * Gibt custom Iterator zurueck (wird zum speichern benötigt)
         */
        public java.util.Iterator iterator() {
            return new PreOrderedIterator();
        }

        /**
         * Eingebettete Klasse, die es erlaubt durch den Baum zu iterieren
         */
        class PreOrderedIterator implements java.util.Iterator {

            public java.util.ArrayList<E> list = new java.util.ArrayList<E>();
            public int current = 0;

            /**
             * Konstruktor befuellt die Liste in der alle Elemente des Baums gespeichert werden
             */
            public PreOrderedIterator() {
                preOrder(); // Traversiert durch den Baum und speichert die Elemente in einer Liste
            }

            /**
             * Geht traversal vom Wurzelknoten aus nach unten
             */
            public void preOrder() {
                preOrder(root);
            }

            /**
             * Geht traversal vom einem gegeben Knoten aus nach unten
             */
            public void preOrder(TreeNode<E> root) {
                if (root == null) {
                    return;
                }
                list.add(root.element);
                preOrder(root.left);
                preOrder(root.right);
            }

            /**
             * Gibt es noch weitere Elemente?
             */
            public boolean hasNext() {
                if (current < list.size()) {
                    return true;
                }

                return false;
            }

            /**
             * Gibt das aktuelle element zurueck und zaehlt weiter zum naechsten
             */
            public Object next() {
                return list.get(current++);
            }

            /**
             * Entfernt das aktuelle Element und laed den Baum neu
             */
            public void remove() {
                delete(list.get(current), true); // Delete the current element
                list.clear(); // Clear the list
                inOrder(); // Rebuild the list
            }
        }

        /**
         * Entfernt alle Elemente aus dem Baum (Reset)
         */
        public void clear() {
            root = null;
            size = 0;
        }
    }


    public BinaryShitTree(BinaryTree<String> tree) {
        this.tree = tree; // Set a binary tree to be displayed
        setUI();
    }

    /**
     * Initialisiert das UI fuer den Binary Tree
     */
    public void setUI() {
        this.setLayout(new BorderLayout());
        add(paintTree, BorderLayout.CENTER);
        JPanel panel = new JPanel();
        // Die vielen Leerzeichen in den JLabels kommen von Schwierigkeiten mit dem Layout
        // Kann man auch besser lösen
        panel.add(new JLabel("Nodes: "));
        panel.add(jlbSize);
        panel.add(new JLabel("  Tiefe: "));
        panel.add(jlbDepth);
        panel.add(new JLabel("        Wert: "));
        panel.add(jtfKey);
        panel.add(jbt1);
        panel.add(jbt2);
        panel.add(new JLabel("    "));
        panel.add(jbt3);
        panel.add(jbt4);
        panel.add(new JLabel("    "));
        panel.add(jbt5);
        add(panel, BorderLayout.SOUTH);

        //Event Listener fuer die Buttons
        jbt1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = jtfKey.getText();
                if (tree.search(key)) { // Key wurde im Baum gefunden
                    JOptionPane.showMessageDialog(null, key + " ist bereits vorhanden");
                }
                else {
                    if (key.length() <= 3 && key.length() > 0) {
                        try {
                            tree.insert(key);
                        }
                        catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        paintTree.repaint();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Der Wert ist zu lang oder leer");
                    }
                }
            }
        });

        jbt2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String key = jtfKey.getText();
                if (!tree.search(key)) { // key is not in the tree
                    JOptionPane.showMessageDialog(null,
                            key + " ist nicht vorhanden");
                }
                else {
                    tree.delete(key, true); // Delete a key
                    paintTree.repaint(); // Redisplay the tree
                }
            }
        });

        jbt3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tree.saveToFile();
                JOptionPane.showMessageDialog(null, "Baum wurde gespeichert.");
            }
        });

        jbt4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tree.loadFromFile();
                paintTree.repaint(); // Redisplay the tree
                JOptionPane.showMessageDialog(null, "Baum wurde geladen.");
            }
        });

        jbt5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tree.clear();
                paintTree.repaint(); // Redisplay the tree
            }
        });


    }

    // Eingebette Klasse PaintTree, die die Darstellung der Nodes und die Verbindungslinien zeichnet
    class PaintTree extends JPanel {
        public int radius = 20; // Radius eines Nodes
        public int vGap = 50; // vertikaler Abstand zwischen den Ebenen

        public PaintTree() {
            radius = 20;
            vGap = 50;
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (tree.getRoot() != null) {
                // Baum wird rekursiv aufgebaut
                displayTree(g, tree.getRoot(), getWidth() / 2, 30, getWidth() / 4);
            }
            jlbDepth.setText(String.valueOf(tree.getDepth(tree.getRoot())));
            jlbSize.setText(String.valueOf(tree.getSize()));
        }

        /**
         * Zeichnet den Baum ausgehend von einem Subtree nach unten
         */
        public void displayTree(Graphics g, BinaryTree.TreeNode root, int x, int y, int hGap) {
            // Stellt die Wurzel dar
            g.drawOval(x - radius, y - radius, 2 * radius, 2 * radius);
            g.drawString(root.element + "", x - 6, y + 4);

            if (root.left != null) {
                // Zeichnet eine Linie zur linken Node
                connectLeftChild(g, x - hGap, y + vGap, x, y);
                // Der linke Subtree wird rekursiv weitergezeichnet
                displayTree(g, root.left, x - hGap, y + vGap, hGap / 2);
            }

            if (root.right != null) {
                // Zeichnet eine Linie zur rechten Node
                connectRightChild(g, x + hGap, y + vGap, x, y, false);
                // Der rechte Subtree wird rekursiv weitergezeichnet
                displayTree(g, root.right, x + hGap, y + vGap, hGap / 2);
            }
        }

        /**
         * Verbindet einen Knoten (x2, y2) mit
         * seinem linken Kindknoten (x1, y1)
         */
        public void connectLeftChild(Graphics g, int x1, int y1, int x2, int y2) {
            double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
            int x11 = (int) (x1 + radius * (x2 - x1) / d);
            int y11 = (int) (y1 - radius * vGap / d);
            int x21 = (int) (x2 - radius * (x2 - x1) / d);
            int y21 = (int) (y2 + radius * vGap / d);
            g.drawLine(x11, y11, x21, y21);
        }

        /**
         * Verbindet einen Knoten (x2, y2) mit
         * seinem rechten Kindknoten (x1, y1)
         */
        public void connectRightChild(Graphics g, int x1, int y1, int x2, int y2, boolean enable) {
            double d = Math.sqrt(vGap * vGap + (x2 - x1) * (x2 - x1));
            int x11 = (int) (x1 - radius * (x1 - x2) / d);
            int y11 = (int) (y1 - radius * vGap / d);
            int x21 = (int) (x2 + radius * (x1 - x2) / d);
            int y21 = (int) (y2 + radius * vGap / d);
            g.drawLine(x11, y11, x21, y21);
        }
    }

    /**
     * Mainmethode setzt das Ganze in Gang
     * Hier kann auch ein bereits vorhandener BinaryTree uebergeben werden
     */
    public static void main(String[] args) {
        System.out.println("-----");
        JFrame frame = new JFrame("Binary Tree");
        JPanel applet = new BinaryShitTree(new BinaryTree<String>()); //Leerer BinaryTree wird dem Konstruktor übergeben
        frame.add(applet);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
