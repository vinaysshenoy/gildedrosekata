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
}