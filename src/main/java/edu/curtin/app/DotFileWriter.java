package edu.curtin.app;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DotFileWriter {
    private static final Logger logger = Logger.getLogger(DotFileWriter.class.getName());

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
            logger.log(Level.INFO, "DOT file successfully written to: {0}", filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write DOT file", e);
        }
    }
}
