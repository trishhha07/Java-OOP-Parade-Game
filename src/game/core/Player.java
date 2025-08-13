package game.core;

import game.utils.Constants;
import java.util.*;

/**
 * Abstract base class representing a player in the game. Handles card
 * management, interactions with the parade, and score calculation.
 */
public abstract class Player {

    // ============================ Attributes ============================
    /**
     * The name of the player.
     */
    protected String name;

    /**
     * Cards in the player's hand (not visible to other players).
     */
    protected List<Card> closedCards;

    /**
     * Open cards grouped by color (visible to all players).
     */
    protected Map<String, List<Card>> openCards;

    /**
     * The player's current score.
     */
    protected int score;

    // ============================ Constructor ============================
    /**
     * Creates a new player with the specified name.
     *
     * @param name The name of the player.
     */
    public Player(String name) {
        this.name = name;
        this.closedCards = new ArrayList<>();
        this.openCards = new HashMap<>();
        this.score = 0;
    }
    // ============================ Player Type Check ============================
    /**
     * Checks if the player is a human player.
     *
     * @return false.
     */
    public boolean isHuman() {
        return false;
    }

    // ============================ Abstract Methods ============================
    public abstract void playCard(Parade parade, Scanner scanner);

    public abstract void finalPlay(Scanner scanner);

    // ============================ Card Initialization ============================
    /**
     * Initializes the player's closed cards by drawing cards from the deck.
     *
     * @param deck The deck to draw cards from.
     */
    public void initializeClosedCards(Deck deck) {
        for (int i = 0; i < Constants.INITIAL_HAND_SIZE; i++) {
            Card card = deck.removeCardFromDeck();
            if (card != null) {
                closedCards.add(card);
            }
        }
    }

    // ============================ Parade Interaction ============================
    /**
     * Draws cards from the parade based on the last played card. Cards are
     * added to the player's open cards if they match the color or have a lower
     * value than the played card.
     *
     * @param parade The parade from which cards are drawn.
     */
    public List<Card> drawCardsFromParade(Parade parade) {

        // Not necessary but kept for consistency
        if (parade.isEmpty()) {
            return new ArrayList<>();
        }

        Card playedCard = parade.getLastPlayedCard();
        List<Card> cardsToReceive = parade.getEligibleCards(playedCard);
        parade.removeCards(cardsToReceive);
        addCardsToOpenCards(cardsToReceive);

        return cardsToReceive;
    }

    private void addCardsToOpenCards(List<Card> cards) {
        for (Card card : cards) {
            openCards.computeIfAbsent(card.getColor(), key -> new ArrayList<>()).add(card);
        }
    }

    // ============================ Deck Interaction ============================
    /**
     * Draws a single card from the deck and adds it to closed cards.
     *
     */
    public void drawCardFromDeck(Deck deck) {
        Card card = deck.removeCardFromDeck();
        if (card == null) {
            throw new IllegalStateException("Deck is empty!");
        }
        closedCards.add(card);
    }

    // ============================ Score Calculation ============================
    /**
     * Calculates the player's score based on the total value of open cards.
     */
    public void calculateScore() {
        score = 0;
        for (List<Card> cards : openCards.values()) {
            for (Card card : cards) {
                score += card.getValue();
            }
        }
    }

    // ============================ Getter Methods ============================
    public String getName() {
        return name;
    }

    public List<Card> getClosedCards() {
        return Collections.unmodifiableList(closedCards);
    }

    public Map<String, List<Card>> getOpenCards() {
        return Collections.unmodifiableMap(openCards);
    }

    public int getScore() {
        return score;
    }

    /**
     * Gets the total number of open cards the player has.
     *
     * @return The total count of open cards.
     */
    public int getTotalOpenCards() {
        return openCards.values().stream()
                .mapToInt(List::size)
                .sum();
    }

    /**
     * Counts the total number of colors in the player's open cards.
     *
     * @return The total color count of open cards.
     */
    public int getColorCount() {
        int totalCount = 0;
        for (List<Card> cards : openCards.values()) {
            totalCount += cards.size();
        }
        return totalCount;
    }

    // ============================ Setter Methods ============================
    public void setScore(int score) {
        this.score = score;
    }

    // For testing of tiebreakers
    public void setOpenCards(Map<String, List<Card>> openCards) {
        this.openCards = new HashMap<>(openCards);
    }
}
