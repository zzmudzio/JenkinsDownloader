package pl.zzmudzio.config;

public enum StatusColors {
    /*
    To make messages more readable, each error or success will be emphasized with applicable color.
    */
    ERROR("\u001B[31m"), SUCCESS("\u001B[32m"), RESET("\u001B[0m");

    private final String ansiCode;
    private StatusColors(String ansiCode) {
        this.ansiCode = ansiCode;
    }
    public String getAnsiCode() {
        return this.ansiCode;
    }
}
