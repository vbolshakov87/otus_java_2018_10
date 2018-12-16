package ru.otus.l041.framework;

class Output {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String GREEN_BOLD = "\033[1;32m";
    private static final String BLUE_BOLD = "\033[1;34m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String RED_BOLD = "\033[31m";

    static void title(String title) {
        System.out.printf("%s%s%s\n", BLUE_BOLD, title, ANSI_RESET);
    }

    static void positive(String text) {
        System.out.printf("%s%s%s\n", GREEN_BOLD, text, ANSI_RESET);
    }

    static void info(String text) {
        System.out.printf("%s%s%s\n", ANSI_CYAN, text, ANSI_RESET);
    }

    static void err(String text) {
        System.out.printf("%s%s%s\n", RED_BOLD, text, ANSI_RESET);
    }

    static void finishTest() {
        System.out.printf("%s---------------------------%s\n\n", ANSI_CYAN, ANSI_RESET);
    }

    static void stat(int passed, int failed) {
        if (passed > 0) {
            System.out.printf("%spassed: %d%s\n", GREEN_BOLD, passed, ANSI_RESET);
        }
        if (failed > 0) {
            System.out.printf("%sfailed:%d%s\n", RED_BOLD, failed, ANSI_RESET);
        }
    }

}
