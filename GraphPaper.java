import java.util.*;
import java.util.Collections;

public class GraphPaper {
    public static ArrayList<Name_and_Id> person = new ArrayList<Name_and_Id>();
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) {
        Checking();
        List_Maker();
        Graph_Saz();
    }

    public static boolean kudum_araye(String Name, ArrayList<Name_and_Id> arraye) {
        for (Name_and_Id person : arraye) {
            if (person.Name.equals(Name)) {
                return true;
            }
        }
        return false;
    }

    public static void Graph_Saz() {
        ArrayList<Name_and_Id> Name_List = new ArrayList<>();
        ArrayList<Name_and_Id> Aid_Name_List = new ArrayList<>();

        for (Name_and_Id person : person) {
            if (person.ancestor.equals("anonymous")) {
                Name_List.add(person);
            }
        }
        while (Name_List.size() != 0) {
            for (Name_and_Id person_1 : Name_List) {
                if (person_1.descendant.size() > 0) {
                    System.out.print(person_1.Name + "->");
                } else {
                    System.out.print(person_1.Name);
                }
                for (String person_2 : person_1.descendant) {
                    System.out.print(person_2 + " ");
                    int Place = Place_Finder(person_2, person);
                    Aid_Name_List.add(person.get(Place));
                }
                System.out.println();
            }
            while (Name_List.size() != 0) {
                Name_List.remove(0);
            }
            Name_List.addAll(Aid_Name_List);
            while (Aid_Name_List.size() != 0) {
                Aid_Name_List.remove(0);
            }
        }
    }

    public static int Place_Finder(String name, ArrayList<Name_and_Id> Mesal) {
        for (int i = 0; i < Mesal.size(); i++) {
            if (Mesal.get(i).Name.equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public static void List_Maker() {

        for (Name_and_Id person : person) {
            Collections.sort(person.descendant);
        }

        for (int i = 0; i < person.size(); i++) {
            for (int j = 0; j < person.size() - i; j++) {
                if (person.get(i).Name.compareTo(person.get(j).Name) > 0) {
                    Collections.swap(person, i, j);
                }
            }
        }
    }

    public static void Checking() {
        int number = Integer.parseInt(scn.nextLine());
        for (int i = 0; i < number; i++) {
            String[] in = scn.nextLine().split(" ");
            Name_and_Id Child = new Name_and_Id(in[0]);
            Name_and_Id Dad = new Name_and_Id(in[1]);
            Child.ancestor = Dad.Name;

            if (kudum_araye(Dad.Name, person) && !kudum_araye(Child.Name, person)) {
                int Place = Place_Finder(Dad.Name, person);
                person.get(Place).descendant.add(Child.Name);
                person.add(Child);
            } else if (!kudum_araye(Dad.Name, person) && kudum_araye(Child.Name, person)) {
                int Place = Place_Finder(Child.Name, person);
                person.get(Place).ancestor = Dad.Name;
                Dad.descendant.add(Child.Name);
                person.add(Dad);
            } else if (kudum_araye(Dad.Name, person) && kudum_araye(Child.Name, person)) {
                int Dad_Place = Place_Finder(Dad.Name, person);
                int Child_Place = Place_Finder(Child.Name, person);
                person.get(Dad_Place).descendant.add(Child.Name);
                person.get(Child_Place).ancestor = Dad.Name;
            } else {
                Dad.descendant.add(Child.Name);
                person.add(Dad);
                person.add(Child);
            }
        }
    }

}

class Name_and_Id {

    public String ancestor = "anonymous";
    public ArrayList<String> descendant = new ArrayList<String>();
    public String Name;

    Name_and_Id(String n) {
        this.Name = n;
    }
}
