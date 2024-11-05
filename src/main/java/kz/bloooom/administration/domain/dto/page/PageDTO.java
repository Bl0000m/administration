package kz.bloooom.administration.domain.dto.page;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Обьект pageable")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PageDTO<T> {
     Long total;
     Integer pageNumber;
     Integer totalPages;
     List<T> content;
}
