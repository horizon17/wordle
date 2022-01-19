package scc;

import java.io.Console;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

	/**
	 * WORDLE:
	 * ввести зеленые буквы: s2 - буква s на позици 2
	 * ввести желтые буквы: z_ буква z без позиции
	 * вывести подходящие слова - ff
	 * продолжить ввод
	 *
	 */
	public static void main(String[] args) throws IOException {

		Main main = new Main();
		main.worker();

	}

	public void worker() throws IOException {

		HashMap<Integer, String> grn = new HashMap<>();
		List<String> yell = new ArrayList<>();

		List<String> arr = new ArrayList<>();
		List<String> allW = Files.readAllLines(Path.of("C:/tmp/input/words.txt"));
		arr = allW.stream().filter(r -> r.length()==5).collect(Collectors.toList());
		Console console = System.console();
		while (true) {
			String nextIn = console.readLine("Enter letters: ");
			if (nextIn.contains("_")) {
				yell.add(nextIn.replace("_", "")); // yellow letter without position
			} else if (nextIn.equals("ff")) {
				List<String> out = arr;
				if (yell.size() != 0 ) {
					out = arr.stream().filter(r -> yell.stream().allMatch(s -> r.contains(s)))
							.collect(Collectors.toList());
				}
				if (grn.size() != 0 ) {
					out = out.stream().filter(r -> grn.entrySet().stream()
							.allMatch(e -> r.substring(e.getKey(),e.getKey()+1).equals(e.getValue())))
							.collect(Collectors.toList());
				}
				console.printf(out.toString());
			} else {
				grn.put(Integer.valueOf(nextIn.substring(1,2)),nextIn.substring(0,1)); // green letter with position
			}
		}
	}


}
