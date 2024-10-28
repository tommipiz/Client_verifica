package it.pizzutilo;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) throws UnknownHostException , IOException{
       Socket s = new Socket("localHost", 3000);
       System.out.println("Connessione al server effettuata");

       try(BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            String input;

            while(true) {
                System.out.println("Inserisci una nota , '?' per visualizzare le note salvate, o '!' per uscire: ");
                input = userInput.readLine();

                out.writeBytes(input + "\n");

                if (input.equals("!")) {
                    System.out.println("Disconnessione...");
                    break;
                } else if(input.equals("?")) {
                    System.out.println("ecco la lista delle note salvate ");
                    String response;
                    while(!(response = in.readLine()).equals("@")) {
                    System.out.println(response);
                    }
                } else {
                    String response = in.readLine();
                    System.out.println(("Risposta del server: " + response));
                }
            }   
        } finally {
            s.close();
        }
    }
}