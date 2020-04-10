/*
 * Copyright (c) 2018 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.companykata;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.eclipse.collections.api.bag.MutableBag;
import org.eclipse.collections.api.block.function.primitive.DoubleFunction;
import org.eclipse.collections.impl.block.function.AddFunction;
import org.eclipse.collections.impl.collection.mutable.CollectionAdapter;
import org.eclipse.collections.impl.factory.Bags;
import org.eclipse.collections.impl.utility.Iterate;

/**
 * Has a number, a {@link Customer}, a {@link List} of {@link LineItem}s, and a boolean that states whether or not the order
 * has been delivered. There is a class variable that contains the next order number.
 */
public class Order
{
    private static final AtomicInteger NEXT_ORDER_NUMBER = new AtomicInteger(1);

    private final int orderNumber;
    private final MutableBag<LineItem> lineItems = Bags.mutable.empty();
    private boolean isDelivered;

    public Order()
    {
        this.orderNumber = NEXT_ORDER_NUMBER.getAndIncrement();
    }

    public static void resetNextOrderNumber()
    {
        NEXT_ORDER_NUMBER.set(1);
    }

    public void deliver()
    {
        this.isDelivered = true;
    }

    public boolean isDelivered()
    {
        return this.isDelivered;
    }

    public void addLineItem(LineItem aLineItem)
    {
        this.lineItems.add(aLineItem);
    }

    public void addLineItems(LineItem aLineItem, int num)
    {
        this.lineItems.addOccurrences(aLineItem, num);
    }

    public MutableBag<LineItem> getLineItems()
    {
        return this.lineItems;
    }

    @Override
    public String toString()
    {
        return "order " + this.orderNumber + " items: " + this.lineItems.size();
    }

    /**
     * Refactor to use {@link org.eclipse.collections.api.RichIterable#sumOfDouble(DoubleFunction)}.
     */
    public double getValue()
    {
        Collection<Double> itemValues = Iterate.collect(this.lineItems, LineItem::getValue);

        return CollectionAdapter.adapt(itemValues).injectInto(0.0, AddFunction.DOUBLE_TO_DOUBLE);
    }
}
