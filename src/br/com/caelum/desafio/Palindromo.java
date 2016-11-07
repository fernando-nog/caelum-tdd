package br.com.caelum.desafio;

public class Palindromo {
	
	public static boolean ehPalindromo(String frase) {

        String fraseFiltrada = frase
                .toUpperCase().replace(" ", "").replace("-", "");

        for(int i = 0; i < fraseFiltrada.length(); i++) {
            if(fraseFiltrada.charAt(i) != 
                    fraseFiltrada.charAt(fraseFiltrada.length() -i)) {
                return false;
            }
        }

        return true;
    }
}
