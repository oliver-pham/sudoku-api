package com.oliver.sudoku;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import net.minidev.json.JSONObject;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SudokuApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void shouldGeneratePuzzle() throws Exception {
		Pattern puzzlePattern = Pattern.compile("^([0-9]|\\.){81}$");
		Matcher matcher = puzzlePattern.matcher(this.restTemplate.getForObject("http://localhost:" + port + "/api/generate", String.class));
		assertTrue(matcher.find());
	}

	@Test
	void shouldSolvePuzzle() throws Exception {
		// .8.....6.......12..31.26....9....8.....9.74....2....1...91......7....64....4..239
		JSONObject request = new JSONObject();
		request.put("puzzleString", ".8.....6.......12..31.26....9....8.....9.74....2....1...91......7....64....4..239");
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/api/solve", request, String.class)).contains("284719365967354128531826794395241876618937452742685913429163587873592641156478239");
	}
}
