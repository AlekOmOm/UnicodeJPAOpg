package dk.kea.unicodejpaopg.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UnicodeRestController {

    // Endpoints:
    private static final String UNICODE = "/unicode/{i}";
    private static final String CHAR_TO_UNICODE = "/charToUnicode/{c}";
    private static final String LETTERS_SEQUENCE = "/lettersSequence/{letter}/{count}";


    @GetMapping(UNICODE)
    public String unicode(@PathVariable int i) {
        char c = (char)i;
        return "unicode=" + i + " char=" + c;
    }

    @GetMapping(CHAR_TO_UNICODE)
    public String charToUnicode(@PathVariable char c) {
        int i = c;
        return "char=" + c + " unicode=" + i;
    }

    @GetMapping(LETTERS_SEQUENCE)
    public String lettersSequence(@PathVariable char letter, @PathVariable int count) {
        // StringBuilder brugt, da det er hurtigere end String concatenation
            // dette gør at man kan bygge en string op, uden at lave en ny string hver gang
            // string syntax: "abc" +","+ "def" = "abcdef"
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < count; i++) {
            sb.append((char)(letter + i));
            sb.append(",");
        }

        return sb.toString();
    }

}
