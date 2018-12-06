package ru.otus.l041.framework;

class Output {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String GREEN_BOLD = "\033[1;32m";
    private static final String BLUE_BOLD = "\033[1;34m";
    private static final String ANSI_CYAN = "\u001B[36m";

    static void title(String title) {
        System.out.printf("%s%s%s\n", BLUE_BOLD, title, ANSI_RESET);
    }

    static void positive(String text) {
        System.out.printf("%s%s%s\n", GREEN_BOLD, text, ANSI_RESET);
    }

    static void info(String text) {
        System.out.printf("%s%s%s\n", ANSI_CYAN, text, ANSI_RESET);
    }

    static void finish() {
        System.out.printf("%s---------------------------%s\n\n", ANSI_CYAN, ANSI_RESET);
    }

}
