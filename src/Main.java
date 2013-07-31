import javax.swing.*;

class Main {

    public static void main(String[] args) {
        Client frame = new Client();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setSize(1024, 768);
    }
}