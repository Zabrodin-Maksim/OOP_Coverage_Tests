package cz.upce.fei.boop.lexikalnianalyzator.statemachine;

import static cz.upce.fei.boop.lexikalnianalyzator.Counter.counter;
import cz.upce.fei.boop.lexikalnianalyzator.collection.TokenList;
import cz.upce.fei.boop.lexikalnianalyzator.token.*;
import cz.upce.fei.boop.lexikalnianalyzator.token.enums.KeyWord;
import static cz.upce.fei.boop.lexikalnianalyzator.token.enums.KeyWord.*;
import cz.upce.fei.boop.lexikalnianalyzator.token.enums.*;
import static cz.upce.fei.boop.lexikalnianalyzator.token.enums.SeparatorEnum.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.TestName;

/**
 * TODO Sestavit testovací případy třídy StateMachine s cílem dosáhnout co
 * největšího pokrytí.
 * <p>
 * Česky stručný popis JUnit 4 je na http://voho.eu/wiki/java-junit/ nebo
 * https://www.itnetwork.cz/java/testovani/testovani-v-jave-prvni-unit-test-v-junit
 * <p>
 * Třída {@code StateMachine} je součásti projektu Lexikální analyzátor, který
 * byl v minulých letech zadán jako první semestrální práce předmětu objektově
 * orientované programování. Pro vypracování testu a pochopení celého projektu
 * je k dispozici písemné zadání.
 *
 * <p>
 * Požadavky na testovací případy (metody):
 *
 * <ol>
 * <li>Doporučuje se volit název testovací metody podle typu kontroly s vhodným
 * číselným prefixem. Velmi se osvědčilo používat tvarové číslování. Například
 * dvouúrovňové, tj. test001_01, test002_20 apod. To umožňuje vkládat do
 * testovací třídy další testy, které patří do stejného typu kontroly. Číselná
 * identifikace umožňuje rychlejší orientaci v testovacích metodách.
 *
 * <li> Každá testovací metoda bude obsahovat na prvním řádku výpis názvu testu
 * tímto příkazem {@code print(testName.getMethodName()) }. Tím se vytiskne
 * pořadové číslo test a název metody.
 *
 * <li> Instaci třídy {@code StateMachine} nemusíme v testovací metodě vytvářet,
 * protože je automaticky vytvořena před každým spuštěním jakékoliv testovací
 * metody.
 *
 * <li>Instance testované třídy se automaticky vytváří metodou {@code setUp} s
 * anotací {code @Before}.
 *
 * <li>Úklid po vykonání testovací metody provede metoda {@code tearDown} s
 * anotací {code @After}.
 *
 * <li> Velmi se doporučuje aby byla pouze jedna kontrola v testovací metodě,
 * tj. jedna metoda {@code assert}.
 *
 *
 * <li>Pokud se kontroluje reálná číselná hodnota (float, double), tak je nutné
 * zvolit třetí parametr, kterým se volí přesnost porovnání. V našem případě
 * budeme používat přesnost, která dána konstantou {@code DELTA}.
 *
 *
 *
 * </ol>
 *
 *
 * @author
 */
public class StateMachineTest {

    @Rule
    public TestName testName = new TestName();

    static void print(String methodName) {
        System.out.println(String.format("%03d StateMachine.%s   ",
                counter++, methodName));
    }

    static final double DELTA = 1e-9;

    Machine machine;
    TokenList tokens;

    public StateMachineTest() {
    }

    @Before
    public void setUp() {
        tokens = new TokenList();
        machine = new StateMachine(tokens);
    }

    @After
    public void tearDown() {
        tokens = null;
        machine = null;
    }

    @Test
    public void test001_02_identifier() {
        print(testName.getMethodName());
        machine.execute("abcdef\n");
        assertEquals(TokenType.IDENTIFIER, tokens.getToken(0).getType());
    }

    @Test(expected = AnalyzerException.class)
    public void test001_01_AnalyzerException() {  //state 1
        print(testName.getMethodName());
        machine.execute(".\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_02_AnalyzerException() {  //state 1
        print(testName.getMethodName());
        machine.execute("@\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_03_AnalyzerException() {  //state 1
        print(testName.getMethodName());
        machine.execute("Горе от ума\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_04_AnalyzerException() {  //state 1
        print(testName.getMethodName());
        machine.execute("programování\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_05_AnalyzerException() {  //state 1
        print(testName.getMethodName());
        machine.execute("%!#$%\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_06_AnalyzerException() {  //state 2
        print(testName.getMethodName());
        machine.execute("A#a\n");//
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_07_AnalyzerException() {
        print(testName.getMethodName());
        machine.execute("oaoaoaoa.aoaoaoa\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_08_AnalyzerException() {  //state 3
        print(testName.getMethodName());
        machine.execute("0e");//
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_09_AnalyzerException() {   //state 4
        print(testName.getMethodName());
        machine.execute("0x.x0\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_10_AnalyzerException() {   //state 5
        print(testName.getMethodName());
        machine.execute("018\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_11_AnalyzerException() {   //state 6
        print(testName.getMethodName());
        machine.execute("10x\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_12_AnalyzerException() {   //state 7
        print(testName.getMethodName());
        machine.execute("11.x\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_13_AnalyzerException() {   //state 8
        print(testName.getMethodName());
        machine.execute("0.0x00001\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_14_AnalyzerException() {   //state 9
        print(testName.getMethodName());
        machine.execute("1EEEEE\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_15_AnalyzerException() {   //state 10
        print(testName.getMethodName());
        machine.execute("ape!\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_16_AnalyzerException() {   //state 11
        print(testName.getMethodName());
        machine.execute("1e+C++\n");
        fail();
    }

    @Test(expected = AnalyzerException.class)
    public void test001_17_AnalyzerException() {   //state 12
        print(testName.getMethodName());
        machine.execute("1o-1o-1o-1--11-o\n");
        fail();
    }

      @Test
    public void test001_03_identifier() {   //1 to 2
        print(testName.getMethodName());
        machine.execute("A0aoaoa\n");
        assertEquals(TokenType.IDENTIFIER, tokens.getToken(0).getType());
    }

    
    @Test
    public void test001_23_identifier() {   //1 to 6
        print(testName.getMethodName());
        machine.execute("1000.\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_12_identifier() {   //1 to 13
        print(testName.getMethodName());
        machine.execute("; 123\n");
        assertEquals(TokenType.SEPARATOR, tokens.getToken(0).getType());
    }

    @Test
    public void test001_13_identifier() {   //1 to 13
        print(testName.getMethodName());
        machine.execute("== null\n");
        assertEquals(TokenType.SEPARATOR, tokens.getToken(0).getType());
    }

    @Test
    public void test001_14_identifier() {   //1 to 13
        print(testName.getMethodName());
        machine.execute(": : : :\n");
        assertEquals(TokenType.SEPARATOR, tokens.getToken(0).getType());
    }

    @Test
    public void test001_15_identifier() {   //1 to 13
        print(testName.getMethodName());
        machine.execute(": : , : :\n");
        assertEquals(TokenType.SEPARATOR, tokens.getToken(0).getType());
    }

    @Test
    public void test001_16_identifier() {   //1 to 13
        print(testName.getMethodName());
        machine.execute("\t\n");
        assertEquals(TokenType.SEPARATOR, tokens.getToken(0).getType());
    }

    @Test
    public void test001_17_identifier() {   //1 to 14
        print(testName.getMethodName());
        machine.execute("/ / / / * / / / /\n");
        assertEquals(TokenType.OPERATOR, tokens.getToken(0).getType());
    }

    @Test
    public void test001_19_identifier() {   //1 to 14
        print(testName.getMethodName());
        machine.execute("+ - +\n");
        assertEquals(TokenType.OPERATOR, tokens.getToken(0).getType());
    }
        @Test
    public void test001_19a_identifier() {   //10 to 14
        print(testName.getMethodName());
        machine.execute("ad+\n");
        assertEquals(TokenType.IDENTIFIER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_21_identifier() {   //1 to 3 to 7
        print(testName.getMethodName());
        machine.execute("0.\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_22_identifier() {   //1 to 15
        print(testName.getMethodName());
        machine.execute("{\n");
        assertEquals(State.STATE15, machine.getState());
    }
        @Test
    public void test001_22a_identifier() {   //1 to 15
        print(testName.getMethodName());
        machine.execute("{}\n");
        assertEquals(State.STATE1, machine.getState());
    }
    
    @Test
    public void test001_04_identifier() {   //1 to 3 to 4
        print(testName.getMethodName());
        machine.execute("0x7Aa\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }
    
//    @Test
//    public void test001_04a_identifier() {   //1 to 3 to 4 to 10
//        print(testName.getMethodName());
//        machine.execute("0x7Aa§\n");
//        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
//    }

    @Test
    public void test001_05_identifier() {   //1 to 3 to 5
        print(testName.getMethodName());
        machine.execute("0123\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_06_identifier() {   //1 to 3 to 7 to 8
        print(testName.getMethodName());
        machine.execute("0.12\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }


    @Test
    public void test001_08_identifier() {   //1 to 3 to 7 to 9 to 12
        print(testName.getMethodName());
        machine.execute("0.e12\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_07_identifier() {   //1 to 3 to 7 to 9 to 11 to 12
        print(testName.getMethodName());
        machine.execute("0.e+1\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }
    @Test
    public void test001_09_identifier() {   //1 to 6
        print(testName.getMethodName());
        machine.execute("1000\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_10_identifier() {   //1 to 6 to 9 to 11 to 12
        print(testName.getMethodName());
        machine.execute("11E-22\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }
    
    @Test
    public void test001_20_identifier() {   //1 to 3 to 7 to 8 to 9 to 12
        print(testName.getMethodName());
        machine.execute("0.99e9\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }

    @Test
    public void test001_11_identifier() {   //1 to 6 to 9 to 12
        print(testName.getMethodName());
        machine.execute("11e222\n");
        assertEquals(TokenType.LONG_NUMBER, tokens.getToken(0).getType());
    }
    

  

}
