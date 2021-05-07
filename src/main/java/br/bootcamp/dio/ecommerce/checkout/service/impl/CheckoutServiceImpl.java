package br.bootcamp.dio.ecommerce.checkout.service.impl;

import br.bootcamp.dio.ecommerce.checkout.entity.CheckoutEntity;
import br.bootcamp.dio.ecommerce.checkout.event.CheckoutCreatedEvent;
import br.bootcamp.dio.ecommerce.checkout.repository.CheckoutRepository;
import br.bootcamp.dio.ecommerce.checkout.resource_controller.checkout.CheckoutResquest;
import br.bootcamp.dio.ecommerce.checkout.service.CheckoutService;
import br.bootcamp.dio.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CheckoutServiceImpl implements CheckoutService {

    private final CheckoutRepository checkoutRepository;
    private final CheckoutCreatedSource checkoutCreatedSource;

    @Override
    public Optional<CheckoutEntity> create(CheckoutResquest checkoutResquest) {
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .build();
        final CheckoutEntity checkoutEntitySave = checkoutRepository.save(checkoutEntity);

        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder()
                .setCheckoutCode(checkoutEntitySave.getCode())
                .setStatus(checkoutEntitySave.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(checkoutEntitySave);
    }


}