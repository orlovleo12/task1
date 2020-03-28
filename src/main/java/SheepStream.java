package main.java;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/*8. Класс Овца - возраст, цвет шерсти, вес. С помощью стримов написать методы,
которые будут из коллекции овец получать:
суммарный вес.
количество овец с наиболее часто встречавшимся цветом шерсти
максимальный возраст
самую старую овцу с заданным в аргументах цветом шерсти*/
public class SheepStream {
    public static void main(String[] args) {

        List<Sheep> sheepList = new ArrayList<>();
        sheepList.add(new Sheep(12, "black", 50));
        sheepList.add(new Sheep(15, "black", 50));
        sheepList.add(new Sheep(25, "black", 50));
        sheepList.add(new Sheep(100, "white", 50));
        sheepList.add(new Sheep(4, "black", 50));
        sheepList.add(new Sheep(8, "black", 50));
        sheepList.add(new Sheep(45, "white", 50));
        sheepList.add(new Sheep(411, "black", 50));

        //суммарный вес.
        int allWeight = sheepList.stream().reduce(0, (partialAgeResult, sheep) -> partialAgeResult + sheep.getWeight(), Integer::sum);
        System.out.println(allWeight);
        //количество овец с наиболее часто встречавшимся цветом шерсти
        Map<String, List<Sheep>> groupByColor =
                sheepList.stream().collect(Collectors.groupingBy(Sheep::getColor));

        System.out.println( groupByColor.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .findFirst().get().getValue().size());
        //максимальный возраст
        Sheep maxAgeSheep = sheepList
                .stream()
                .max(Comparator.comparing(Sheep::getAge))
                .orElseThrow(NoSuchElementException::new);//чтобы не надо было использовать Optional<Sheep>
        System.out.println(maxAgeSheep.getAge());
        //самую старую овцу с заданным в аргументах цветом шерсти
        Map<String, List<Sheep>> groupByColor2 =
                sheepList.stream().collect(Collectors.groupingBy(Sheep::getColor));

        System.out.println( groupByColor2.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .filter(x->"white".equals(x.getKey())).findFirst().get().getValue()
                .stream()
                .max(Comparator.comparing(Sheep::getAge))
                .orElseThrow(NoSuchElementException::new).getAge());
    }
     static class Sheep {
        private int age;
        private String color;
        private int weight;

        public Sheep(int age, String color, int weight) {
            this.age = age;
            this.color = color;
            this.weight = weight;
        }


        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }
    }

}
