/*
 * Copyright (C) 2015 University of Pittsburgh.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301  USA
 */
package edu.pitt.dbmi.ccd.commons.file;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * Jul 15, 2015 1:16:59 PM
 *
 * @author Kevin V. Bui (kvb2@pitt.edu)
 */
public class FileTools {

    private FileTools() {
    }

    /**
     * Get the directory string as a Path.
     *
     * @param directory the directory which the path represents.
     * @param required the directory must exist
     * @return the Path of the directory
     * @throws FileNotFoundException whenever the given string is not a
     * directory or does not exist
     */
    public static Path getDirectory(String directory, boolean required) throws FileNotFoundException {
        Path path = Paths.get(directory);

        if (Files.exists(path)) {
            if (!Files.isDirectory(path)) {
                throw new FileNotFoundException(String.format("'%s' is not a directory.\n", directory));
            }
        } else {
            if (required) {
                throw new FileNotFoundException(String.format("Directory '%s' does not exist.\n", directory));
            }
        }

        return path;
    }

    /**
     * Get the file string as a Path.
     *
     * @param file the file which the path represents.
     * @return the Path of the file
     * @throws FileNotFoundException whenever the given string is not a file or
     * does not exist
     */
    public static Path getFile(String file) throws FileNotFoundException {
        Path path = Paths.get(file);

        if (Files.exists(path)) {
            if (!Files.isRegularFile(path)) {
                throw new FileNotFoundException(String.format("'%s' is not a file.\n", file));
            }
        } else {
            throw new FileNotFoundException(String.format("File '%s' does not exist.\n", file));
        }

        return path;
    }

}
