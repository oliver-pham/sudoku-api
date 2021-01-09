package com.oliver.sudoku;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SudokuApi {

    private static final Logger logger = LoggerFactory.getLogger(SudokuApi.class);

    @GetMapping("/api/generate")
    public ResponseEntity<String> generatePuzzle() {
        try {
            Puzzle puzzle = new Puzzle();
            puzzle.generatePuzzleString();
            return ResponseEntity.ok(puzzle.getPuzzleString());
        } catch (Exception e) {
            logger.error("Generation Error: ", e);
            return ResponseEntity.badRequest().body("Error: Cannot generate a puzzle at this time!");
        }
    }

    @PostMapping("/api/solve")
    public ResponseEntity<String> solvePuzzle(@RequestBody Puzzle puzzle) {
        try {
            String solution = puzzle.getPuzzleSolutionString();
            return ResponseEntity.ok(solution);
        } catch (Exception e) {
            logger.error("Solving Error: ", e);
            return ResponseEntity.badRequest().body("Error: Puzzle cannot be solved!");
        }
        
    }
}