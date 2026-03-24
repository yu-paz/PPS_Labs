import java.util.*;
import java.util.stream.*;

public class Week10_labs
{
    public static void main(String[] args)
    {
        List<String> fruit = Arrays.asList("cherry","banana","berry","apple","cherry","kiwi","fig","date","lemon","honeydew","cherry","elderberry","apple","banana","grape");

        // Collect elements into a Set
        Set<String> fruitSet = fruit.stream()
            .collect(Collectors.toSet());
        System.out.println("Set: " + fruitSet);
		System.out.println();

        // Collect the fruit into groups based on their first character
        Map<Character, List<String>> groupedByFirstChar = fruit.stream()
            .collect(Collectors.groupingBy(f -> f.charAt(0)));
        System.out.println("Grouped by first char: " + groupedByFirstChar);
		System.out.println();

        // Group fruit by the length of the name
        Map<Integer, List<String>> groupedByLength = fruit.stream()
            .collect(Collectors.groupingBy(String::length));
        System.out.println("Grouped by length: " + groupedByLength);
		System.out.println();

        // Collect the fruit that has "erry" in it
        List<String> erryFruit = fruit.stream()
            .filter(f -> f.contains("erry"))
            .collect(Collectors.toList());
        System.out.println("Contains 'erry': " + erryFruit);
		System.out.println();

        // Create a partition of fruit based on if it contains "erry"
        Map<Boolean, List<String>> erryPartition = fruit.stream()
            .collect(Collectors.partitioningBy(f -> f.contains("erry")));
        System.out.println("Partition by 'erry': " + erryPartition);
		System.out.println();

        // Collect the fruit that has 5 or less symbols
        List<String> shortFruit = fruit.stream()
            .filter(f -> f.length() <= 5)
            .collect(Collectors.toList());
        System.out.println("5 or fewer characters: " + shortFruit);
		System.out.println();

        // Find the total number of symbols in all the fruit stored
        int totalSymbols = fruit.stream()
            .mapToInt(String::length)
            .sum();
        System.out.println("Total symbols: " + totalSymbols);
		System.out.println();


		List<Integer> data = Arrays.asList(87, 23, 45, 100, 6, 78, 92, 44, 13, 56, 34, 99, 82, 19, 1012, 78, 45, 90, 23, 56, 78, 100, 3, 43, 67, 89, 21, 34, 10);

        // Partition data based on if >=50
		List<Integer> greaterThan50 = data.stream()
			.filter(n -> n >= 50)
			.collect(Collectors.toList());
		System.out.println("Greater than or equal to 50: " + greaterThan50); 
		System.out.println();

		//divide data into groups based on the remainder when divided by 7
		Map<Integer, List<Integer>> groupedByRemainder = data.stream()
			.collect(Collectors.groupingBy(n -> n % 7));
		System.out.println("Grouped by remainder when divided by 7: " + groupedByRemainder);
		System.out.println();
		
		//find the sum of the data
		int sum = data.stream()
			.mapToInt(Integer::intValue)
			.sum();
		System.out.println("Sum of data: " + sum);
		System.out.println();

		//collect the unique values
		List<Integer> uIntegers = data.stream()
			.distinct()
			.collect(Collectors.toList());
		System.out.println("Unique values: " + uIntegers);
		System.out.println();

        //compute the cube of each values
		List<Integer> cubes = data.stream()
			.map(n -> n * n * n) 
			.collect(Collectors.toList());
		System.out.println("All of the values after being cubed: " + cubes);
		System.out.println();

		//find the sum of the cubes of each value
		int sumOfCubes = cubes.stream()
			.mapToInt(Integer::intValue)
			.sum();
		System.out.println("summ of all cubed values: " + sumOfCubes);
		System.out.println();

		//increase the value of each element by 5
		List<Integer> addTwo = data.stream()
			.map(n -> n + 2)
			.collect(Collectors.toList());
		System.out.println("All values after adding 2: " + addTwo);
		System.out.println();

		//compute the cube of the even values
		List<Integer> cubedEvenNums = data.stream()
			.filter(n -> n % 2 == 0)
			.map(n -> n * n * n)
			.collect(Collectors.toList());
		System.out.println("all even values after being cubed: " + cubedEvenNums);

   }
}
