package category.model;

import category.exception.EmptyCategoryTitle;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@RequiredArgsConstructor
@Data
public class Category implements Comparable<Category> {

    private final Long id;
    private final String title;
    private Category parent;
    private Set<Category> child = new TreeSet<>();

    public static Category newCategory(Long id, String title, Category parent) {
        var category = new Category(id, title);
        category.validate();
        category.setParent(parent);

        return category;
    }

    public void validate() {
        if (title == null) {
            log.warn("Category title is empty [category: {}]", this);
            throw new EmptyCategoryTitle("category.title.empty");
        }
    }

    public Set<Category> getParents() {
        Set<Category> parents = new TreeSet<>();

        var categoryParent = this.parent;

        while (categoryParent != null) {
            parents.add(categoryParent);
            categoryParent = categoryParent.getParent();
        }

        return parents;
    }

    public void setParent(Category parent) {
        if (parent == null) return;

        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Category category) {
        if (category == null) return;

        child.add(category);
    }

    @Override
    public int compareTo(Category o) {
        if (parent != null && o.getParent() == null)
            return 1;

        if (parent == null && o.getParent() == null)
            return  0;

        if (parent == null && o.getParent() != null)
            return  -1;

        return this.getChild().size() > o.getChild().size() ? 1 : -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(id, category.id) && Objects.equals(title, category.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}