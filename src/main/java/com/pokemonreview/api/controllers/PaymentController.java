package com.pokemonreview.api.controllers;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.web.bind.annotation.*;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;




@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Value("${stripe.success.url}")
    private String successUrl;

    @Value("${stripe.cancel.url}")
    private String cancelUrl;

    @PostMapping("/create-checkout-session")
    public String createCheckoutSession() throws StripeException {
        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency("lkr")
                                                .setUnitAmount(500000L) // Amount in cents (5000 LKR)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Advertisement Payment")
                                                                .build())
                                                .build())
                                .build())
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .build();

        Session session = Session.create(params);

        return session.getUrl();
    }

    @PostMapping("/webhook")
    public String handle(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader) {
        String endpointSecret = "your-webhook-secret";
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (StripeException e) {
            return "Error in webhook";
        }

        switch (event.getType()) {
            case "payment_intent.succeeded":
                System.out.println("Payment succeeded");
                break;
            case "payment_intent.payment_failed":
                System.out.println("Payment failed");
                break;
            default:
                System.out.println("Unhandled event type: " + event.getType());
        }

        return "";
    }
}
