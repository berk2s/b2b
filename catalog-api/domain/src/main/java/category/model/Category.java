package category.model;

import category.exception.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Category implements Comparable<Category> {

    private Long id;
    private String title;
    private String slug;
    private String seoTitle;
    private String metaDesc;
    private String imgUrl;
    private Category parent;

    @Builder.Default
    private Set<Category> child = new TreeSet<>();

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

    public static CategoryBuilder builder() {
        return new CustomBuilder();
    }

    private static class CustomBuilder extends CategoryBuilder {

        public Category build() {
            if (StringUtils.isEmpty(super.title)) {
                log.warn("Category title is empty [category: {}]", this);
                throw new EmptyTitle("category.title.empty");
            }

            if (StringUtils.isEmpty(super.slug)) {
                log.warn("Category slug is empty [category: {}]", this);
                throw new EmptySlug("category.slug.empty");
            }

            if (StringUtils.trim(super.title).length() < 3 || StringUtils.trim(super.title).length() > 50) {
                log.warn("Category title length is too short [category: {}]", this);
                throw new OutOfRangeTitle("category.title.outOfRange");
            }

            if (StringUtils.trim(super.slug).length() < 3 || StringUtils.trim(super.slug).length() > 50) {
                log.warn("Category slug length is too short or long [category: {}]", this);
                throw new OutOfRangeSlug("category.slug.outOfRange");
            }

            if (StringUtils.isNotEmpty(super.seoTitle) && StringUtils.trim(super.seoTitle).length() > 50) {
                log.warn("Too long seo title [category: {}]", this);
                throw new OutOfRangeSeoTitle("category.seoTitle.tooLong");
            }

            if (StringUtils.isNotEmpty(super.metaDesc) && StringUtils.trim(super.metaDesc).length() > 150) {
                log.warn("Too long meta desc [category: {}]", this);
                throw new OutOfRangeMetaDesc("category.metaDesc.tooLong");
            }

            return super.build();
        }
    }
}
