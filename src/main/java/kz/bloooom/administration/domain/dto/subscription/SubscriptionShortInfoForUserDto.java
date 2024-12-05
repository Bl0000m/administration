package kz.bloooom.administration.domain.dto.subscription;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@ToString
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Schema(description = "Обьект для показа краткой информации")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SubscriptionShortInfoForUserDto {
    @Schema(description = "Id подписки")
    Long id;

    @Schema(description = "Наименование подписки")
    String name;
}
