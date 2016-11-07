package br.com.caelum.desafio;

public class Palindromo {
	
	public static boolean ehPalindromo(String frase) {

        String fraseFiltrada = frase
                .toUpperCase().replace(" ", "").replace("-", "");

        for(int i = 0; i < fraseFiltrada.length(); i++) {
        	int interadorReverso = fraseFiltrada.length() - (i + 1);
        	        	
            if(fraseFiltrada.charAt(i) != fraseFiltrada.charAt(interadorReverso)) {
                return false;
            }
        }

        return true;
    }
}
