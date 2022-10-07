import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

public class TextReader {
    ProcessBuilder builder;
    JFrame jFrame;
    JPanel jPanel;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    public void CreateJLabel(String message) {
        JLabel label = new JLabel(message);
        Random r = new Random();
        label.setForeground(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
        Font font = new Font("serif", Font.BOLD, 30);
        label.setFont(font);
        label.setSize(new Dimension(300, 50));
        Random random = new Random();
        int x = random.nextInt((int) screenSize.getHeight());
        int y = random.nextInt((int) screenSize.getWidth());
        label.setBounds(y, x, 300, 50);
        System.out.println(x + "," + y);
        jPanel.setLayout(null);
        jPanel.add(label);
        jFrame.add(jPanel, BorderLayout.CENTER);
        jFrame.setVisible(true);
        jFrame.repaint();

        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        jPanel.remove(label);
                        jPanel.revalidate();
                        jFrame.repaint();
                    }
                },
                5000
        );
    }

    public void execute() throws IOException {

        SetupFrame();
        StartTextReading();
        //CreateJLabel("messageeee");
    }

    private void SetupFrame() {
        jFrame = new JFrame();
        jPanel = new JPanel();
        jPanel.setBackground(new Color(0f, 0f, 0f, 0f));
        jFrame.getContentPane();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        jFrame.setSize(screenSize);
        jFrame.setUndecorated(true);
        jFrame.setBackground(new Color(0f, 0f, 0f, 0f));
        jFrame.setVisible(true);
    }

    private void StartTextReading() throws IOException {
        builder = new ProcessBuilder("node", "bot.js");
        builder.directory(new File("C:/Users/Sonrisa/Documents/witchChatbot"));
        builder.redirectErrorStream(true);
        Process process = builder.start();
        InputStream stdout = process.getInputStream();

        BufferedReader reader = new BufferedReader(new InputStreamReader(stdout));
        String line;

        while ((line = reader.readLine()) != null) {
            System.out.println("Stdout: " + line);
            CreateJLabel(line);
        }
    }


}
