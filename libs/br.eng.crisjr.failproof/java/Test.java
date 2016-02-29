import br.eng.crisjr.failproof.web;

public class Test {
  public static final void main(String[] args) {
    String[] stuff = web.getLinks(args[0]);

    for (String it: stuff) {
      System.out.println(it);
    }
  }
}
