package year2022.day10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class B {
    public static void main(String[] args) {

        Scanner scanner = null;
        try {
            scanner = new Scanner(Paths.get(new File(A.class.getResource("input.txt").getPath()).getPath()));
        } catch (
                IOException e) {
            throw new RuntimeException(e);
        }
        List<String> content = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            content.add(line);
        }
        Puzzle10 puzzle10 = new Puzzle10();
        puzzle10.programState = puzzle10.runProgram(content);
        for (int i = 0; i < puzzle10.programState.size(); i++) {
            System.out.println(puzzle10.programState.get(i).x);
        }
    }
}

class Puzzle10 {
    private static final String DATA_FILE = "input.txt";
    private static final int WIDTH = 40;
    private static final int HEIGHT = 6;

    public List<State> programState;

    public Object solve1(String content) {
        return IntStream.range(0, HEIGHT)
                .map(clock -> clock * WIDTH + 20)
                .map(clock -> stateAt(programState, clock).x * clock)
                .sum();
    }

    public Object solve2(String content) {
        final var spritePos = IntStream.range(0, WIDTH * HEIGHT)
                .mapToObj(pixel -> stateAt(programState, pixel + 1))
                .collect(Collectors.toList());

        final var screen = IntStream.range(0, WIDTH * HEIGHT)
                .mapToObj(pixel -> spritePos.get(pixel).x - 1 <= (pixel % WIDTH)
                        && (pixel % WIDTH) <= spritePos.get(pixel).x + 1 ? "#" : ".")
                .collect(Collectors.joining());

        return "\n" + IntStream.range(0, HEIGHT)
                .mapToObj(i -> screen.substring(i * WIDTH, (i + 1) * WIDTH))
                .collect(Collectors.joining("\n"));
    }

    public List<State> runProgram(List<String> commands) {
        List<State> programState = new ArrayList<>();
        programState.add(new State(0, 0));

        var currentState = new State(1, 1);

        for (var command : commands) {
            var toks = command.split(" ");
            var arg = toks.length == 2 ? Integer.parseInt(toks[1]) : null;

            currentState = OP_MAP.get(toks[0]).execute(currentState, arg);
            programState.add(currentState);
        }

        return programState;
    }

    private State stateAt(List<State> programState, int t) {
        for (int i = 0; i < programState.size() - 1; i++) {
            if (programState.get(i).clock <= t && t < programState.get(i + 1).clock) {
                return programState.get(i);
            }
        }

        return programState.get(programState.size() - 1);
    }

    private final Map<String, Instruction> OP_MAP = Map.of(
            "noop", new Instruction(1, (x, y) -> x),
            "addx", new Instruction(2, (x, y) -> x + y));

    class Instruction {
        int clockCycles;
        BiFunction<Integer, Integer, Integer> func;

        public Instruction(int clockCycles, BiFunction<Integer, Integer, Integer> func) {
            this.clockCycles = clockCycles;
            this.func = func;
        }

        public State execute(State state, Integer arg) {
            return new State(state.clock + clockCycles, func.apply(state.x, arg));
        }
    }

    class State {
        int clock;
        int x;

        public State(int clock, int x) {
            this.clock = clock;
            this.x = x;
        }
    }


    public String getDataFilePath() {
        return DATA_FILE;
    }

    public String getPuzzleName() {
        return "Day 10 - Cathode-Ray Tube";
    }
}