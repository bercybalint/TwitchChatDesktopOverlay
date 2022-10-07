import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        try {
            new TextReader().execute();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}