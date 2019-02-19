package com.vinaysshenoy.gildedrosekata

import arguments
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import strikt.api.expectThat
import strikt.assertions.isEqualTo

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GildedRoseTest {

  @ParameterizedTest(name = "{0} must have sell in days of {2} if waiting for {1} days")
  @MethodSource("params for computing sell in days")
  fun `sell in days must always decrease by one after updating quality`(
      items: List<Item>,
      daysToRun: Int,
      expectedSellInDays: List<Int>
  ) {
    val gildedRose = GildedRose(items = items.toTypedArray())

    (0 until daysToRun).forEach {
      gildedRose.updateQuality()
    }

    val sellInDays = gildedRose.items.map { it.sellIn }

    expectThat(sellInDays).isEqualTo(expectedSellInDays)
  }

  fun `params for computing sell in days`(): List<Arguments> {
    fun generateTestCase(items: List<Item>, daysToRun: Int, expectedSellInDays: List<Int>): Arguments {
      return arguments(items, daysToRun, expectedSellInDays)
    }

    return listOf(
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 1)
            ),
            daysToRun = 0,
            expectedSellInDays = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 1)
            ),
            daysToRun = 1,
            expectedSellInDays = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 1)
            ),
            daysToRun = 2,
            expectedSellInDays = listOf(-1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 1),
                Item(name = "item 2", sellIn = 2, quality = 1)
            ),
            daysToRun = 3,
            expectedSellInDays = listOf(2, -1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 1),
                Item(name = "item 2", sellIn = 2, quality = 1)
            ),
            daysToRun = 6,
            expectedSellInDays = listOf(-1, -4)
        )
    )
  }

  @ParameterizedTest(name = "Sulfuras: {0} must have sell in days of {2} if waiting for {1} days")
  @MethodSource("params for computing sell in days of sulfuras")
  fun `sell in days of sulfuras must never change`(
      items: List<Item>,
      daysToRun: Int,
      expectedSellInDays: List<Int>
  ) {
    val gildedRose = GildedRose(items = items.toTypedArray())

    (0 until daysToRun).forEach {
      gildedRose.updateQuality()
    }

    val sellInDays = gildedRose.items.map { it.sellIn }

    expectThat(sellInDays).isEqualTo(expectedSellInDays)
  }

  fun `params for computing sell in days of sulfuras`(): List<Arguments> {
    fun generateTestCase(items: List<Item>, daysToRun: Int, expectedSellInDays: List<Int>): Arguments {
      return arguments(items, daysToRun, expectedSellInDays)
    }

    return listOf(
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 1, quality = 1)
            ),
            daysToRun = 0,
            expectedSellInDays = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 1, quality = 1)
            ),
            daysToRun = 1,
            expectedSellInDays = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 5, quality = 1)
            ),
            daysToRun = 3,
            expectedSellInDays = listOf(5)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 5, quality = 1),
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -5, quality = 1)
            ),
            daysToRun = 6,
            expectedSellInDays = listOf(5, -5)
        )
    )
  }

  @ParameterizedTest(name = "{0} must have qualities of {2} if waiting for {1} days")
  @MethodSource("params for computing quality")
  fun `quality must decrease by one after updating quality`(
      items: List<Item>,
      daysToRun: Int,
      expectedQualities: List<Int>
  ) {
    val gildedRose = GildedRose(items = items.toTypedArray())

    (0 until daysToRun).forEach {
      gildedRose.updateQuality()
    }

    val qualiies = gildedRose.items.map { it.quality }

    expectThat(qualiies).isEqualTo(expectedQualities)
  }

  fun `params for computing quality`(): List<Arguments> {
    fun generateTestCase(items: List<Item>, daysToRun: Int, expectedQualities: List<Int>): Arguments {
      return arguments(items, daysToRun, expectedQualities)
    }

    return listOf(
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 1)
            ),
            daysToRun = 0,
            expectedQualities = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 1)
            ),
            daysToRun = 1,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 10)
            ),
            daysToRun = 5,
            expectedQualities = listOf(5)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 10)
            ),
            daysToRun = 6,
            expectedQualities = listOf(3)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 10)
            ),
            daysToRun = 7,
            expectedQualities = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 10)
            ),
            daysToRun = 8,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 7)
            ),
            daysToRun = 1,
            expectedQualities = listOf(6)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 7)
            ),
            daysToRun = 2,
            expectedQualities = listOf(5)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 7)
            ),
            daysToRun = 4,
            expectedQualities = listOf(3)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 7)
            ),
            daysToRun = 7,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 4)
            ),
            daysToRun = 2,
            expectedQualities = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 1, quality = 4)
            ),
            daysToRun = 10,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "item 1", sellIn = 5, quality = 7),
                Item(name = "item 2", sellIn = 2, quality = 7)
            ),
            daysToRun = 4,
            expectedQualities = listOf(3, 1)
        )
    )
  }

  @ParameterizedTest(name = "Aged Brie: {0} must have qualities of {2} if waiting for {1} days")
  @MethodSource("params for quality of aged brie")
  fun `quality of aged brie must increase instead of decrease`(
      items: List<Item>,
      daysToRun: Int,
      expectedQualities: List<Int>
  ) {
    val gildedRose = GildedRose(items = items.toTypedArray())

    (0 until daysToRun).forEach {
      gildedRose.updateQuality()
    }

    val qualiies = gildedRose.items.map { it.quality }

    expectThat(qualiies).isEqualTo(expectedQualities)
  }

  fun `params for quality of aged brie`(): List<Arguments> {
    fun generateTestCase(items: List<Item>, daysToRun: Int, expectedQualities: List<Int>): Arguments {
      return arguments(items, daysToRun, expectedQualities)
    }

    return listOf(
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 1, quality = 1)
            ),
            daysToRun = 0,
            expectedQualities = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 1, quality = 1)
            ),
            daysToRun = 1,
            expectedQualities = listOf(2)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 5, quality = 10)
            ),
            daysToRun = 5,
            expectedQualities = listOf(15)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 5, quality = 10)
            ),
            daysToRun = 6,
            expectedQualities = listOf(17)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 5, quality = 10)
            ),
            daysToRun = 7,
            expectedQualities = listOf(19)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 5, quality = 10),
                Item(name = "Aged Brie", sellIn = 25, quality = 10)
            ),
            daysToRun = 7,
            expectedQualities = listOf(19, 17)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Aged Brie", sellIn = 5, quality = 10),
                Item(name = "Aged Brie", sellIn = 25, quality = 10),
                Item(name = "Aged Brie", sellIn = 1, quality = 36),
                Item(name = "Aged Brie", sellIn = 1, quality = 37),
                Item(name = "Aged Brie", sellIn = 1, quality = 38),
                Item(name = "Aged Brie", sellIn = 1, quality = 39)
            ),
            daysToRun = 7,
            expectedQualities = listOf(19, 17, 49, 50, 50, 50)
        )
    )
  }

  @ParameterizedTest(name = "Sulfuras: {0} must have qualities of {2} if waiting for {1} days")
  @MethodSource("params for quality of sulfuras")
  fun `quality of sulfuras must never change`(
      items: List<Item>,
      daysToRun: Int,
      expectedQualities: List<Int>
  ) {
    val gildedRose = GildedRose(items = items.toTypedArray())

    (0 until daysToRun).forEach {
      gildedRose.updateQuality()
    }

    val qualiies = gildedRose.items.map { it.quality }

    expectThat(qualiies).isEqualTo(expectedQualities)
  }

  fun `params for quality of sulfuras`(): List<Arguments> {
    fun generateTestCase(items: List<Item>, daysToRun: Int, expectedQualities: List<Int>): Arguments {
      return arguments(items, daysToRun, expectedQualities)
    }

    return listOf(
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 1, quality = 0)
            ),
            daysToRun = 0,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 1, quality = 0)
            ),
            daysToRun = 1,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 3, quality = 5)
            ),
            daysToRun = 3,
            expectedQualities = listOf(5)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -5, quality = -5)
            ),
            daysToRun = 10,
            expectedQualities = listOf(-5)
        ),
        generateTestCase(
            items = listOf(
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = -10, quality = -5),
                Item(name = "Sulfuras, Hand of Ragnaros", sellIn = 10, quality = 50)
            ),
            daysToRun = 15,
            expectedQualities = listOf(-5, 50)
        )
    )
  }

  @ParameterizedTest(name = "Backstage passes: {0} must have qualities of {2} if waiting for {1} days")
  @MethodSource("params for quality of backstage passes")
  fun `quality of backstage passes must increase instead of decrease until the sell by date passes`(
      items: List<Item>,
      daysToRun: Int,
      expectedQualities: List<Int>
  ) {
    val gildedRose = GildedRose(items = items.toTypedArray())

    (0 until daysToRun).forEach {
      gildedRose.updateQuality()
    }

    val qualiies = gildedRose.items.map { it.quality }

    expectThat(qualiies).isEqualTo(expectedQualities)
  }

  fun `params for quality of backstage passes`(): List<Arguments> {
    fun generateTestCase(items: List<Item>, daysToRun: Int, expectedQualities: List<Int>): Arguments {
      return arguments(items, daysToRun, expectedQualities)
    }

    val name = "Backstage passes to a TAFKAL80ETC concert"

    return listOf(
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 1, quality = 1)
            ),
            daysToRun = 0,
            expectedQualities = listOf(1)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 1, quality = 1)
            ),
            daysToRun = 1,
            expectedQualities = listOf(4)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 1, quality = 1)
            ),
            daysToRun = 2,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 5, quality = 10)
            ),
            daysToRun = 5,
            expectedQualities = listOf(25)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 5, quality = 10)
            ),
            daysToRun = 6,
            expectedQualities = listOf(0)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 10, quality = 1)
            ),
            daysToRun = 8,
            expectedQualities = listOf(20)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 15, quality = 1)
            ),
            daysToRun = 5,
            expectedQualities = listOf(6)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 5, quality = 10),
                Item(name = name, sellIn = 7, quality = 10),
                Item(name = name, sellIn = 25, quality = 10)
            ),
            daysToRun = 7,
            expectedQualities = listOf(0, 29, 17)
        ),
        generateTestCase(
            items = listOf(
                Item(name = name, sellIn = 5, quality = 10),
                Item(name = name, sellIn = 25, quality = 10),
                Item(name = name, sellIn = 10, quality = 50),
                Item(name = name, sellIn = 8, quality = 49),
                Item(name = name, sellIn = 13, quality = 10)
            ),
            daysToRun = 7,
            expectedQualities = listOf(0, 17, 50, 50, 21)
        )
    )
  }
}
