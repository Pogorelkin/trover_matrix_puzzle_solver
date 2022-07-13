package service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RWFileService {
    public List<Boolean> readStartPosition() {
        ArrayList<Boolean> list = new ArrayList<>();
        File file = new File("resources/start.txt");

        try (FileInputStream inputStream = new FileInputStream(file)) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            for (int i = 0; i < 3; i++) {
                String line = bufferedReader.readLine();
                for (int j = 0; j < 3; j++) {
                    list.add("1".equals(String.valueOf(line.charAt(j))));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
