import java.util.ArrayList;
import java.util.LinkedList;

public class Main {
    static void main() {

        //Operações Get em ArrayList tendem a ser mais perfomáticas a medida em que a lista cresce em número de elementos.
        ArrayList<String> arrayListNames = new ArrayList<>();

        arrayListNames.add("Hugo");
        arrayListNames.add("Yuji");
        System.out.println(arrayListNames.get(0));

        //Operações de add ou remove no início e no fim em LinkedList tendem a ser mais perfomáticas a medida em que a lista cresce em número de elementos.
        LinkedList<String> linkedListNames = new LinkedList<>();

        linkedListNames.add("Hugo");
        linkedListNames.add("Yuji");

        linkedListNames.removeLast();
        System.out.println(linkedListNames);
    }
}
