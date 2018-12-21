
package com.gildedrose;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;

public class GildedRoseTest {

    // Interface
    GildedRose gildedRose;

    @Before
    public void setUp() {
	gildedRose = new GildedRoseImpl();
    }

    @Test
    public void testUpdateQualityOfAbedBrieItem() {
	// Given
	List<Item> items = Stream
		.of(new Item("Aged Brie", 2, 0), new Item("Aged Brie", 2, 51), new Item("Aged Brie", 4, -3))
		.collect(Collectors.toList());

	// When
	gildedRose.updateQuality(items);

	// Then
	assertEquals("Quality item 0", 1, items.get(0).getQuality());
	assertEquals("SellIn item 0", 1, items.get(0).getSellIn());
	assertEquals("Quality item 1", 51, items.get(1).getQuality());
	assertEquals("SellIn item 1", 2, items.get(1).getSellIn());
	assertEquals("Quality item 2", -3, items.get(2).getQuality());
	assertEquals("SellIn item 2", 4, items.get(2).getSellIn());

	// Print result
	items.forEach(System.out::println);

    }

    @Test
    public void testUpdateQualityOfSulfurasItem() {
	// Given
	List<Item> items = Stream
		.of(new Item("Sulfuras", 2, 0), new Item("Sulfuras", 2, 51), new Item("Sulfuras", 4, -3))
		.collect(Collectors.toList());

	// When
	gildedRose.updateQuality(items);

	// Then
	assertEquals("Quality item 0", 0, items.get(0).getQuality());
	assertEquals("SellIn item 0", 2, items.get(0).getSellIn());
	assertEquals("Quality item 1", 51, items.get(1).getQuality());
	assertEquals("SellIn item 1", 2, items.get(1).getSellIn());
	assertEquals("Quality item 2", -3, items.get(2).getQuality());
	assertEquals("SellIn item 2", 4, items.get(2).getSellIn());

	// Print result
	items.forEach(System.out::println);

    }

    @Test
    public void testUpdateQualityOfBackstagePassesItem() {
	// Given
	List<Item> items = Stream.of(new Item("Backstage passes", 2, 0), new Item("Backstage passes", 2, 51),
		new Item("Backstage passes", 4, -3), new Item("Backstage passes", 11, 12),
		new Item("Backstage passes", 5, 49)).collect(Collectors.toList());

	// When
	gildedRose.updateQuality(items);

	// Then
	assertEquals("Quality item 0", 3, items.get(0).getQuality());
	assertEquals("SellIn item 0", 1, items.get(0).getSellIn());
	assertEquals("Quality item 1", 51, items.get(1).getQuality());
	assertEquals("SellIn item 1", 2, items.get(1).getSellIn());
	assertEquals("Quality item 2", -3, items.get(2).getQuality());
	assertEquals("SellIn item 2", 4, items.get(2).getSellIn());
	assertEquals("Quality item 3", 13, items.get(3).getQuality());
	assertEquals("SellIn item 3", 10, items.get(3).getSellIn());
	assertEquals("Quality item 4", 50, items.get(4).getQuality());
	assertEquals("SellIn item 4", 4, items.get(4).getSellIn());

	// Print result
	items.forEach(System.out::println);

    }

    @Test
    public void testGlobalUpdateQuality() {

	// Given
	List<Item> items = Stream.of(
		new Item("Dexterity Vest", 10, 20), new Item("Aged Brie", 2, 0),
		new Item("Elixir of the Mongoose", 5, 7), new Item("Sulfuras", 0, 80),
		new Item("Sulfuras, Hand of Ragnaros", -1, 80), new Item("Backstage passes", 15, 20),
		new Item("Backstage passes", 3, 20),
		new Item("Backstage passes", 10, 49), new Item("Backstage passes", 5, 49),
		new Item("Sulfuras", 2, 43), new Item("Backstage passes", 6, 23),
		new Item("Conjured", 3, 6)).collect(Collectors.toList());

	// When
	gildedRose.updateQuality(items);
	
	// Then
	assertEquals("Quality item 0 ", 19, items.get(0).getQuality());
	assertEquals("Quality item 1 ", 1, items.get(1).getQuality());
	assertEquals("Quality item 2 ", 6, items.get(2).getQuality());
	assertEquals("Quality item 3 not updated because its ovenr than 50 !", 80, items.get(3).getQuality());
	assertEquals("Quality item 4 not updated because its negative !", 80, items.get(4).getQuality());
	assertEquals("Quality item 4 not updated because sellIn is null !", -1, items.get(4).getSellIn());
	assertEquals("Quality item 5 ", 21, items.get(5).getQuality());
	assertEquals("Quality item 6 ", 23, items.get(6).getQuality());
	assertEquals("Quality item 7 ", 50, items.get(7).getQuality());
	assertEquals("Quality item 8 ", 50, items.get(8).getQuality());
	assertEquals("Quality item 9 ", 43, items.get(9).getQuality());
	assertEquals("Quality item 10 ", 25, items.get(10).getQuality());
	assertEquals("Quality item 11 ", 5, items.get(11).getQuality());

	// Print result
	items.forEach(System.out::println);

    }

}