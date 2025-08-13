package game.renderer;

import game.utils.*;
import java.util.Scanner;

/**
 * Renders messages and phases throughout the Parade Card Game. This class
 * provides static methods for displaying various stages of the game flow, such
 * as welcome screens, setup phases, and final results.
 */
public class GamePhaseRenderer {

    public static void showWelcomeMessage(Scanner scanner) {
        Helper.flush();
        Helper.progressBar();
        Helper.flush();
        AsciiArt.welcomeArt();
        String border = "****************************************";

        int duration = Constants.TYPEWRITE_DURATION;
        System.out.println("\n" + border);
        Helper.typewrite("🎉 WELCOME TO THE PARADE CARD GAME! 🎭", duration);
        System.out.println(border + "\n");

        Helper.typewrite("🎴 Remember Players! The rule is simple.", duration);
        Helper.typewrite("🏆 Score as LOW as possible. Good Luck! 🍀\n", duration);

        System.out.println(border + "\n");

        Helper.pressEnterToContinue(scanner);
        Helper.flush();
    }

    public static void showGameSetup() {
        Helper.printBox("🎲 WELCOME TO THE GAME SETUP 🎲");
    }

    public static void showSetUpPlayers() {
        Helper.printBox("🎭 PLAYER SETUP 🎭");
    }

    public static void showGoodByeMessage() {
        System.out.println("╔═══════════════════════════════════════╗");
        System.out.println("║                                       ║");
        System.out.println("║     🌟 THANK YOU FOR PLAYING 🌟       ║");
        System.out.println("║             🎉  PARADE  🎉            ║");
        System.out.println("║                                       ║");
        System.out.println("╚═══════════════════════════════════════╝");
    }

    public static void showLastRoundPhase() {
        Helper.flush();
        Helper.printBox("🚨 Last Round 🚨");
        Helper.sleep(Constants.NORMAL_DELAY_TIME);
    }

    public static void showFinalPhase() {
        Helper.printBox("🎴 Add 2 Cards to Open Cards");
    }

    public static void showFlippingPhase() {
        Helper.printBox("🃏 Flipping Cards");
    }

    public static void showFinalResultPhase() {
        System.out.println("===============================");
        System.out.println("        FINAL RESULTS         ");
        System.out.println("===============================");
    }
}
