package xen42.canadamod.recipe;

import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientAssignmentTest {

    @Test
    void repeatedIngredientsMatchDistinctSlots() {
        assertTrue(IngredientAssignment.matches(
                List.of("beetroot", "beetroot", "beetroot", "beetroot"),
                List.of("beetroot", "beetroot", "beetroot", "beetroot"),
                String::equals));
    }

    @Test
    void incorrectDuplicateQuantityIsRejected() {
        assertFalse(IngredientAssignment.matches(
                List.of("beetroot", "beetroot", "beetroot", "beetroot"),
                List.of("beetroot", "beetroot", "beetroot", "carrot"),
                String::equals));
    }

    @Test
    void ingredientOrderDoesNotMatter() {
        assertTrue(IngredientAssignment.matches(
                List.of("flour", "flour", "flour", "water"),
                List.of("water", "flour", "flour", "flour"),
                String::equals));
    }

    @Test
    void overlappingAlternativesUseDistinctSlots() {
        assertTrue(IngredientAssignment.matches(
                List.of("any_vegetable", "carrot"),
                List.of("carrot", "potato"),
                (required, supplied) ->
                        required.equals(supplied)
                        || required.equals("any_vegetable")
                           && List.of("carrot", "potato").contains(supplied)));
    }

    @Test
    void oneItemCannotSatisfyTwoRequirements() {
        assertFalse(IngredientAssignment.matches(
                List.of("any_vegetable", "carrot"),
                List.of("carrot", "stone"),
                (required, supplied) ->
                        required.equals(supplied)
                        || required.equals("any_vegetable")
                           && List.of("carrot", "potato").contains(supplied)));
    }

    @Test
    void missingIngredientIsRejected() {
        assertFalse(IngredientAssignment.matches(
                List.of("flour", "flour", "water"),
                List.of("flour", "water"),
                String::equals));
    }

    @Test
    void extraIngredientIsRejected() {
        assertFalse(IngredientAssignment.matches(
                List.of("flour", "water"),
                List.of("flour", "water", "sugar"),
                String::equals));
    }

    @Test
    void emptyRecipeMatchesEmptyInputs() {
        assertTrue(IngredientAssignment.matches(
                List.<String>of(),
                List.<String>of(),
                String::equals));
    }
}
