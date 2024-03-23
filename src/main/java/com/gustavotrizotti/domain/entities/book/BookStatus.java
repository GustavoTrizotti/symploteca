package com.gustavotrizotti.domain.entities.book;

public enum BookStatus {
    AVAILABLE("Disponível"),
    CHECKED_OUT("Empresado");

    private String label;

    BookStatus(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
