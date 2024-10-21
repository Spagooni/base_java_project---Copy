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
                String style = railway.isDualTrack() 
                    ? (railway.isCompleted() ? "[color=\"black:black\"]" : "[style=\"dashed\",color=\"black:black\"]")
                    : (railway.isCompleted() ? "" : "[style=\"dashed\"]");

                writer.write("    " + townA.getName() + " -- " + townB.getName() + " " + style + "\n");
            }

            writer.write("}\n");
            logger.log(Level.INFO, "DOT file successfully written to: {0}", filename);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write DOT file", e);
        }
    }
}
