package cz.upce.fei.boop.lexikalnianalyzator;

/**
 *
 * @author karel@simerda.cz
 */
public class Counter {
    // TODO Třída se natahuje při spuštění každého testu! Proč? 
    // Protože používáme @Rule v JUnit, je to pravidla ktere spouštěná pokaždé, když spustím test
   public static  int counter;
   static {
      counter = 1; 
   }
}
