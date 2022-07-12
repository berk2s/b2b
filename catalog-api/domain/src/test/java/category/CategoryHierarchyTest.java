package category;

import category.model.Category;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryHierarchyTest {

    @DisplayName("Should calculate parents successfully")
    @Test
    void should_calculate_parents_successfully() {
        // Given
        var parent1 = Category.newCategory(1L,"Top parent", null);
        var parent2 = Category.newCategory(2L,"Mid parent", parent1);
        var child = Category.newCategory(3L,"Child", parent2);

        // When
        TreeSet<Category> hierarchy = (TreeSet<Category>) child.getParents();

        // Then
        assertEquals(0, hierarchy.headSet(parent1).size());
        assertEquals(1, hierarchy.headSet(parent2).size());
    }

    @DisplayName("Should add a category as a child successfully")
    @Test
    void should_add_category_as_child_successfully() {
        // Given
        var parent = Category.newCategory(1L, "Parent", null);
        var child = Category.newCategory(1L, "Child", null);

        // When
        parent.addChild(child);

        // Then
        var parents = (TreeSet<Category>) child.getParents();
        var childs = (TreeSet<Category>) parent.getChild();

        assertEquals(0, parents.headSet(parent).size());
        assertEquals(0, childs.headSet(child).size());
    }
}
