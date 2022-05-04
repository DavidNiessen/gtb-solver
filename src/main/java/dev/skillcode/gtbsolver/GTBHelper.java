package dev.skillcode.gtbsolver;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GTBHelper {

    private static final Pattern pattern = Pattern.compile("(?<=§e).*$");

    private GTBHelper() {
    }

    private static String lastEncryptedWord = "";
    private static List<String> lastWords = new ArrayList<>();

    public static List<String> possibleWords(final String actionBar) {
        final Optional<String> optionalEncryptedWord = getEncryptedWord(actionBar);
        if (!optionalEncryptedWord.isPresent()) return new ArrayList<>();

        final String word = optionalEncryptedWord.get();
        if (word.equals(lastEncryptedWord)) return lastWords;
        lastEncryptedWord = word;

        final List<String> filteredWords = filterLengthAndWhitespaces(word, GTBWordList.words);

        if (filteredWords.isEmpty()) return filteredWords;

        final List<Pair<Integer, Character>> charList = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            final char character = word.charAt(i);
            if (character != '_' && character != ' ') charList.add(Pair.of(i, character));
        }

        if (charList.isEmpty()) return filteredWords;

        final List<String> words = filteredWords.stream().filter(current -> checkWord(current, charList)).collect(Collectors.toList());

        lastWords = words;
        return words;
    }

    private static List<String> filterLengthAndWhitespaces(final String encryptedWord, final List<String> words) {
        final int length = encryptedWord.length();
        final int whitespaces = StringUtils.countMatches(encryptedWord, " ");

        return words.stream().filter(current -> current.length() == length && StringUtils.countMatches(current, " ") == whitespaces).collect(Collectors.toList());
    }

    private static boolean checkWord(final String word, final List<Pair<Integer, Character>> charList) {
        int counter = 0;
        for (final Pair<Integer, Character> pair : charList) {
            if (word.charAt(pair.getLeft()) == pair.getRight()) {
                counter++;
            }
        }
        return counter == charList.size();
    }

    private static Optional<String> getEncryptedWord(final String actionBar) {
        final Matcher matcher = pattern.matcher(actionBar);
        if (!matcher.find()) return Optional.empty();

        String word = matcher.group().toLowerCase(Locale.ROOT);
        if (word.startsWith(" ")) {
            word = word.substring(1);
        }

        return Optional.of(word);
    }

}
