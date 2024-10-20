package edu.curtin.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class DotFileWriter {

    public static void writeDotFile(Map<String, Town> towns, Map<String, Railway> railways, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("graph Towns {\n");

            for (Town town : towns.values()) {
                writer.write("    " + town.getName() + "\n");
            }

            for (Railway railway : railways.values()) {
                Town townA = railway.getTownA();
                Town townB = railway.getTownB();
                if (railway.isDualTrack()) {
                    if (!railway.isCompleted()) {
                        writer.write("    " + townA.getName() + " -- " + townB.getName() + " [style=\"dashed\",color=\"black:black\"]\n");
                    } else {
                        writer.write("    " + townA.getName() + " -- " + townB.getName() + " [color=\"black:black\"]\n");
                    }
                } else {
                    if (!railway.isCompleted()) {
                        writer.write("    " + townA.getName() + " -- " + townB.getName() + " [style=\"dashed\"]\n");
                    } else {
                        writer.write("    " + townA.getName() + " -- " + townB.getName() + "\n");
                    }
                }
            }

            writer.write("}\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
