/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package me.munchii.Jasmin.result;

import me.munchii.Jasmin.util.Option;

import java.util.Optional;

public class JasminResult<T> extends Option<T, JasminInfo> {
    protected JasminResult(T first, JasminInfo second) {
        super(first, second);
    }

    public static <T> JasminResult<T> of(T result, JasminInfo info) {
        return new JasminResult<>(result, info);
    }

    public static <T> JasminResult<T> ofResult(T result) {
        return new JasminResult<>(result, null);
    }

    public static <T> JasminResult<T> ofInfo(JasminInfo info) {
        return new JasminResult<>(null, info);
    }

    public Optional<T> getResult() {
        return getFirst();
    }

    public Optional<JasminInfo> getInfo() {
        return getSecond();
    }

    public boolean hasResult() {
        return hasFirst();
    }

    public boolean hasInfo() {
        return hasSecond();
    }
}
