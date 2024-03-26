package creating_sets;
import java.io.*;
import java.util.Arrays;

public class Program {
    public static void main(String[] args) throws IOException {
        File file = new File("notes.txt");
        FileWriter writer = new FileWriter(file);
        for (int i = 0; i < 50; i++) {
            //int n = (int) (100 + Math.random() * 9900);
            int n = 10000;
            int[][] a = new int[n][2];
                for (int j = 0; j < n; j++) {
                    a[j][0] = (int) (Math.random() * 100); //в диапазоне [0, 100)
                    a[j][1] = (int) (Math.random() * 100);
                    //запись всей строки
                    String number = a[j][0] + " " + a[j][1] + ";";
                    writer.write(number);
                }
                writer.append('\n');
        }
        writer.close();
        BufferedReader reader = new BufferedReader(new FileReader("notes.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] a = line.split(";");
            String[][] c = new String[a.length][2];
            for (int i = 0; i < a.length; i++) {
                c[i] = a[i].split(" ");
            }
            System.out.println(Arrays.deepToString(c));
        }
        reader.close();
    }
}
