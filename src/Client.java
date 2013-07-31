import javax.swing.*;
import java.awt.*;

public class Client extends JFrame  {

    private JTabbedPane tabbedPane1;

    TerminalWindow terminal = new TerminalWindow();

    Client () {
        terminal.setVisible(true);

        tabbedPane1.addTab("blah", terminal);
        tabbedPane1.setVisible(true);

        this.setLayout(new BorderLayout());
        this.add(tabbedPane1, BorderLayout.CENTER);
    }


}
