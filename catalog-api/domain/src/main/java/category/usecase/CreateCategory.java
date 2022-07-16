package category.usecase;

import lombok.Builder;
import lombok.Data;
import usecase.UseCase;

@Data
@Builder
public class CreateCategory implements UseCase {

    private String title;
    private String slug;
    private Long parentId;
}
