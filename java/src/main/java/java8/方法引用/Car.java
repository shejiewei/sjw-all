package java8.方法引用;

import java.util.Arrays;
import java.util.List;

/**
 * Created by shejiewei on 2019/2/17.
 */
public class Car {
    @FunctionalInterface
    public interface  Supplier<T>
    {
        T get();
    }
    public static Car create(final Supplier<Car> supplier)
    {
        return supplier.get();
    }

    public  static void collide(final Car car)
    {
        System.out.println("collied"+car.toString());
    }

    public void follow(Car annother)
    {
        System.out.println("follow"+annother.toString());
    }

    public void repair(){
        System.out.println("repair"+this.toString());
    }
     public static void main(String[] args) {
      Car car1=Car.create(Car::new);
      Car car2=Car.create(Car::new);
      Car car3=Car.create(Car::new);
      Car car4=new Car();

         List<Car> cars = Arrays.asList(car1, car2, car3, car4);
         //静态方法引用class:static_method
         cars.forEach(Car::collide);
      //特定类的任意对象的方法引用 class:method
      cars.forEach(Car::repair);

      //特定对象的方法引用 instance:method
    final Car police=Car.create(Car::new);
    cars.forEach(police::follow);
     }


}
