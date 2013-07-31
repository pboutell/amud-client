import de.mud.telnet.TelnetWrapper;
import de.mud.terminal.SwingTerminal;
import java.io.IOException;
import de.mud.terminal.vt320;

/**
 * Created with IntelliJ IDEA.
 * User: parker
 * Date: 7/28/13
 * Time: 1:50 PM
 * To change this template use File | Settings | File Templates.
 */
class Listener implements Runnable {
    private TelnetWrapper telnet;
    private SwingTerminal terminal;

    Listener(SwingTerminal term, TelnetWrapper telnet) {
        this.terminal = term;
        this.telnet = telnet;
    }

    public void addLine(String line) {
        vt320 vduBuffer = (vt320) terminal.getVDUBuffer();
        vduBuffer.putString(line);
    }

    public void run(){
        int size = 0;
        byte[] buf = new byte[2048];

        while (true) {
            try {
                size = telnet.read(buf);
            }
            catch (IOException e){
                System.out.println("IOException while reading from server: " + e.getMessage());
            }
            if (size > 0) {
                String line = new String(buf);
                addLine(line);
                buf = new byte[2048];
            }
        }
    }
}