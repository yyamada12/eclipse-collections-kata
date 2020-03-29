/*
 * Copyright (c) 2017 Goldman Sachs and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompany this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 */

package org.eclipse.collections.petkata;

import org.eclipse.collections.api.block.function.Function;
import org.eclipse.collections.api.block.predicate.Predicate;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.set.MutableSet;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Sets;
import org.eclipse.collections.impl.test.Verify;
import org.junit.Assert;
import org.junit.Test;

/**
 * In the slides leading up to this exercise you should have learned about the following APIs.
 * <p/>
 * {@link MutableList#flatCollect(Function)}<br>
 * {@link MutableList#select(Predicate)}<br>
 * {@link MutableList#reject(Predicate)}<br>
 * {@link MutableList#count(Predicate)}<br>
 * {@link MutableList#detect(Predicate)}<br>
 * {@link MutableList#anySatisfy(Predicate)}<br>
 * {@link MutableList#allSatisfy(Predicate)}<br>
 * {@link MutableList#noneSatisfy(Predicate)}<br>
 * <br>
 * You should have also learned about the following methods as well.<br>
 * <br>
 * {@link MutableList#selectWith(Predicate2, Object)}<br>
 * {@link MutableList#rejectWith(Predicate2, Object)}<br>
 * {@link MutableList#countWith(Predicate2, Object)}<br>
 * {@link MutableList#detectWith(Predicate2, Object)}<br>
 * {@link MutableList#anySatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#allSatisfyWith(Predicate2, Object)}<br>
 * {@link MutableList#noneSatisfyWith(Predicate2, Object)}<br>
 * <br>
 *
 * @see <a href="http://eclipse.github.io/eclipse-collections-kata/pet-kata/#/4">Exercise 2 Slides</a>
 */
public class Exercise2Test extends PetDomainForKata
{
    @Test
    public void doAnyPeopleHaveCats()
    {
        Predicate<Person> predicate = person -> person.hasPet(PetType.CAT);
        Assert.assertTrue(this.people.anySatisfy(predicate));
    }

    @Test
    public void doAllPeopleHavePets()
    {
        Predicate<Person> predicate = person -> person.isPetPerson();
        boolean result = people.allSatisfy(predicate);
        Assert.assertFalse(result);
    }

    @Test
    public void howManyPeopleHaveCats()
    {
        int count = people.countWith(Person::hasPet, PetType.CAT);
        Assert.assertEquals(2, count);
    }

    @Test
    public void findMarySmith()
    {
        Person result = people.detectWith(Person::named, "Mary Smith");
        Assert.assertEquals("Mary", result.getFirstName());
        Assert.assertEquals("Smith", result.getLastName());
    }

    @Test
    public void getPeopleWithPets()
    {
        MutableList<Person> petPeople = this.people.select(Person::isPetPerson);
        Verify.assertSize(7, petPeople);
    }

    @Test
    public void getAllPetTypesOfAllPeople()
    {
        Function<Person, Iterable<PetType>> function = person -> person.getPetTypes();
        MutableSet<PetType> petTypes = people.flatCollect(function).toSet();
        Assert.assertEquals(
                Sets.mutable.with(PetType.CAT, PetType.DOG, PetType.TURTLE, PetType.HAMSTER, PetType.BIRD, PetType.SNAKE),
                petTypes);
    }

    @Test
    public void getFirstNamesOfAllPeople()
    {
        MutableList<String> firstNames = people.collect(Person::getFirstName);
        Assert.assertEquals(
                Lists.mutable.with("Mary", "Bob", "Ted", "Jake", "Barry", "Terry", "Harry", "John"),
                firstNames);
    }

    @Test
    public void doAnyPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.anySatisfy(person -> person.hasPet(PetType.CAT));
        Assert.assertTrue(peopleHaveCatsLambda);

        boolean peopleHaveCatsMethodRef = people.anySatisfyWith(Person::hasPet, PetType.CAT);
        Assert.assertTrue(peopleHaveCatsMethodRef);
    }

    @Test
    public void doAllPeopleHaveCatsRefactor()
    {
        boolean peopleHaveCatsLambda = this.people.allSatisfy(person -> person.hasPet(PetType.CAT));
        Assert.assertFalse(peopleHaveCatsLambda);

        boolean peopleHaveCatsMethodRef = people.allSatisfyWith(Person::hasPet, PetType.CAT);
        Assert.assertFalse(peopleHaveCatsMethodRef);
    }

    @Test
    public void getPeopleWithCatsRefactor()
    {
        //use method reference, NOT lambdas, to solve the problem below
        MutableList<Person> peopleWithCatsMethodRef = null;
        Verify.assertSize(2, peopleWithCatsMethodRef);
    }

    @Test
    public void getPeopleWithoutCatsRefactor()
    {
        //use method reference, NOT lambdas, to solve the problem below
        MutableList<Person> peopleWithoutCatsMethodRef = null;
        Verify.assertSize(6, peopleWithoutCatsMethodRef);
    }
}
