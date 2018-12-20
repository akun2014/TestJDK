package com.gk.proxy.aop;

/**
 * Created by akun on 2018/12/20.
 */
public class RailwayStation implements TicketService {
    @Override
    public void sellTicket() {
        System.out.println("售票............");
    }

    @Override
    public void inquire() {
        System.out.println("问询.............");
    }

    @Override
    public void withdraw() {
        System.out.println("退票.............");
    }
}
