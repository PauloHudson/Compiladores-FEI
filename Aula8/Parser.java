import java.util.List;

public class Parser {

  private final List<Token> tokens;
  private Token token;

  public Parser(List<Token> tokens) {
    this.tokens = tokens;
  }

  public void parse() {
    // Cria uma cópia dos tokens para tentar as duas regras sem perder o estado
    List<Token> tokensBackup = new java.util.ArrayList<>(tokens);
    token = getNextToken();
    boolean reconheceu = false;
  // Tenta if/else sem reportar erro (para não poluir a saída caso o while seja válido)
  boolean tentouIfElse = ifelse(false) && matchT("EOF");
    if (tentouIfElse) {
      reconheceu = true;
    } else {
      // Restaura os tokens e o token para tentar o whileLoop do início
      tokens.clear();
      tokens.addAll(tokensBackup);
      token = getNextToken();
      if (whileLoop() && matchT("EOF")) {
        reconheceu = true;
      }
    }
    if (reconheceu) {
      System.out.println("Sintaticamente correto");
    } else {
      erro("parse");
    }
  }


  // Analisador sintático para laço enquanto (while)
  private boolean whileLoop() {
    // Estrutura esperada: id operador num : expressao
    if (condicao() && matchL(":") && expressao()) {
      return true;
    }
    erro("whileLoop");
    return false;
  }

  public Token getNextToken() {
    if (!tokens.isEmpty()) 
      return tokens.remove(0);
    return null;
  }

  private void erro(String regra) {
    System.out.println("-------------- Regra: " + regra);
    System.out.println("token inválido: " + token);
    System.out.println("------------------------------");
  }


  private boolean ifelse(boolean reportarErro) {
    if (matchL("if") &&
        condicao() &&
        matchL("then") &&
        expressao() &&
        matchL("else") &&
        expressao()) {
      return true;
    }
    if (reportarErro) {
      erro("ifelse");
    }
    return false;
  }

  private boolean condicao() {
    if (id() && operador() && num())
      return true;

    erro("condicao");
    return false;
  }

  private boolean expressao() {
    if (id() && operadorAtribuicao() && num())
      return true;

    erro("expressao");
    return false;
  }

  private boolean operador() {
    if (token != null && token.tipo.equals("operador_condicional") &&
        (token.lexema.equals(">") || token.lexema.equals("<") || token.lexema.equals("=="))) {
      token = getNextToken();
      return true;
    }
    erro("operador");
    return false;
  }

  private boolean operadorAtribuicao() {
    if (matchL("=")) 
      return true;

    erro("operadorAtribuicao");
    return false;
  }

  private boolean id() {
    if (matchT("id")) 
      return true;

    erro("id");
    return false;
  }

  private boolean num() {
    if (matchT("num"))
      return true;

    erro("num");
    return false;
  }

  private boolean matchT(String tipo){
    if (token.tipo.equals(tipo)){
      token = getNextToken();
      return true;
    }
    return false;
  }

  private boolean matchL(String lexema){
    if (token.lexema.equals(lexema)){
      token = getNextToken();
      return true;
    }
    return false;
  }
}
