package category.usecase;

import lombok.Builder;
import lombok.Data;
import usecase.UseCase;

import java.util.List;

@Data
@Builder
public class UpdateCategory implements UseCase {

    private Long categoryId;
    private String title;
    private String slug;
    private String desc;
    private String seoTitle;
    private String metaDesc;
    private String imgUrl;
    private Long parentId;
    private List<Long> childCategoryIds;
}
