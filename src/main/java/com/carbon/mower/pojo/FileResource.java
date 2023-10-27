package com.carbon.mower.pojo;


import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

public record FileResource(List<String> lines) {

    private static final Pattern LETTER_ONLY = Pattern.compile("^[A-Z]+$");

    public PositionBO getLawn() {
        var lines = this.lines;
        var lawn = lines.getFirst().split(" ");

        return new PositionBO(
                Integer.parseInt(lawn[0]),
                Integer.parseInt(lawn[1]));
    }

    public List<MowerBO> getMowers() {
        var lines = this.lines.subList(1, this.lines.size());
        var mowers = new LinkedList<MowerBO>();

        var position = new PositionBO(0, 0);
        var orientation = OrientationType.N;

        for (String line : lines) {
            var splitLine = line.split(" ");
            var isInstruction = LETTER_ONLY.matcher(line).matches();

            if (isInstruction) {
                mowers.addLast(new MowerBO(position, orientation, line));
                continue;
            }
            position = new PositionBO(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[1]));
            orientation = OrientationType.valueOf(splitLine[2]);
        }
        return mowers;
    }
}
