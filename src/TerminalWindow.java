import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import de.mud.telnet.TelnetWrapper;
import de.mud.terminal.SwingTerminal;

class TerminalWindow extends JPanel implements KeyListener  {

    private VirtualTerminal vTerm = new VirtualTerminal();
    private TelnetWrapper telnetWrapper = new TelnetWrapper();
    private JTextField input = new JTextField();
    private SwingTerminal swingTerminal = new SwingTerminal(vTerm);
    private Thread listenThread;
    public Listener listener;

    TerminalWindow() {
        this.setLayout(new BorderLayout());
        this.add(swingTerminal, BorderLayout.CENTER);
        this.add(input, BorderLayout.SOUTH);

        swingTerminal.setResizeStrategy(swingTerminal.RESIZE_SCREEN);
        input.addKeyListener(this);
        initListener();
    }

    private void parseCommand(String command) {
        String[] com;
        String[] args;

        args = command.split(" ");
        com = args[0].split(":");

        switch (com[1].toUpperCase()) {
            case "CONNECT":
                connect(args[1], Integer.parseInt(args[2]));
                break;
            case "QUIT":
                System.exit(0);
                break;
            default:
                listener.addLine("\r\nInvalid Command.\n\r");
                break;
        }
    }

    public synchronized void keyPressed(KeyEvent e) {
        switch (e.getKeyChar()) {
            case KeyEvent.VK_ENTER:
                e.consume();
                if (input.getText().startsWith(":")) {
                    parseCommand(input.getText());
                }
                else {
                    send(input.getText());
                }
                input.setText("");
                break;
        }
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    private void initListener() {
        listener = new Listener(swingTerminal, telnetWrapper);
        listenThread = new Thread(listener);
    }

    public void connect(String host, int port) {
        try {
            telnetWrapper.connect(host, port);
            listenThread.start();
        } catch (IOException e) {
            System.out.println("Failed to connect to host");
            System.out.println(e.getMessage());
        }
    }



    public void send(String text) {
        try {
            telnetWrapper.send(text + "\r");
        }
        catch (IOException ex) {
            System.out.println("Failed sending to host.");
            System.out.println(ex.getMessage());
        }
    }


}
