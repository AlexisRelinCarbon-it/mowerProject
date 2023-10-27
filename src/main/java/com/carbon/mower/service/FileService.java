package com.carbon.mower.service;

import com.carbon.mower.pojo.FileResource;
import com.carbon.mower.pojo.MowerBO;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;


public class FileService {

    private final String inputPath;
    private final String outputPath;

    public FileService(String inputPath, String outputPath) {
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public FileResource extractResource() throws IOException {
        var path = Path.of(inputPath);
        return new FileResource(Files.readAllLines(path, StandardCharsets.UTF_8));
    }

    public void writeMowersToFile(List<MowerBO> mowers) throws IOException {
        var path = Path.of(outputPath);
        var mowersString = mowers.stream()
                .map(MowerBO::toStringForFile)
                .collect(Collectors.toList());
        Files.write(path, mowersString);
    }
}
