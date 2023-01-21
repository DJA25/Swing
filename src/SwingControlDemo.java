import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SwingControlDemo  implements ActionListener, AbTest {
    private JFrame mainFrame;
    private JLabel headerLabel;
    private JLabel statusLabel;
    private JPanel controlPanel;
    private JMenuBar mb;
    private JMenu file, edit, help;
    private JMenuItem cut, copy, paste, selectAll;
    private JTextArea ta;
    private JTextArea tf;
    private int WIDTH=800;
    private int HEIGHT=700;
    private JPanel jp;
    public String curText = "";
    private JSplitPane splitPane;
    public boolean shift = false;
    public boolean clear = false;
    public SwingControlDemo() {
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingControlDemo swingControlDemo = new SwingControlDemo();
        swingControlDemo.showEventDemo();
    }

    private void prepareGUI() {
        mainFrame = new JFrame("Java SWING Examples");
        mainFrame.setSize(WIDTH, HEIGHT);
        mainFrame.setLayout(new GridLayout(1, 1));




        cut = new JMenuItem("cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("selectAll");
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);

        JMenuItem open = new JMenuItem("open");
        open.addActionListener(this);



        mb = new JMenuBar();
        file = new JMenu("File");
        edit = new JMenu("Edit");
        help = new JMenu("Help");
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);

        file.add(open);

        mb.add(file);
        mb.add(edit);
        mb.add(help);




//Provide minimum sizes for the two components in the split pane


        ta = new JTextArea();
        ta.setEditable(false);

        ta.setBounds(50, 5, WIDTH-100, HEIGHT-50);
//        jp = new JPanel();
        tf = new JTextArea();
        tf.setBounds(100, HEIGHT-100, WIDTH-100, HEIGHT-20);
        tf.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shift = true;
                }
                if(e.getKeyCode()==KeyEvent.VK_ENTER && !shift) {
                    if(tf.getText().length()>0) {
                        ta.append("\n" + "ME: " + tf.getText());
                        clear = true;
                    }
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER && clear) {
                    tf.setText("");
                    clear = false;
                }
                else if(e.getKeyCode()==KeyEvent.VK_ENTER) {
                    tf.append("\n");
                }
                if(e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    shift = false;
                }
            }
        });
//        tf.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                ta, tf);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(HEIGHT/4*3);


        Dimension minimumSize = new Dimension(100, 50);
        ta.setMinimumSize(minimumSize);
        tf.setMinimumSize(minimumSize);

        mainFrame.add(splitPane);


        mainFrame.setJMenuBar(mb);

        headerLabel = new JLabel("", JLabel.CENTER);
        statusLabel = new JLabel("", JLabel.CENTER);
        statusLabel.setSize(350, 100);

        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        controlPanel.setLayout(new FlowLayout());

//        mainFrame.add(headerLabel);
//        mainFrame.add(controlPanel);
//        mainFrame.add(statusLabel);
        mainFrame.setVisible(true);
    }

    private void showEventDemo() {
        headerLabel.setText("Control in action: Button");

        JButton okButton = new JButton("OK");
        JButton submitButton = new JButton("Submit");
        JButton cancelButton = new JButton("Cancel");

        okButton.setActionCommand("OK");
        submitButton.setActionCommand("Submit");
        cancelButton.setActionCommand("Cancel");

        okButton.addActionListener(new ButtonClickListener());
        submitButton.addActionListener(new ButtonClickListener());
        cancelButton.addActionListener(new ButtonClickListener());

        controlPanel.add(okButton);
        controlPanel.add(submitButton);
        controlPanel.add(cancelButton);

        mainFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cut)
            tf.cut();
        if (e.getSource() == paste)
            tf.paste();
        if (e.getSource() == copy)
            tf.copy();
        if (e.getSource() == selectAll)
            tf.selectAll();
    }

    @Override
    public void test() {
        
    }

    @Override
    public int test2(int x) {
        return 0;
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.equals("OK")) {
                statusLabel.setText("Ok Button clicked.");
            } else if (command.equals("Submit")) {
                statusLabel.setText("Submit Button clicked.");
            } else {
                statusLabel.setText("Cancel Button clicked.");
            }
        }
    }

}