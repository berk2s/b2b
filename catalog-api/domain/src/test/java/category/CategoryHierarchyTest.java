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
        var parent1 = Category.builder()
                .id(1L)
                .title("Top parent")
                .slug("top-parent")
                .parent(null)
                .build();

        var parent2 = Category.builder()
                .id(2L)
                .title("Mid parent")
                .slug("mid-parent")
                .parent(parent1)
                .build();

        var child = Category.builder()
                .id(3L)
                .title("Child")
                .slug("child")
                .parent(parent2)
                .build();

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
        var parent = Category.builder()
                .id(1L)
                .title("Top parent")
                .slug("top-parent")
                .parent(null)
                .build();

        var child = Category.builder()
                .id(3L)
                .title("Child")
                .slug("child")
                .parent(parent)
                .build();

        // When
        parent.addChild(child);

        // Then
        var parents = (TreeSet<Category>) child.getParents();
        var childs = (TreeSet<Category>) parent.getChild();

        assertEquals(0, parents.headSet(parent).size());
        assertEquals(0, childs.headSet(child).size());
    }
}
