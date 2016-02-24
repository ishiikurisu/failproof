import br.eng.crisjr.failproof.*;

public class BS {
    public static void main(String[] args) {
        String[] stuff = web.process(args[0]);
        for (String it: stuff) {
            System.out.println(extractList(it));
        }
    }

    private static String extractList(String inlet) {
        String[] stuff = inlet.split("\n");
        String outlet = "# " + stuff[0] + "\n";

        for (int i = 1; i < stuff.length; i++) {
            outlet += "+ " + stuff[i] + "\n";
        }

        return outlet;
    }
}
