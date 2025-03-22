import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import io.reactivex.Observable;

class Hangman {
    Observable<Output> play(Observable<String> words, Observable<String> letters) {
        return Observable.merge(
                words.map(word -> new Input(word, true)),
                letters.map(letter -> new Input(letter, false))
        ).scan(
                Output.empty(),
                (gameState, input) -> input.isWord()
                        ? processWord(gameState, input.input())
                        : processLetter(gameState, input.input())
        ).skip(1);
    }

    private Output processWord(Output gameState, String word) {
        if (gameState.status != Status.PLAYING) {
            return new Output(word, "_".repeat(word.length()), Set.of(), Set.of(), List.of(), Status.PLAYING);
        }

        return gameState;
    }

    private Output processLetter(Output gameState, String letter) {
        if (gameState.guess.contains(letter) || gameState.misses.contains(letter)) {
            throw new IllegalArgumentException("Letter " + letter + " was already played");
        }

        String newDiscovered = IntStream.range(0, gameState.secret.length())
                .filter(i -> gameState.secret.charAt(i) == letter.charAt(0))
                .boxed()
                .reduce(
                        gameState.discovered,
                        (discovered, i) -> discovered.replaceAll("(?<=^.{" + i + "}).", letter),
                        (d1, d2) -> d1 + d2);

        if (newDiscovered.equals(gameState.discovered)) {
            return new Output(
                    gameState.secret,
                    gameState.discovered,
                    gameState.guess,
                    Stream.concat(gameState.misses.stream(), Stream.of(letter)).collect(Collectors.toUnmodifiableSet()),
                    Stream.concat(gameState.parts.stream(), Stream.of(Part.values()[gameState.parts.size()])).toList(),
                    gameState.parts.size() == Part.values().length - 1
                            ? Status.LOSS
                            : gameState.status
            );
        }

        return new Output(
                gameState.secret,
                newDiscovered,
                Stream.concat(gameState.guess.stream(), Stream.of(letter)).collect(Collectors.toUnmodifiableSet()),
                gameState.misses,
                gameState.parts,
                !newDiscovered.contains("_") ? Status.WIN : gameState.status
        );
    }

    private record Input(String input, boolean isWord) {}
}