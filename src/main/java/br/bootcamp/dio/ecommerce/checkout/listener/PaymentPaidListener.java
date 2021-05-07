package br.bootcamp.dio.ecommerce.checkout.listener;

import br.bootcamp.dio.ecommerce.checkout.entity.CheckoutEntity;
import br.bootcamp.dio.ecommerce.checkout.repository.CheckoutRepository;
import br.bootcamp.dio.ecommerce.checkout.streaming.PaymentPaidSink;
import br.bootcamp.dio.ecommerce.payment.event.PaymentCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentPaidListener {

    private final CheckoutRepository checkoutRepository;

    @StreamListener(PaymentPaidSink.INPUT)
    public void handler(PaymentCreatedEvent event){
        final CheckoutEntity checkoutEntity = checkoutRepository.findByCode(event.getCheckoutCode().toString()).orElseThrow();
        checkoutEntity.setStatus(CheckoutEntity.Status.APPROVED);
        checkoutRepository.save(checkoutEntity);
    }

}
