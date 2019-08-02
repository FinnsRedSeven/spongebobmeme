package words;

import java.util.Random;

public class RandomCase implements Option {


    @Override
    public String translateText(String input) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (Character c : input.toCharArray()) {
            sb.append(random.nextDouble()>.5 ? String.valueOf(c).toLowerCase():String.valueOf(c).toUpperCase());
        }
        return sb.toString();
    }


    @Override
    public String toString() {
        return "Random Case";
    }
}
