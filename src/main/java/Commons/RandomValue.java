package Commons;

import java.security.SecureRandom;
import java.util.Random;

public class RandomValue {
    public static String genRandomUser() {
            Random random = new Random();
            int randomNumber = random.nextInt(1000) + 1; // Sinh số ngẫu nhiên từ 1 đến 100
            return "user" + randomNumber;
    }
    public static String genRandomGmail(){
        Random random = new Random();
        int randomNumber = random.nextInt(1000) + 1; // Sinh số ngẫu nhiên từ 1 đến 100
        return "email" + randomNumber+"@gmail.com";
    }
    public static final String NUMBERS = "123456789";
    private static final int ID_LENGTH = 8;
    private static final SecureRandom random = new SecureRandom();

    public static String genRandomID() {
        StringBuilder sb = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            int randomIndex = random.nextInt(NUMBERS.length());
            sb.append(NUMBERS.charAt(randomIndex));
        }
        return sb.toString();
    }
    public static int genRandomClOrderID() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000) + 1; // Sinh số ngẫu nhiên từ 1 đến 100
        return randomNumber;
    }

}
