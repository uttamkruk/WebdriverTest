package utility;

import org.apache.commons.codec.binary.Base64;

public class Decode {

    public static void main(String[] args) {
        System.out.println("The decoded value is: " + decode("Q0RWQzAwNzQwOTRATlVWT0xP"));
        System.out.println("The decoded value is: " + decode("Q0RWQzAwNjc1MzFATlVWT0xP"));
        System.out.println("The decoded value is: " + decode("MS1VUDctMTUzNEBTSUVCRUw"));
        System.out.println("The decoded value is: " + decode("MS1WMFEtNzc1QFNJRUJFTA"));

    }

    public static String decode(String id) {
        try {
            return new String(Base64.decodeBase64(id), "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(String.format("Unable to decode {%s}", id), e);
        }
    }

}
