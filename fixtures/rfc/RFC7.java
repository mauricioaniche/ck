package rfc;

class RFC7 {
    public void m1() {
        Integer[] someNumbers = {1, 2, 3, 4};

        Stream.of(someNumbers).map(n -> n * 2).forEach(System.out::println);
    }

    public void m2() {
        Map<Character, List<Integer>> byAlphabet = aList
                .stream()
                .collect(Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
                        Collectors.mapping(Avatar::getId, Collectors.toList())));
    }
}