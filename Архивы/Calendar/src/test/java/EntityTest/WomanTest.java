package EntityTest;

import entity.Woman;

public class WomanTest implements Test{
    @Override
    public void test() {

        System.out.println("Создание элемента Woman");
        Woman woman = new Woman("Света", "+7(925)-456-98-65","svetka@mail.ru");
        System.out.println("--Успешно");
    }
}
