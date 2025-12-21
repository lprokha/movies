package task1;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// код можно менять только в особо отмеченном месте этого файла,
// так же возможно вам понадобится добавить что-то в 
// блок import и поменять имя пакета
// в остальных местах этого файла код менять не разрешается.

final class Cat {
    private static final Random r = new Random();
    private static final List<String> names = List.of("Peach", "Ginger", "Toby", "Seth", "Tibbles", "Tabby", "Poppy",
            "Millie", "Daisy", "Jasper", "Misty", "Minka");
    private final String name;
    private final int age;
    private final Breed breed;
    private final Color color;

    private Cat() {
        name = names.get(r.nextInt(names.size()));
        age = r.nextInt(10) + 1;
        breed = Breed.values()[r.nextInt(Breed.values().length)];
        color = Color.values()[r.nextInt(Color.values().length)];
    }

    public static final List<Cat> makeCats(int amount) {
        // это называется конвейерная обработка.
        // будем проходить на одном из занятий
        return Stream.generate(Cat::new)
                .limit(amount)
                .collect(Collectors.toList());
    }

    public final Color getColor() {
        return color;
    }

    public final String getName() {
        return name;
    }

    public final int getAge() {
        return age;
    }

    public final Breed getBreed() {
        return breed;
    }

    enum Color {
        TABBY, SILVER, GRAY, PEACH, GINGER, TORTIE
    }

    enum Breed {
        SPHINX, BRITAIN, SCOTTISH, VULGARIS, ORIENTAL
    }

    /*********/

    public static int byBreed(Cat cat1, Cat cat2) {
        return (cat1.getBreed().compareTo(cat2.getBreed()));
    }

    public static int byNameAndAge(Cat cat1, Cat cat2) {
        int comparison = cat1.getName().compareTo(cat2.getName());
        if (comparison == 0) {
            return (Integer.compare(cat1.getAge(), cat2.getAge()));
        }
        return comparison;
    }

    public boolean hasNameThisLong() {
        return name.length() == 5;
    }

    /*********/
}
