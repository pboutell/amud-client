/**
 * Created with IntelliJ IDEA.
 * User: parker
 * Date: 7/28/13
 * Time: 11:57 AM
 * To change this template use File | Settings | File Templates.
 */

import javax.swing.*;
import java.awt.BorderLayout;

class Client {

    private static void run() {
        TerminalWindow term = new TerminalWindow();
        JTabbedPane tabbedPane = new JTabbedPane();
        JFrame frame = new JFrame();

        tabbedPane.addTab("blah", term);
        tabbedPane.setVisible(true);

        frame.setLayout(new BorderLayout());
        frame.add(tabbedPane, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1024, 768);
    }

    public static void main(String[] args) {
        run();
    }
}