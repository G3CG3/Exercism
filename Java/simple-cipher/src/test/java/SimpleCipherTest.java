import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleCipherTest {
    private Cipher randomKeyCipher;
    private Cipher substitutionCipher = new Cipher("abcdefghij");

    @BeforeEach
    public void setup() {
        randomKeyCipher = new Cipher();
    }

    /**
     * Here we take advantage of the fact that plaintext of "aaa..." doesn't output the key. This is a critical
     * problem with shift ciphers, some characters will always output the key verbatim.
     */
    @Test
    public void randomKeyCipherCanEncode() {
        String plainText = "aaaaaaaaaa";
        String cipherText = randomKeyCipher.getKey().substring(0, 10);
        assertThat(randomKeyCipher.encode(plainText)).isEqualTo(cipherText);
    }

    @Test
    public void randomKeyCipherCanDecode() {
        String cipherText = "aaaaaaaaaa";
        assertThat(randomKeyCipher.decode(randomKeyCipher.getKey().substring(0, 10)))
            .isEqualTo(cipherText);
    }

    @Test
    public void randomKeyCipherIsReversible() {
        String plainText = "abcdefghij";
        assertThat(randomKeyCipher.decode(randomKeyCipher.encode(plainText))).isEqualTo(plainText);
    }

    @Test
    public void randomKeyCipherKeyIsLowercaseLetters() {
        assertThat(randomKeyCipher.getKey()).matches("^[a-z]+$");
    }

    @Test
    public void substitutionCipherCanEncode() {
        String plainText = "aaaaaaaaaa";
        String cipherText = "abcdefghij";
        assertThat(substitutionCipher.encode(plainText)).isEqualTo(cipherText);
    }

    @Test
    public void substitutionCipherCanDecode() {
        String plainText = "abcdefghij";
        String cipherText = "aaaaaaaaaa";
        assertThat(substitutionCipher.decode(plainText)).isEqualTo(cipherText);
    }

    @Test
    public void substitutionCipherIsReversibleGivenKey() {
        String plainText = "abcdefghij";
        assertThat(substitutionCipher.decode(substitutionCipher.encode(plainText))).isEqualTo(plainText);
    }

    @Test
    public void substitutionCipherCanDoubleShiftEncode() {
        String plainText = "iamapandabear";
        String cipherText = "qayaeaagaciai";
        assertThat(new Cipher(plainText).encode(plainText)).isEqualTo(cipherText);
    }

    @Test
    public void substitutionCipherCanWrapEncode() {
        String plainText = "zzzzzzzzzz";
        String cipherText = "zabcdefghi";
        assertThat(substitutionCipher.encode(plainText)).isEqualTo(cipherText);
    }

    @Test
    public void substitutionCipherCanWrapDecode() {
        String plainText = "zabcdefghi";
        String cipherText = "zzzzzzzzzz";
        assertThat(substitutionCipher.decode(plainText)).isEqualTo(cipherText);
    }

    @Test
    public void substitutionCipherMessageLongerThanKey() {
        String plainText = "iamapandabear";
        String key = "abc";
        String cipherText = "iboaqcnecbfcr";
        assertThat(new Cipher(key).encode(plainText)).isEqualTo(cipherText);
    }
}

