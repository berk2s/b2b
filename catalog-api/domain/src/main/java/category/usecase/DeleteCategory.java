package category.usecase;

import lombok.Builder;
import lombok.Data;
import usecase.UseCase;

@Data
@Builder
public class DeleteCategory implements UseCase {

    private Long categoryId;
}
