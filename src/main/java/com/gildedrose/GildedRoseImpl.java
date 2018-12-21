package com.gildedrose;

import java.util.List;
import java.util.logging.Logger;

/**
 * 
 * @author Ismail
 *
 */
public class GildedRoseImpl implements GildedRose {

    Logger logger = Logger.getLogger("logger");
    public final int MIN_DAY = 5;
    public final int INCREASE_MIN_NUMBER = 3;
    public final int MAX_DAY = 10;
    public final int INCREASE_MAX_NUMBER = 2;
    public final int MAX_QUALITY = 50;

    /**
     * Update quality of each items
     * 
     * @param items
     *            list of items
     */
    public void updateQuality(List<Item> items) {
	items.stream().forEach(item -> {
	    if (item.getQuality() < 0 || item.getQuality() > 50) {
		logger.info("The Quality of item " + item.getName() + " is more than 50 or negative");
		return;
	    }

	    switch (item.getName()) {
	    case "Aged Brie":
		increaseQuality(item, MAX_QUALITY);
		break;
	    case "Backstage passes":
		increaseQuality(item, MIN_DAY, INCREASE_MIN_NUMBER, MAX_DAY, INCREASE_MAX_NUMBER, MAX_QUALITY);
		break;
	    case "Sulfuras":
		// never has to be sold or decreases in Quality
		break;

	    default:
		item.setQuality(item.getQuality() - 1);
		break;
	    }

	    if (!item.getName().equals("Sulfuras")) {
		item.setSellIn(item.getSellIn() - 1);
	    }

	});
    }

    /**
     * Increase Quality with param
     * 
     * @param item
     * @param minDay
     * @param increaseMinNumber
     * @param maxDay
     * @param increaseMaxNumber
     * @param maxQuality
     * 
     */
    private void increaseQuality(Item item, int minDay, int increaseMinNumber, int maxDay, int increaseMaxNumber,
	    int maxQuality) {
	if (item.getSellIn() <= minDay && item.getQuality() + increaseMinNumber <= maxQuality) {
	    item.setQuality(item.getQuality() + increaseMinNumber);

	} else if ((item.getSellIn() > minDay && item.getSellIn() <= maxDay)
		&& item.getQuality() + increaseMaxNumber <= maxQuality) {
	    item.setQuality(item.getQuality() + increaseMaxNumber);
	} else {
	    increaseQuality(item, maxQuality);
	}
    }

    /**
     * Increase quality by 1, without exceeding the quality max
     * 
     * @param item
     * @param maxQuality
     */
    private void increaseQuality(Item item, int maxQuality) {
	if (item.getQuality() < maxQuality) {
	    item.setQuality(item.getQuality() + 1);
	}

    }

}