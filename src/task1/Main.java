package task1;

public class Main {

    public static void main(String[] args) {

        var cats = Cat.makeCats(10);
        Printer.print(cats);

        /*********/

        cats.sort(Cat::byBreed);
        Printer.print(cats);

        cats.sort(Cat::byNameAndAge);
        Printer.print(cats);

        final Cat.Color removableColor = Cat.Color.SILVER;

        cats.removeIf(cat -> cat.getColor() == removableColor);
        Printer.print(cats);

        cats.removeIf(Cat::hasNameThisLong);
        Printer.print(cats);


        /*********/
    }

}
