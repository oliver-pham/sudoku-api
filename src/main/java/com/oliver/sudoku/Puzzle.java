package com.oliver.sudoku;

import com.qqwing.QQWing;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.qqwing.PrintStyle;


public class Puzzle {
    private String puzzleString;

    public String getPuzzleString() {
        return puzzleString;
    }

    public void generatePuzzleString() {
        QQWing qqWing = new QQWing();
        // Generate a puzzle
        qqWing.generatePuzzle();
        // Convert the puzzle to a one-line string
        qqWing.setPrintStyle(PrintStyle.ONE_LINE);
        // Initialize the puzzle string representation of the puzzle
        puzzleString = qqWing.getPuzzleString();
    }

    public void setPuzzleString(String initString) {
        // Validate the initial string representation of the puzzle with RegEx
        Pattern puzzlePattern = Pattern.compile("^([0-9]|\\.){81}$");
        Matcher matcher = puzzlePattern.matcher(initString);
        boolean isValidPuzzle = matcher.find();
        if (isValidPuzzle) {
            puzzleString = initString;
        }
        else {
            generatePuzzleString();
        }
    }

    public int[] getPuzzleArray() {
        int[] puzzle = new int[QQWing.BOARD_SIZE];

        for (int c = 0; c < QQWing.BOARD_SIZE; c++) {
            if (puzzleString.charAt(c) >= '1' && puzzleString.charAt(c) <= '9') {
                puzzle[c] = puzzleString.charAt(c) - '0';
            }
            if (puzzleString.charAt(c) == '.' || puzzleString.charAt(c) == '0') {
                puzzle[c] = 0;
            }
        }

        return puzzle;
    }

    public String getPuzzleSolutionString() {
        QQWing qqWing = new QQWing();

        // Convert the string representation of the puzzle
        int[] puzzle = getPuzzleArray();
        // Set the converted version
        qqWing.setPuzzle(puzzle);
        // Solve the puzzle
        qqWing.solve();
        // Convert the puzzle to a one-line string
        qqWing.setPrintStyle(PrintStyle.ONE_LINE);

        if (qqWing.isSolved())
            return qqWing.getSolutionString();
        else
            throw new IllegalArgumentException("Not a Solvable Puzzle!");
    }
}