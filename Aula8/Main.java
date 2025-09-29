import java.util.ArrayList;
import java.util.List;

public class Main {
  public static void main(String[] args) {
    List<Token> tokens = new ArrayList<>();

    tokens.add(new Token("id", "x"));
    tokens.add(new Token("operador_condicional", ">"));
    tokens.add(new Token("num", "5"));
    tokens.add(new Token("dois_pontos", ":"));
    
    tokens.add(new Token("id", "x"));
    tokens.add(new Token("operador_atribuicao", "="));
    tokens.add(new Token("num", "1"));
    tokens.add(new Token("EOF", "$"));

    tokens.add(new Token("id", "y"));
    tokens.add(new Token("operador_atribuicao", "="));
    tokens.add(new Token("num", "2"));
    tokens.add(new Token("EOF", "$"));

    Parser parser = new Parser(tokens);
    parser.parse();
  }
}
