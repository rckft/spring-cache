package dev.rckft.cache;

public record Addends(double first, double second) {

    public Double sum() {
        return first + second;
    }

}
