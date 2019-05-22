package com.gk.proxy.aop;

/**
 * Created by akun on 2018/12/20.
 */
public interface TicketService {
    /**
     * 售票
     */
    void sellTicket();

    /**
     * 问询
     */
    void inquire();

    /**
     * 售票
     */
    void withdraw();
}