package kz.bloooom.administration.converter.transaction;

import kz.bloooom.administration.domain.dto.transaction.TransactionInfoDto;
import kz.bloooom.administration.domain.entity.Transactions;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TransactionInfoDtoConverter {

    public TransactionInfoDto convert(Transactions source) {
        TransactionInfoDto target = new TransactionInfoDto();

        target.setUserName(source.getUser().getName());
        target.setAmount(source.getAmount());
        target.setCurrency(source.getCurrency().getTitle());
        target.setType(source.getType().getName().get("ru"));
        target.setStatus(source.getStatus().getName().get("ru"));
        return target;
    }

    public List<TransactionInfoDto> convert(List<Transactions> bouquets) {
        return CollectionUtils.isEmpty(bouquets) ?
                Collections.emptyList() :
                bouquets.stream().map(this::convert).collect(Collectors.toList());
    }
}
