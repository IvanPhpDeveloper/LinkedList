package TheBestLinkedList;

import java.util.Iterator;


public class Test {


    private static final TheBestLinkedList<String> theBestLinkedList = new TheBestLinkedList<String>();

    public static void main(String[] args) {
             theBestLinkedList.add("green");
             theBestLinkedList.add("yellow");
             theBestLinkedList.add("red");

//             theBestLinkedList.remove(2);

             theBestLinkedList.set(1,"white");
             for(String color:theBestLinkedList){
                 System.out.println(color);
             }

        System.out.println(theBestLinkedList.contains("white"));

        Iterator iter=theBestLinkedList.iterator();
        System.out.println(iter.next());//green
        System.out.println(iter.next());
        System.out.println(iter.hasNext());
        iter.remove();//should be no more 3
        System.out.println(iter.hasNext());//false

        for(String color:theBestLinkedList){
            System.out.println(color);
        }

        System.out.println(theBestLinkedList.indexOf("green"));
        System.out.println(theBestLinkedList.lastIndexOf("red"));
    }
}