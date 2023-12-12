/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.util;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class Option<K, V> {
    @Nullable
    private final K first;

    @Nullable
    private final V second;

    protected Option(K first, V second) {
        this.first = first;
        this.second = second;
    }

    public static <K, V> Option<K, V> ofFirst(@NotNull K first) {
        return new Option<>(first, null);
    }

    public static <K, V> Option<K, V> ofSecond(@NotNull V second) {
        return new Option<>(null, second);
    }

    public Optional<K> getFirst() {
        if (first == null) {
            return Optional.empty();
        }

        return Optional.of(first);
    }

    public Optional<V> getSecond() {
        if (second == null) {
            return Optional.empty();
        }

        return Optional.of(second);
    }

    public boolean hasFirst() {
        return first != null;
    }

    public boolean hasSecond() {
        return second != null;
    }
}
