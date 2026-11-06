package xen42.canadamod.recipe;

import java.util.List;
import java.util.function.BiPredicate;

/**
 * Matches required ingredients to distinct input slots.
 *
 * The caller determines compatibility, allowing Minecraft Ingredient
 * predicates (including tags) without coupling this class to Minecraft.
 */
public final class IngredientAssignment {
    private IngredientAssignment() {}

    public static <I, S> boolean matches(
            List<I> ingredients,
            List<S> inputs,
            BiPredicate<I, S> compatible) {

        if (ingredients == null || inputs == null || compatible == null
                || ingredients.size() != inputs.size()) {
            return false;
        }

        return assign(ingredients, inputs, compatible, 0, new boolean[inputs.size()]);
    }

    private static <I, S> boolean assign(
            List<I> ingredients,
            List<S> inputs,
            BiPredicate<I, S> compatible,
            int ingredientIndex,
            boolean[] used) {

        if (ingredientIndex == ingredients.size()) {
            return true;
        }

        for (int slot = 0; slot < inputs.size(); slot++) {
            if (!used[slot] && compatible.test(ingredients.get(ingredientIndex), inputs.get(slot))) {
                used[slot] = true;

                if (assign(ingredients, inputs, compatible, ingredientIndex + 1, used)) {
                    return true;
                }

                used[slot] = false;
            }
        }

        return false;
    }
}
