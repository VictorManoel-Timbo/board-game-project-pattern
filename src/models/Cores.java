package models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Cores {
    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CIANO = "\u001B[36m";

    public static ArrayList<String> listaDeCores = new ArrayList<>(Arrays.asList(RED, GREEN, YELLOW, BLUE, PURPLE, CIANO));
    public static HashMap<String, String> nomeDasCores = new HashMap<>() {{
        put(RED, "vermelho");
        put(GREEN, "verde");
        put(YELLOW, "amarelo");
        put(BLUE, "azul");
        put(PURPLE, "roxo");
        put(CIANO, "ciano");
    }};
}
