package me.sargunvohra.lib.gsonkotlin;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

// Writing this class in Java reminded me why I love Kotlin so much...
// Just compare this to the 1-line Kotlin equivalent 'KFoo.kt'!

public class JFoo {

    @NotNull
    private final String x;

    @Nullable
    private final String y;

    public JFoo(@NotNull String x, @Nullable String y) {
        this.x = x;
        this.y = y;
    }

    @NotNull
    public String getX() {
        return x;
    }

    @Nullable
    public String getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JFoo jFoo = (JFoo) o;

        return x.equals(jFoo.x) && (y != null ? y.equals(jFoo.y) : jFoo.y == null);

    }

    @Override
    public int hashCode() {
        int result = x.hashCode();
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }
}
