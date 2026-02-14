package com.generation.javaeat.api;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

/**
 * La classe MD5Hasher implementa funzionalità di hashing crittografico utilizzando
 * l'algoritmo MD5. Sebbene MD5 non sia considerato sicuro per applicazioni
 * crittografiche moderne a causa di vulnerabilità note, viene qui utilizzato
 * per scopi didattici o per applicazioni dove la sicurezza crittografica non è
 * un requisito primario. L'hashing delle password è una pratica fondamentale
 * nella sicurezza delle applicazioni: invece di memorizzare le password in
 * chiaro, si memorizza il loro hash, rendendo impossibile risalire alla
 * password originale partendo dal valore hashato memorizzato.
 */
@Service
public class MD5Hasher
{

    /**
     * Il metodo hash prende una stringa in input e restituisce la sua rappresentazione
     * esadecimale dopo aver applicato l'algoritmo di hashing MD5. Il processo di hashing
     * trasforma un input di qualsiasi lunghezza in una stringa di lunghezza fissa (128 bit
     * per MD5), apparentemente casuale ma deterministicamente calcolata. La conversione
     * in formato esadecimale rende il risultato leggibile e adatto alla memorizzazione
     * nel database.
     *
     * @param input La stringa da codificare, tipicamente una password in chiaro.
     * @return Una stringa esadecimale di 32 caratteri rappresentante l'hash MD5.
     * @throws RuntimeException Se l'algoritmo MD5 non è disponibile nell'ambiente di
     *                         esecuzione, situazione improbabile ma gestita per robustezza.
     */
    public String hash(String input)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes)
            {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}
