package dk.kea.unicodejpaopg.config;

import dk.kea.unicodejpaopg.model.Unicode;
import dk.kea.unicodejpaopg.repository.UnicodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class InitData implements CommandLineRunner {

    // Q: how many Unicode letters can there be in a Set<Character>?
    // A: 65536, because a char is 16 bits, and 2^16 = 65536

    // I InitData skriv en metode der gemmer alle de Unicode bogstaver i unicode sql tabellen, som kan være i et Set<Character>
    // Q: Unicode bogstav = et bogstav som kan vises på skærmen
    // A: Unicode bogstaverne er de bogstaver som kan vises på skærmen, og de er de bogstaver som er i unicode tabellen
    // Q: hvordan kan alle mulige bogstaver i et Set<Character> findes?
    // A: ved at finde alle bogstaverne i unicode tabellen, og gemme dem i et Set<Character>
    // Q: hvordan kan alle bogstaverne i unicode tabellen findes?

    @Override
    public void run(String... args) throws Exception {
        welcome();
        saveAllUnicodeLetters();
    }

    private void welcome() {
        System.out.println(
                " \n" +
                "Welcome to the Unicode JPA application\n" +
                "----------------------------------------\n" +
                "This application have the Use cases of: \n" +
                "        1. providing the Unicode of a given character\n" +
                "        2. providing the character of a given Unicode\n" +
                "        3. providing a sequence of characters starting from a given character\n" +
                "        4. saving all Unicode letters to the database with name and description\n" +
                "----------------------------------------\n" +
                "endpoints available: \n" +
                "        1. localhost/8080/unicode/{i} \n" +
                "        2. localhost/8080/charToUnicode/{c} \n" +
                "        3. localhost/8080/lettersSequence/{letter}/{count} \n" +
                "----------------------------------------\n" +
                "InitData initializing the DB...\n"

        );

        System.out.println("Enjoy the application!");

    }


    @Autowired
    private UnicodeRepository unicodeRepository;


    public void saveAllUnicodeLetters() {
        Set<Character> unicodeChars = new HashSet<>();
        // MAX_VALUE = 65535, siden char er 16 bits = 2^16 = 65536
        // 2^16: 16 bits i anden, fordi dette er max muligheder for bit combinationer
        for (int i = 0; i < Character.MAX_VALUE; i++) {
            char c = (char) i;
            unicodeChars.add(c);
        }


        Unicode unicode;
        for (Character c : unicodeChars) {

            try {
                unicode = new Unicode(c);
                if (unicodeRepository.existsById(unicode.getUnicode())) {
                    continue;
                }
                unicodeRepository.save(new Unicode(c));

            } catch (Exception e) {
                System.out.println("Exception occurred while processing character: " + c);
                e.printStackTrace();
            }
        }

    }

}
