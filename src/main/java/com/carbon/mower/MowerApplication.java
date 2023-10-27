package com.carbon.mower;

import com.carbon.mower.service.FileService;
import com.carbon.mower.service.MowerService;

import java.io.IOException;

public class MowerApplication {

    private static final int NUMBER_PARAMETER_EXPECTED = 2;
    private static final String INVALID_PARAMETERS_MESSAGE =
            "Two mandatory parameters ; file input path and file output path";

    public static void main(String... args) {

        if (args.length != NUMBER_PARAMETER_EXPECTED) {
            System.out.println(INVALID_PARAMETERS_MESSAGE);
            return;
        }

        var resourceService = new FileService(args[0], args[1]);
        var mowerService = new MowerService();

        try {
            var resource = resourceService.extractResource();
            var lawn = resource.getLawn();
            var mowers = resource.getMowers();

            mowers = mowerService.runMowers(mowers, lawn);
            resourceService.writeMowersToFile(mowers);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
