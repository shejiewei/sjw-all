package spi;

import com.sun.tools.javac.util.ServiceLoader;
/**
 * Created by shejiewei on 2019/1/23.
 */
public class test
{

     public static void main(String[] args) {


             ServiceLoader<Robot> serviceLoader = ServiceLoader.load(Robot.class);
             System.out.println("Java SPI");
             serviceLoader.forEach(Robot::sayHello);

      }
}
