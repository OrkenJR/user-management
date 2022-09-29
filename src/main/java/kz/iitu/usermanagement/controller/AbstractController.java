package kz.iitu.usermanagement.controller;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Абстрактный класс для контроллеров чтобы обернуть в ResponseEntity
 * По возможности надо вынести в библиотеку
 *
 * @author Orken
 * @version 1.0.0
 **/
public class AbstractController {
    protected <T> ResponseEntity<T> responseWrap(T producer) {
        return Optional.of(producer).
                map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.noContent().build());
    }

    protected <T> ResponseEntity<T> responseWrap(Supplier<T> producer) {
        return producer.get() == null || (producer.get() instanceof List && ((List<?>) producer.get()).isEmpty()) ? ResponseEntity.noContent().build() :
                Optional.of(producer.get()).
                        map(ResponseEntity::ok)
                        .orElseGet(() -> ResponseEntity.noContent().build());
    }
}
