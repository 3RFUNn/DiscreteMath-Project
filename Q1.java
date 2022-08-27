import java.util.*;

public class Majmue {


    public static <T> List<List<T>> MajmueTavani(List<T> List) {
        List<List<T>> ZirMajmue = new ArrayList<>();
        ZirMajmue.add(new ArrayList<T>());
        for (int i = 0; i < List.size(); i++) {
            int TulZirMajmue = ZirMajmue.size();
            for (int ZirMajmueDaruni = 0; ZirMajmueDaruni < TulZirMajmue; ZirMajmueDaruni++) {
                List<T> ZirMajmue2 = new ArrayList<>(ZirMajmue.get(ZirMajmueDaruni));
                ZirMajmue2.add(List.get(i));
                ZirMajmue.add(ZirMajmue2);
            }

        }
        Comparator<List<T>> moghayese = (o1, o2) -> {
            int Tul2 = o2.size();
            int Tul1 = o1.size();
            if (Tul2 != Tul1) {
                return Tul1 - Tul2;
            }
            for (int i = 0; i < Tul1; i++) {
                if (o2.get(i) != o1.get(i)) {
                    return (Integer) o1.get(i) - (Integer) o2.get(i);
                }
            }
            return 0;
        };
        Collections.sort(ZirMajmue, moghayese);
        return ZirMajmue;
    }


    public static <T> String PowerGenerator(List<List<T>> Tavani) {
        ArrayList<String> answer = new ArrayList<>();
        for (List<T> Majmue : Tavani) {
            answer.add(Majmue.toString().replace('[', '{').replace(']', '}')
                    .replaceAll("\\s", ""));
        }
        return String.join(" , ", answer);
    }


    public static <T> String MajmueSaz(Set<T> set) {
        return set.toString().replace('[', '{').replace(']', '}')
                .replaceAll("\\s", "");
    }

    public static <T> Set<T> Tafazol(Set<T> a, Set<T> b) {
        Set<T> Eshterak = new TreeSet<>(a);
        Eshterak.removeAll(b);
        return Eshterak;
    }

    public static <T> Set<T> Ejtema(Set<T> a, Set<T> b) {
        Set<T> Eshterak = new TreeSet<>(a);
        Eshterak.addAll(b);
        return Eshterak;
    }


    public static <T> Set<T> Delta(Set<T> a, Set<T> b) {
        return Tafazol(Ejtema(a, b), Eshterak(a, b));
    }

    public static <T> Set<T> Eshterak(Set<T> a, Set<T> b) {
        Set<T> Eshterak = new TreeSet<>(a);
        Eshterak.retainAll(b);
        return Eshterak;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int SetA = input.nextInt();
        Set<Integer> ASet = new TreeSet<>();
        for (int i = 0; i < SetA; i++) {
            ASet.add(input.nextInt());
        }
        int SetB = input.nextInt();
        Set<Integer> Bset = new TreeSet<>();
        for (int i = 0; i < SetB; i++) {
            Bset.add(input.nextInt());
        }
        System.out.println("A -> "
                + PowerGenerator(MajmueTavani(new ArrayList<Integer>(ASet))));
        System.out.println("B -> "
                + PowerGenerator(MajmueTavani(new ArrayList<Integer>(Bset))));
        System.out.println("A⋃B:" + MajmueSaz(Ejtema(ASet, Bset)));
        System.out.println("A⋂B:" + MajmueSaz(Eshterak(ASet, Bset)));
        System.out.println("A-B:" + MajmueSaz(Tafazol(ASet, Bset)));
        System.out.println("A△B:" + MajmueSaz(Delta(ASet, Bset)));

    }
}
