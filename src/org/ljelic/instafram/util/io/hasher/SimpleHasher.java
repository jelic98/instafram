package org.ljelic.instafram.util.io.hasher;

public class SimpleHasher implements Hasher {

    private enum HashType {
        HASH,
        DEHASH
    }

    private static final int OFFSET = 1;
    private static final int ALPHABET_LENGTH = 26;

    @Override
    public String hash(String content) {
        return compute(content, HashType.HASH);
    }

    @Override
    public String dehash(String content) {
        return compute(content, HashType.DEHASH);
    }

    private String compute(String content, HashType type) {
        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < content.length(); i++) {
            char oldChar = content.charAt(i);
            char newChar;

            if(Character.isLetter(oldChar)) {
                char start = Character.isUpperCase(oldChar) ? 'A' : 'a';
                int offset = type == HashType.HASH ? OFFSET : -OFFSET;

                newChar = (char) ((oldChar + offset - start) % ALPHABET_LENGTH + start);

                if(newChar < start) {
                    newChar += ALPHABET_LENGTH;
                }
            }else {
                newChar = oldChar;
            }

            builder.append(Character.toString(newChar));
        }

        return builder.toString();
    }
}